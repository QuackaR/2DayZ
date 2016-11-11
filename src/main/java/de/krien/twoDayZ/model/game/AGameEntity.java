package de.krien.twoDayZ.model.game;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import de.krien.twoDayZ.model.AEntity;
import de.krien.twoDayZ.util.debug.EDebugModels;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class AGameEntity extends AEntity{

    private final Vector2f DEFAULT_POSITION = new Vector2f(0, 0);
    private final float DEFAULT_SCALE = 1.0f;
    private final float DEFAULT_SPEED = 0.0f;
    private final float DEFAULT_ROTATION = 0.0f;
    private final EDebugModels DEFAULT_TEXTURE = EDebugModels.NOT_FOUND;

    public float scale;
    public float speed;
    public float rotation;
    public Texture texture;

    public int verticesBufferID;
    public int texturesBufferID;
    public FloatBuffer vertices;
    public FloatBuffer textures;

    public AGameEntity() {
        this.position = DEFAULT_POSITION;
        this.scale = DEFAULT_SCALE;
        this.speed = DEFAULT_SPEED;
        this.rotation = DEFAULT_ROTATION;

        verticesBufferID = GL15.glGenBuffers();
        texturesBufferID = GL15.glGenBuffers();
    }

    public void update(float timeSinceLastGameLoop) {
        if(texture == null) { // TODO Performance?
            this.texture = TextureUtil.loadEntityImage(DEFAULT_TEXTURE);
        }
        vertices = RenderUtil.createVerticesVBO(verticesBufferID, texture, position);
        textures = RenderUtil.createTextureVBO(texturesBufferID);
    }

    public void draw() {
        RenderUtil.drawGameObject(
                texture,
                scale,
                position,
                (float) rotation,
                verticesBufferID,
                texturesBufferID,
                vertices.limit());
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
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
    
    @Override
    public Vector2f getSize() {
    	return new Vector2f(texture.getImageWidth(), texture.getImageHeight());
    }
}
