package org.jm2d.world;

import org.lwjgl.opengl.INTELBlackholeRender;

import static org.jm2d.world.Block.BlockType.*;

/**
 * A "chunk" of blocks in the world. These are loaded and saved as units.
 */
public class Chunk {

    public static final int CHUNK_WIDTH  = 16;
    public static final int CHUNK_HEIGHT = 16;

    private Block[][] blocks = new Block[CHUNK_WIDTH][CHUNK_HEIGHT];

    /**
     * For creating new chunks.
     */
    private static final Block.BlockType[][] BLANK_CHUNK;

    /**
     * Position.
     */
    public final int xPos, yPos;

    static {
        BLANK_CHUNK = new Block.BlockType[CHUNK_WIDTH][CHUNK_HEIGHT];
        for (Block.BlockType[] row : BLANK_CHUNK)
            for (Block.BlockType type : row)
                type = AIR;
    }

    /**
     * Create a new chunk.
     * @param xLoc which chunk this is. xLoc 0 means x-coordinates 0-15.
     * @param yLoc vertical chunk. yLoc 2 means y-coords 32-47.
     */
    public Chunk(int xLoc, int yLoc) {
        this(xLoc, yLoc, BLANK_CHUNK);
    }

    public Chunk(int xLoc, int yLoc, Block.BlockType[][] types) {
        for (int x = 0; x < CHUNK_WIDTH; ++x) {
            for (int y = 0; y < CHUNK_WIDTH; ++y) {
                blocks[x][y] = new Block(xLoc * 16 + x, yLoc * 16 + y, types[x][y]);
            }
        }
        this.xPos = xLoc;
        this.yPos = yLoc;
    }

    public void render() {
        for (Block[] row : blocks) {
            for (Block block : row) {
                block.render();
            }
        }
    }

    public void setBlock(int x, int y, Block.BlockType type) {
        blocks[x][y] = new Block(xPos * 16 + x, yPos * 16 + x, type);
    }

}
