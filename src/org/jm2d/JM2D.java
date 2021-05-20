package org.jm2d;

import static org.jm2d.util.ErrorHandling.error;

import org.jm2d.gl.*;
import org.jm2d.world.World;

/**
 * Main class of the game. This runs everything, and its methods are called
 */
public final class JM2D {

    private static Window wn;

    private JM2D() {}

    private static Shader shaderProgram;

    private static World world;

    /**
     * This should be set if the game should close after the current loop.
     */
    public static boolean running = true;

    public static void init() {

        Window.init();
        wn = new Window();

        Texture.createTextures();

        try {
            shaderProgram = new Shader("block");
        } catch (Exception e) {
            e.printStackTrace();
            error("Error: could not initialize shader");
        }

        try {
            shaderProgram.createUniform("texture_sampler");
            shaderProgram.createUniform("camera_pos");
            shaderProgram.createUniform("scale_factor");
        } catch (Exception e) {
            e.printStackTrace();
            error("Error: could not create uniforms");
        }

        shaderProgram.bind();

        shaderProgram.setUniform("texture_sampler", 0);

        world = new World();

    }

    public static void mainloop() {

        while (wn.open() && running) {
            shaderProgram.setUniform("camera_pos", View.getPos());
            shaderProgram.setUniform("scale_factor", View.getScale());
            wn.clear();
            world.render();
            wn.repaint();
        }

    }

    public static void exit() {
        wn.delete();
        shaderProgram.cleanup();
        Window.exit();
    }

}
