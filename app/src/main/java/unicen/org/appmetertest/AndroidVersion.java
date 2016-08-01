package unicen.org.appmetertest;

import android.os.Build;

/**
 * Created by Mauro on 16/07/2016.
 */
public class AndroidVersion {

    public static final AndroidVersion INSTANCE = new AndroidVersion();

    private AndroidVersion(){}

    private final String baseOs = Build.VERSION.BASE_OS;
    private final String codename = Build.VERSION.CODENAME;
    private final String incremental = Build.VERSION.INCREMENTAL;
    private final String release = Build.VERSION.RELEASE;
    private final String securityPatch = Build.VERSION.SECURITY_PATCH;
    private final int sdkInt = Build.VERSION.SDK_INT;

    public String getBaseOs() {
        return baseOs;
    }

    public String getCodename() {
        return codename;
    }

    public String getIncremental() {
        return incremental;
    }

    public String getRelease() {
        return release;
    }

    public String getSecurityPatch() {
        return securityPatch;
    }

    public int getSdkInt() {
        return sdkInt;
    }
}
