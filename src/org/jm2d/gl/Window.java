package org.jm2d.gl;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33.*;
import static org.lwjgl.system.MemoryUtil.*;

import org.jm2d.util.OS;
import org.lwjgl.opengl.GL;

/**
 * Window wrapper class around GLFW code.
 */
public class Window {

    /**
     * GLFW window handle
     */
    private long handle;
    private int size;

    /**
     * Create a window and everything.
     */
    public Window() {

        size = (int) ( glfwGetVideoMode(glfwGetPrimaryMonitor()).height() * 0.75f );

        handle = glfwCreateWindow(size, size, "Minecraft", NULL, NULL);
        glfwShowWindow(handle);

        glfwMakeContextCurrent(handle);
        GL.createCapabilities();

        // Sometimes the OS doesn't want to give attention to the window.
        glfwRequestWindowAttention(handle);

    }

    /**
     * GLFW initialization
     */
    public static void init() {

        // This is necessary for fonts to work on macOS
        if (OS.getOS() == OS.OSType.MACOS) {
            System.setProperty("java.awt.headless", "true");
        }

        glfwInit(); // TODO - check for error

        // macOS needs this. For some reason.
        if (OS.getOS() == OS.OSType.MACOS) {
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        }

    }

    /**
     * Window destruction
     */
    public void delete() {
        glfwDestroyWindow(handle);
    }

    /**
     * GLFW non-initialization
     */
    public static void exit() {

        glfwTerminate();

    }

}
