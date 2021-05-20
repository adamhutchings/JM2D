package org.jm2d;

import static org.jm2d.util.ErrorHandling.error;

import org.jm2d.gl.*;

/**
 * Main class of the game. This runs everything, and its methods are called
 */
public final class JM2D {

    private static Window wn;

    private JM2D() {}

    private static Shader shaderProgram;
    private static Mesh mesh;

    private static Texture tex;

    /**
     * This should be set if the game should close after the current loop.
     */
    public static boolean running = true;

    public static void init() {

        Window.init();
        wn = new Window();

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

        try {
            tex = new Texture("res/grass.png");
        } catch (Exception e) {
            error("Error: could not load texture");
        }

    }

    public static void mainloop() {

        int[] indices = new int[] {
                0, 1, 3, 3, 1, 2,
        };

        float[] positions = new float[] {
                -0.5f,  0.5f,
                -0.5f, -0.5f,
                0.5f, -0.5f,
                0.5f,  0.5f,
        };

        float[] texCoords = new float[] {
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
                0.0f, 0.0f,
        };

        while (wn.open() && running) {
            shaderProgram.setUniform("camera_pos", View.getPos());
            shaderProgram.setUniform("scale_factor", View.getScale());
            if (mesh != null) mesh.delete();
            mesh = new Mesh(positions, indices, texCoords);
            wn.clear();
            mesh.render(tex);
            wn.repaint();
        }

    }

    public static void exit() {
        wn.delete();
        Window.exit();
    }

}
