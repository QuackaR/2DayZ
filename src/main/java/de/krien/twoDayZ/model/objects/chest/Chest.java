package de.krien.twoDayZ.model.objects.chest;

import de.krien.twoDayZ.model.IGameEntity;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

public class Chest implements IGameEntity {

    private final int DEFAULT_POSITION = 500; // Temporary
    private final double DEFAULT_ROTATION = 0;

    private Vector2f position;
    private double rotation;

    private Texture texture;

    private int verticesBufferID;
    private int texturesBufferID;
    private FloatBuffer vertices;
    private FloatBuffer textures;

    public Chest() {
        super();
        this.position = new Vector2f(DEFAULT_POSITION, DEFAULT_POSITION);
        this.rotation = DEFAULT_ROTATION;
        this.texture = TextureUtil.loadGameEntityImage(EChestModels.MILITARY_CHEST_01);
        verticesBufferID = GL15.glGenBuffers();
        texturesBufferID = GL15.glGenBuffers();
    }

    public Chest(Vector2f position, double rotation, Texture texture) {
        super();
        this.position = position;
        this.rotation = rotation;
        this.texture = texture;
        verticesBufferID = GL15.glGenBuffers();
        texturesBufferID = GL15.glGenBuffers();
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void update(float timeSinceLastGameLoop) {
        vertices = RenderUtil.createVerticesVBO(verticesBufferID, texture, position);
        textures = RenderUtil.createTextureVBO(texturesBufferID);
    }

    @Override
    public void draw() {
        RenderUtil.drawGameObject(
                texture,
                1,
                position,
                (float)rotation,
                verticesBufferID,
                texturesBufferID,
                vertices.limit());
    }

    @Override
    public Vector2f getPosition() {
        return position;
    }

    @Override
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}
