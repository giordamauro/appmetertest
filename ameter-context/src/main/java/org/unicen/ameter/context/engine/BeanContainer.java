package org.unicen.ameter.context.engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.unicen.ameter.context.Bean;
import org.unicen.ameter.context.BeanDeclaration;
import org.unicen.ameter.context.BeanProperty;

public class BeanContainer {

    private final Map<String, String> placeholderProperties;

    private final Map<String, Object> instancesById = new HashMap<>();
    private final Map<Class<?>, Object> instancesByClass = new HashMap<>();

    public BeanContainer(List<Bean> beans, Map<String, String> placeholderProperties) {

        Objects.requireNonNull(beans, "Beans cannot be null");
        Objects.requireNonNull(placeholderProperties, "PlaceholderProperties cannot be null");

        this.placeholderProperties = placeholderProperties;

        for (Bean bean : beans) {
            createBean(bean);
        }
    }

    public Object createBean(Bean bean) {

        Object beanInstance = createBeanInstance(bean);
        addBeanProperties(beanInstance, bean.getProperties());

        registerBeanInstance(beanInstance, bean.getId());

        return beanInstance;
    }

    public Object createBeanInstance(Bean bean) {

        Objects.requireNonNull(bean, "Bean cannot be null");

        if (bean.getFactoryBean() != null && bean.getConstructorArgs() != null) {
            throw new IllegalStateException("Cannot instantiate bean having factoryBean and constructorArgs, only one must be present - " + bean);
        }

        Object beanInstance;

        Class<?> beanClass = getClassForName(bean.getClassType());

        if (bean.getConstructorArgs() != null) {

            List<BeanDeclaration> constructorArgs = bean.getConstructorArgs();
            List<ValueType> argList = getByDeclarationList(constructorArgs);

            beanInstance = createInstanceByConstructorArgs(beanClass, argList);
        } else if (bean.getFactoryBean() != null) {

            //TODO: create by FactoryBean
            throw new UnsupportedOperationException("Not implemented yet.");
        } else {
            beanInstance = createInstanceByDefaultConstructor(beanClass);
        }

        return beanInstance;
    }

    public void addBeanProperties(Object beanInstance, List<BeanProperty> properties) {

        Objects.requireNonNull(beanInstance, "BeanInstance cannot be null");

        if (properties != null) {

            for (BeanProperty property : properties) {

                if (property.getName() == null) {
                    throw new IllegalStateException("BeanProperty name cannot be null");
                }

                ValueType valueType = getByDeclaration(property);
                setFieldValue(beanInstance, property.getName(), valueType.getValue());
            }
        }
    }

