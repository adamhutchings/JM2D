package org.jm2d.gl;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL33.*;

/**
 * A mesh with texture coordinates, position coordinates, etc.
 */
public final class Mesh {

    private final int vaoId,
        posVboId, idxVboId, texVboId,
        vertexCount
    ;

    /**
     * Set up all of the arrays into buffers and such.
     */
    public Mesh(float[] positions, int[] indices, float[] texPositions) {

        // The buffers we'll place the data in
        FloatBuffer verticesBuffer, texBuffer;
        IntBuffer indexBuffer;

        vertexCount = indices.length;

        vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        // Vertices
        posVboId = glGenBuffers();
        verticesBuffer = MemoryUtil.memAllocFloat(positions.length);
        verticesBuffer.put(positions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, posVboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        MemoryUtil.memFree(verticesBuffer);

        // Indices
        idxVboId = glGenBuffers();
        indexBuffer = MemoryUtil.memAllocInt(indices.length);
        indexBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
        MemoryUtil.memFree(indexBuffer);

        // Texture coordinates
        texVboId = glGenBuffers();
        texBuffer = MemoryUtil.memAllocFloat(texPositions.length);
        texBuffer.put(texPositions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, texVboId);
        glBufferData(GL_ARRAY_BUFFER, texBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

        // We're not placing data into the vao anymore
        glBindVertexArray(0);

    }

    public void render(Texture t) {

        // Activate first texture unit
        glActiveTexture(GL_TEXTURE0);
        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, t.getID());

        // Draw the mesh
        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        // Clear everything for next mesh
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);

    }

    public void delete() {

        glDisableVertexAttribArray(0);

        // Clear the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Flush all data
        glDeleteBuffers(posVboId);
        glDeleteBuffers(idxVboId);
        glDeleteBuffers(texVboId);

        // Clear the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(vaoId);

    }

}
