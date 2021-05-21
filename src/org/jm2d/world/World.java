package org.jm2d.world;

import java.util.ArrayList;

/**
 * A world, with blocks (more later)
 */
public final class World {

    ArrayList<Chunk> chunks = new ArrayList<>();

    public World() {

    }

    public void render() {
        for (Chunk chunk : chunks)
            chunk.render();
    }

}
