package org.jm2d.world;

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

}