    public void setFieldValue(Object target, String fieldName, Object value) {

        Objects.requireNonNull(target, "Target cannot be null");
        Objects.requireNonNull(fieldName, "FieldName cannot be null");

        Class<?> targetClass = target.getClass();
        try {
            Field propertyField = targetClass.getDeclaredField(fieldName);

            boolean accessible = propertyField.isAccessible();
            propertyField.setAccessible(true);
            propertyField.set(target, value);
            propertyField.setAccessible(accessible);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void registerBeanInstance(Object beanInstance, String beanId) {

        Objects.requireNonNull(beanInstance, "BeanInstance cannot be null");

        if (beanId != null) {

            if (instancesById.containsKey(beanId)) {
                throw new IllegalStateException(String.format("Bean id %s is not unique", beanId));
            }
            instancesById.put(beanId, beanInstance);
        }

        // TODO: Manage more than 1 class instance
        instancesByClass.put(beanInstance.getClass(), beanInstance);
    }

    public List<ValueType> getByDeclarationList(List<BeanDeclaration> declarationList) {

        List<ValueType> instanceList = new ArrayList<>();

        for (BeanDeclaration contructorArg : declarationList) {

            ValueType valueType = getByDeclaration(contructorArg);
            instanceList.add(valueType);
        }

        return instanceList;
    }

    public ValueType getByDeclaration(BeanDeclaration declaration) {

        ValueType valueType;

        if (declaration.getValue() != null) {

            String value = declaration.getValue();
            String type = declaration.getType();

            valueType = getValueType(value, type);
        } else {
            Object beanInstance;

            if (declaration.getRef() != null) {

                String ref = declaration.getRef();
                beanInstance = getBeanById(ref);
            } else if (declaration.getBean() != null) {

                Bean bean = declaration.getBean();
                beanInstance = createBean(bean);

            } else if (declaration.getList() != null) {

                List<ValueType> listValues = getByDeclarationList(declaration.getList());
                beanInstance = getValueTypesValues(listValues);

            } else {
                throw new IllegalStateException("Ref, Value, List or Bean must be set");
            }

            if (beanInstance == null) {
                throw new IllegalStateException("Bean instance cannot be null");
            }

            valueType = new ValueType(beanInstance.getClass(), beanInstance);
        }

        return valueType;
    }

    public ValueType getValueType(String value, String type) {

        String propertyValue = getPropertyValue(value);
        Class<?> valueType = getTypeClass(type);

        Object argValue = propertyValue;

        if (valueType.isAssignableFrom(Integer.class) || valueType.isAssignableFrom(int.class)) {
            argValue = Integer.valueOf(propertyValue);
        } else if (valueType.isAssignableFrom(Boolean.class) || valueType.isAssignableFrom(boolean.class)) {
            argValue = Boolean.valueOf(propertyValue);
        } else if (valueType.isAssignableFrom(Long.class) || valueType.isAssignableFrom(long.class)) {
            argValue = Long.valueOf(propertyValue);
        }

        return new ValueType(valueType, argValue);
    }

    public String getPropertyValue(String value) {

        String resultValue = value;

        if (value != null && value.startsWith("${") && value.endsWith("}")) {

            String propertyName = value.substring(2, value.length() - 1);
            String propertyValue = placeholderProperties.get(propertyName);

            if (propertyValue == null) {
                throw new IllegalStateException("PropertyName not found - " + propertyName);
            }

            resultValue = propertyValue;
        }

        return resultValue;
    }

    public Class<?> getTypeClass(String type) {

        Class<?> valueType = String.class;

        if (type != null) {

            if ("int".equals(type)) {
                valueType = int.class;
            } else if ("long".equals(type)) {
                valueType = long.class;
            } else if ("boolean".equals(type)) {
                valueType = boolean.class;
            } else if ("double".equals(type)) {
                valueType = double.class;
            } else if ("float".equals(type)) {
                valueType = float.class;
            } else {
                valueType = getClassForName(type);
            }
        }

        return valueType;
    }

    public Object createInstanceByDefaultConstructor(Class<?> beanClass) {

        try {
            return beanClass.newInstance();

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Object createInstanceByConstructorArgs(Class<?> beanClass, List<ValueType> argList) {

        List<Class<?>> types = getValueTypesTypes(argList);
        Constructor<?> constructor = findContructorForTypes(beanClass, types);

        List<Object> values = getValueTypesValues(argList);
        Object[] constructorArgs = values.toArray();

        try {
            return constructor.newInstance(constructorArgs);

        } catch (Exception e) {
            throw new IllegalStateException(String.format("Exception creating instance for class '%s' by constructor %s - Args: %s", beanClass.getName(), constructor, values), e);
        }
    }

    public Constructor findContructorForTypes(Class<?> beanClass, List<Class<?>> types) {

        Objects.requireNonNull(beanClass, "beanClass cannot be null");
        Objects.requireNonNull(types, "types cannot be null");

        Constructor<?> constructor;

        Constructor<?>[] constructors = beanClass.getDeclaredConstructors();
        if(constructors.length == 1) {
            constructor = constructors[0];
        }
        else {
            Class<?>[] parameterTypes = types.toArray(new Class<?>[types.size()]);

            try {
                constructor = beanClass.getConstructor(parameterTypes);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        return constructor;
    }

    public Class<?> getClassForName(String classType) {

        Objects.requireNonNull(classType, "ClassType cannot be null");

        try {
            return Class.forName(classType);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public Object getBeanById(String ref) {

        Objects.requireNonNull(ref, "BeanId cannot be null");
        Object refInstance = instancesById.get(ref);

        if (refInstance == null) {
            throw new IllegalStateException("Cannot find reference: " + ref);
        }

        return refInstance;
    }

    public <T> T getBeanByClass(Class<T> beanType) {

        Objects.requireNonNull(beanType, "BeanType cannot be null");
        Object beanInstance = instancesByClass.get(beanType);

        if (beanInstance == null) {
            throw new IllegalStateException("Cannot find beanType: " + beanType);
        }

        @SuppressWarnings("unchecked")
        T castedInstance = (T) beanInstance;

        return castedInstance;
    }

    public List<Object> getValueTypesValues(List<ValueType> listValues) {

        List<Object> values = new ArrayList<>();

        for (ValueType listValueType : listValues) {
            Object value = listValueType.getValue();
            values.add(value);
        }

        return values;
    }

    public List<Class<?>> getValueTypesTypes(List<ValueType> listValues) {

        List<Class<?>> types = new ArrayList<>();

        for (ValueType listValueType : listValues) {
            Class<?> type = listValueType.getType();
            types.add(type);
        }

        return types;
    }
}
