package org.jm2d.gl;

import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL33.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import org.jm2d.util.FileLoader;

/**
 * Wrapper around a shader to provide a uniform way of handling shaders.
 */
public class Shader {

    private final int programId, fragId, vertId;

    private final Map<String, Integer> uniforms = new HashMap<>();

    /**
     * Load a shader from files.
     * @param fileBase load vertex code from src/shader/fileBase_vert.glsl
     * and src/shader/fileBase_frag.glsl
     */
    public Shader(String fileBase) throws Exception {

        programId = glCreateProgram();
        if (programId == 0) {
            throw new Exception("Unable to create parent shader");
        }

        vertId = createShader(
                FileLoader.loadFileAsString("src/shader/" + fileBase + "_vert.glsl"), GL_VERTEX_SHADER
        );
        fragId = createShader(
                FileLoader.loadFileAsString("src/shader/" + fileBase + "_frag.glsl"), GL_FRAGMENT_SHADER
        );

        link();

    }

    /**
     * Link everything together.
     */
    private void link() throws Exception {

        glLinkProgram(programId);
        if (glGetProgrami(programId, GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking shader code: " + glGetProgramInfoLog(programId, 1024));
        }

        if (vertId != 0) {
            glDetachShader(programId, vertId);
        }
        if (fragId != 0) {
            glDetachShader(programId, fragId);
        }

        glValidateProgram(programId);
        if (glGetProgrami(programId, GL_VALIDATE_STATUS) == 0) {
            System.err.println("Warning validating Shader code: " + glGetProgramInfoLog(programId, 1024));
        }

    }

    /**
     * Create an individual shader of a given type.
     */
    private int createShader(String code, int type) throws Exception {

        int shaderId = glCreateShader(type);

        if (shaderId == 0) {
            throw new Exception("Could not create shader of type " + type);
        }

        glShaderSource(shaderId, code);
        glCompileShader(shaderId);

        if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling shader code: " + glGetShaderInfoLog(shaderId, 1024));
        }

        glAttachShader(programId, shaderId);

        return shaderId;

    }

    public void bind() {
        glUseProgram(programId);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        // This check should technically be unnecessary.
        if (programId != 0) {
            glDeleteProgram(programId);
        }
    }

    public void createUniform(String uniformName) throws Exception {
        int uniformLocation = glGetUniformLocation(programId,
                uniformName);
        if (uniformLocation < 0) {
            throw new Exception("Could not find uniform:" +
                    uniformName);
        }
        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        // Dump the matrix into a float buffer
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer fb = stack.mallocFloat(16);
            value.get(fb);
            glUniformMatrix4fv(uniforms.get(uniformName), false, fb);
        }
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(uniforms.get(uniformName), value);
    }

}
