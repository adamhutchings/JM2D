package org.jm2d.world;

import static org.jm2d.world.Block.BlockType;

import java.util.ArrayList;

/**
 * A world, with blocks (more later)
 */
public final class World {

    ArrayList<Chunk> chunks = new ArrayList<>();

    public World() {
        chunks.add(new Chunk(0, 0));
    }

    public void render() {
        for (Chunk chunk : chunks)
            chunk.render();
    }

    /**
     * Set a block. Don't do anything but cleanly replace one block.
     */
    public void setBlock(int xPos, int yPos, BlockType type) {
        for (Chunk chunk : chunks) {
            if ( (xPos / 16 == chunk.xPos) && (yPos / 16 == chunk.yPos) ) {
                chunk.setBlock(xPos % 16, yPos % 16, type);
            }
        }
    }

}
