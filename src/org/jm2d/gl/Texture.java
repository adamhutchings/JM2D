package org.jm2d.gl;

import static org.jm2d.util.ErrorHandling.error;
import static org.lwjgl.opengl.GL33.*;

import java.io.FileInputStream;
import java.nio.ByteBuffer;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

/**
 * A texture we can use.
 * @author adamhutchings
 */
public class Texture {

    private final int texId;

    public Texture(String fileName) throws Exception {

        PNGDecoder decoder = new PNGDecoder(new FileInputStream(fileName));
        ByteBuffer buf = ByteBuffer.allocateDirect(
                4 * decoder.getWidth() * decoder.getHeight()
        );
        decoder.decode(buf, decoder.getWidth() * 4, Format.RGBA);
        buf.flip();

        texId = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texId);

        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, decoder.getWidth(),
                decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buf
        );

        glGenerateMipmap(GL_TEXTURE_2D);

    }

    public int getID() { return texId; }

    public static Texture
            DIRT_TEXTURE,
            GRASS_TEXTURE,
            STONE_TEXTURE;

    public static void createTextures() {
        try {
            DIRT_TEXTURE = new Texture("res/dirt.png");
            GRASS_TEXTURE = new Texture("res/grass.png");
            STONE_TEXTURE = new Texture("res/stone.png");
        } catch (Exception e) {
            e.printStackTrace();
            error("Error: could not load textures");
        }
    }

}
