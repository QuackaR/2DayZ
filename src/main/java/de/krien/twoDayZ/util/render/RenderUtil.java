package de.krien.twoDayZ.util.render;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class RenderUtil {
	
    public static void drawGameObject(Texture texture, float size, Vector2f position, float rotation, int verticesBufferID, int texturesBufferID, int verticesCount) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        Color.white.bind();
        texture.bind();
        GL11.glPushMatrix();
        {
            GL11.glScalef(size, size, size);
            GL11.glTranslatef(position.getX(), position.getY(), 0);
            GL11.glRotatef(rotation, 0.0f, 0.0f, 1.0f); //TODO rotation zu float refactorn
            GL11.glTranslatef(-position.getX(), -position.getY(), 0);

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesBufferID);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0L);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texturesBufferID);
            GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0L);

            GL11.glDrawArrays(GL11.GL_QUADS, 0, verticesCount);
        }
        GL11.glPopMatrix();
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
    }
    
    public static void drawUIObject(int verticesBufferID, int texturesBufferID, int verticesCount, Texture texture) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
        Color.white.bind();
        texture.bind();
        GL11.glPushMatrix();
        {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesBufferID);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0L);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, texturesBufferID);
            GL11.glTexCoordPointer(2, GL11.GL_FLOAT, 0, 0L);

            GL11.glDrawArrays(GL11.GL_QUADS, 0, verticesCount);
        }
        GL11.glPopMatrix();
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
    }
    
    public static void drawUIObject(int verticesBufferID, int colorBufferID, int verticesCount) {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
        Color.gray.bind();
        org.newdawn.slick.opengl.TextureImpl.bindNone();
        GL11.glPushMatrix();
        {
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, verticesBufferID);
            GL11.glVertexPointer(2, GL11.GL_FLOAT, 0, 0L);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorBufferID);
            GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, verticesCount);
        }
        GL11.glPopMatrix();
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
    }

    public static FloatBuffer createVerticesVBO(int bufferID, Texture texture, Vector2f position) {
        float[] vertexData = {
                -texture.getTextureWidth()/2 + position.getX(), 	-texture.getTextureHeight()/2 + position.getY(),
                texture.getTextureWidth()/2 + position.getX(), 		-texture.getTextureHeight()/2 + position.getY(),
                texture.getTextureWidth()/2 + position.getX(), 		texture.getTextureHeight()/2 + position.getY(),
                -texture.getTextureWidth()/2 + position.getX(), 	texture.getTextureHeight()/2 + position.getY()
        };

        FloatBuffer vertices;
        vertices = BufferUtils.createFloatBuffer(vertexData.length);
        vertices.put(vertexData);
        vertices.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return vertices;
    }
    
    /*public static FloatBuffer createVerticesVBO(int bufferID, Vector2f size, Vector2f position) {
        float[] vertexData = {
                -size.getX()/2 + position.getX(), 	-size.getY()/2 + position.getY(),
                size.getX()/2 + position.getX(), 	-size.getY()/2 + position.getY(),
                size.getX()/2 + position.getX(), 	size.getY()/2 + position.getY(),
                -size.getX()/2 + position.getX(), 	size.getY()/2 + position.getY()
        };

        FloatBuffer vertices;
        vertices = BufferUtils.createFloatBuffer(vertexData.length);
        vertices.put(vertexData);
        vertices.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return vertices;
    }*/
    
    public static FloatBuffer createVerticesVBO(int bufferID, Vector2f position, Vector2f size) {
        float[] vertexData = {
                position.getX(),                position.getY(),
                position.getX() + size.getX(),  position.getY(),
                position.getX() + size.getX(),  position.getY() + + size.getY(),
                position.getX(),                position.getY() + + size.getY()
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

    public static FloatBuffer createColorVBO(int bufferID, Color color) {
        float[] colorData = {
        		color.r,  color.g,  color.b,
        		color.r,  color.g,  color.b,
        		color.r,  color.g,  color.b,
        		color.r,  color.g,  color.b
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

    public static FloatBuffer createTextureVBO(int bufferID) {
        float[] textureData = {
                0, 0,
                1, 0,
                1, 1,
                0, 1
        };

        FloatBuffer textures;
        textures = BufferUtils.createFloatBuffer(textureData.length);
        textures.put(textureData);
        textures.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textures, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        return textures;
    }
    
    
    
}
