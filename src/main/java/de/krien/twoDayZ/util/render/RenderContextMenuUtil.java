package de.krien.twoDayZ.util.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import java.nio.FloatBuffer;

public class RenderContextMenuUtil {

    private final static float MENU_WIDTH = 100.0f;
    private final static float MENU_HEIGHT = 25.0f;

    public static void drawContextMenu(int verticesBufferID, int texturesBufferID, int verticesCount) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
        GL11.glPushMatrix();
        {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesBufferID);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0L);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texturesBufferID);
            GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, verticesCount);
        }
        GL11.glPopMatrix();
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
    }

    public static FloatBuffer createVerticesVBO(int bufferID, Vector2f position) {
        float[] vertexData = {
                position.getX(),                position.getY(),
                position.getX() + MENU_WIDTH,   position.getY(),
                position.getX() + MENU_WIDTH,   position.getY() + MENU_HEIGHT,
                position.getX(),                position.getY() + MENU_HEIGHT
        };

        FloatBuffer contextMenuVBO;
        contextMenuVBO = BufferUtils.createFloatBuffer(vertexData.length);
        contextMenuVBO.put(vertexData);
        contextMenuVBO.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, contextMenuVBO, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return contextMenuVBO;
    }

    public static FloatBuffer createColorVBO(int bufferID) {
        float[] colorData = {
                Color.gray.r,  Color.gray.g,  Color.gray.b,
                Color.gray.r,  Color.gray.g,  Color.gray.b,
                Color.gray.r,  Color.gray.g,  Color.gray.b,
                Color.gray.r,  Color.gray.g,  Color.gray.b
        };

        FloatBuffer colors;
        colors = BufferUtils.createFloatBuffer(colorData.length);
        colors.put(colorData);
        colors.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, colors, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return colors;
    }
}
