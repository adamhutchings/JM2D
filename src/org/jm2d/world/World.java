package org.jm2d.world;

import java.util.ArrayList;

/**
 * A world, with blocks (more later)
 */
public final class World {

    ArrayList<Block> blocks = new ArrayList<>();

    public World() {
        blocks.add(new Block(0, 0, Block.BlockType.GRASS));
        blocks.add(new Block(1, 0, Block.BlockType.DIRT));
        blocks.add(new Block(2, 0, Block.BlockType.STONE));
    }

    public void render() {
        for (Block block : blocks)
            block.render();
    }

}
