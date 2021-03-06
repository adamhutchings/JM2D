package org.jm2d.gl;

/**
 * Describes the camera position.
 */
public final class View {

    private View() {}

    private static float x = 0.1f, y = 0.1f;

    private static float blocksPerScreen = 8;

    public static void setPosition(float newX, float newY) {
        x = newX;
        y = newY;
    }

    public static float[] getPos() {
        return new float[]{x, y};
    }

    public static float getScale() {
        return blocksPerScreen;
    }

}
