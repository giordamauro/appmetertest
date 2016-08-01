package unicen.org.appmetertest;

import android.os.Build;

/**
 * Created by Mauro on 16/07/2016.
 */
public final class AndroidBuild {

    public static final AndroidBuild INSTANCE = new AndroidBuild();

    private AndroidBuild() {
    }

    private final AndroidVersion version = AndroidVersion.INSTANCE;

    private final String board = Build.BOARD;
    private final String bootloader = Build.BOOTLOADER;
    private final String brand = Build.BRAND;
    private final String device = Build.DEVICE;
    private final String display = Build.DISPLAY;
    private final String fingerprint = Build.FINGERPRINT;
    private final String hardware = Build.HARDWARE;
    private final String host = Build.HOST;
    private final String id = Build.ID;
    private final String manufacturer = Build.MANUFACTURER;
    private final String model = Build.MODEL;
    private final String product = Build.PRODUCT;
    private final String serial = Build.SERIAL;
    private final String tags = Build.TAGS;
    private final String type = Build.TYPE;
    private final String unknown = Build.UNKNOWN;
    private final String user = Build.USER;

    public AndroidVersion getVersion() {
        return version;
    }

    public String getBoard() {
        return board;
    }

    public String getBootloader() {
        return bootloader;
    }

    public String getBrand() {
        return brand;
    }

    public String getDevice() {
        return device;
    }

    public String getDisplay() {
        return display;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public String getHardware() {
        return hardware;
    }

    public String getHost() {
        return host;
    }

    public String getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getProduct() {
        return product;
    }

    public String getSerial() {
        return serial;
    }

    public String getTags() {
        return tags;
    }

    public String getType() {
        return type;
    }

    public String getUnknown() {
        return unknown;
    }

    public String getUser() {
        return user;
    }
}
