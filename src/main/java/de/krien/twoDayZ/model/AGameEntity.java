package de.krien.twoDayZ.model;

import de.krien.twoDayZ.util.debug.EDebugModels;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

public class AGameEntity {

    private final Vector2f DEFAULT_POSITION = new Vector2f(0, 0);
    private final float DEFAULT_SIZE = 1.0f;
    private final float DEFAULT_SPEED = 0.0f;
    private final float DEFAULT_ROTATION = 0.0f;
    private final EDebugModels DEFAULT_TEXTURE = EDebugModels.NOT_FOUND;

    public Vector2f position;
    public float size;
    public float speed;
    public float rotation;
    public Texture texture;

    public int verticesBufferID;
    public int texturesBufferID;
    public FloatBuffer vertices;
    public FloatBuffer textures;

    public AGameEntity() {
        this.position = DEFAULT_POSITION;
        this.size = DEFAULT_SIZE;
        this.speed = DEFAULT_SPEED;
        this.rotation = DEFAULT_ROTATION;

        verticesBufferID = GL15.glGenBuffers();
        texturesBufferID = GL15.glGenBuffers();
    }

    public void update(float timeSinceLastGameLoop) {
        if(texture == null) { // TODO Performance?
            this.texture = TextureUtil.loadGameEntityImage(DEFAULT_TEXTURE);
        }
        vertices = RenderUtil.createVerticesVBO(verticesBufferID, texture, position);
        textures = RenderUtil.createTextureVBO(texturesBufferID);
    }

    public void draw() {
        RenderUtil.drawGameObject(
                texture,
                size,
                position,
                (float) rotation,
                verticesBufferID,
                texturesBufferID,
                vertices.limit());
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
