package org.jm2d.world;

import javax.swing.text.DefaultTextUI;

import static org.jm2d.world.Block.BlockType;

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
    private static Block.BlockType[][] BLANK_CHUNK, DEFAULT_CHUNK;

    /**
     * Position.
     */
    public final int xPos, yPos;

    public static void initConstants() {
        BLANK_CHUNK = new Block.BlockType[CHUNK_WIDTH][CHUNK_HEIGHT];
        DEFAULT_CHUNK = new Block.BlockType[CHUNK_WIDTH][CHUNK_HEIGHT];
        for (int i = 0; i < BLANK_CHUNK.length; ++i) {
            for (int j = 0; j < BLANK_CHUNK[0].length; ++j) {
                BLANK_CHUNK[i][j] = BlockType.AIR;
            }
        }
        for (int i = 0; i < DEFAULT_CHUNK.length; ++i) {
            for (int j = 0; j < DEFAULT_CHUNK[0].length; ++j) {
                Block.BlockType type;
                if (CHUNK_HEIGHT - j < 4) {
                    type = BlockType.AIR;
                } else if (CHUNK_WIDTH - j == 4) {
                    type = BlockType.GRASS;
                } else if (CHUNK_WIDTH - j < 8) {
                    type = BlockType.DIRT;
                } else {
                    type = BlockType.STONE;
                }
                System.out.println(type);
                DEFAULT_CHUNK[i][j] = type;
            }
        }
    }

    /**
     * Create a new chunk.
     * @param xLoc which chunk this is. xLoc 0 means x-coordinates 0-15.
     * @param yLoc vertical chunk. yLoc 2 means y-coords 32-47.
     */
    public Chunk(int xLoc, int yLoc) {
        this(xLoc, yLoc, DEFAULT_CHUNK);
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
