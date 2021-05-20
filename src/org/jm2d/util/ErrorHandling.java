package org.jm2d.util;

public final class ErrorHandling {

    private ErrorHandling() {}

    public static void error(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }

}
