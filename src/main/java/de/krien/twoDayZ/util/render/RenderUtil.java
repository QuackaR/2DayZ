package de.krien.twoDayZ.util.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

public class RenderUtil {

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
