package org.jm2d;

import org.jm2d.gl.Window;

/**
 * Main class of the game. This runs everything, and its methods are called
 */
public final class JM2D {

    private static Window wn;

    private JM2D() {}

    public static void init() {
        Window.init();
        wn = new Window();
    }

    public static void mainloop() {

    }

    public static void exit() {
        wn.delete();
        Window.exit();
    }

}
