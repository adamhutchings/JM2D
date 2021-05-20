package org.jm2d.world;

import org.jm2d.gl.Mesh;
import org.jm2d.gl.Texture;

/**
 * Represents a single block in the world.
 */
public class Block {

    private final int x, y;

    private final BlockType type;

    // For building meshes
    private static final int[] MESH_INDICES = {
            0, 1, 3, 3, 1, 2
    };

    private final float[] MESH_POSITIONS;

    private final float[] MESH_TEXCOORDS = {
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 1.0f,
            0.0f, 0.0f,
    };

    public Block(int x, int y, BlockType type) {

        this.x = x;
        this.y = y;
        this.type = type;

        MESH_POSITIONS = new float[] {
            x    , y + 1,
            x    , y    ,
            x + 1, y    ,
            x + 1, y + 1,
        };

    }

    private static Texture getTexture(BlockType t) {
        switch (t) {
            case GRASS:
                return Texture.GRASS_TEXTURE;
        }
        return null;
    }

    public void render() {
        Mesh renderMesh = new Mesh(MESH_POSITIONS, MESH_INDICES, MESH_TEXCOORDS);
        renderMesh.render(getTexture(this.type));
        renderMesh.delete();
    }

    public enum BlockType {
        GRASS, // more later
    }

}
