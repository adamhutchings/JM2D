package org.jm2d.util;

/**
 * Get types of operating systems.
 */
public final class OS {

    private OS() {}

    /**
     * OS types.
     */
    public enum OSType {
        MACOS,
        WINDOWS,
        LINUX,
        MISC,
    }

    /**
     * Cached.
     */
    private static OSType os = null;

    /**
     * Actually recalculate the type.
     */
    private static OSType getOSType() {
        String builtinOS = System.getProperty("os.name");
        if (builtinOS.startsWith("Windows")) return OSType.WINDOWS;
        if (builtinOS.startsWith("Mac"))     return OSType.MACOS;
        if (builtinOS.startsWith("Linux"))   return OSType.LINUX;
        return OSType.MISC;
    }

    /**
     * Get type of OS.
     */
    public static OSType getOS() {
        if (os == null) {
            os = getOSType();
        }
        return os;
    }

}
