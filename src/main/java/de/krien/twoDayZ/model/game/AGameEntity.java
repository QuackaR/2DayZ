package de.krien.twoDayZ.model.game;

import java.nio.FloatBuffer;

import org.jbox2d.common.MathUtils;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

import de.krien.twoDayZ.model.AEntity;
import de.krien.twoDayZ.util.debug.EDebugModels;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class AGameEntity extends AEntity {

	private final EDebugModels DEFAULT_TEXTURE = EDebugModels.NOT_FOUND;

	private float scale;
	private float speed;
	private Texture texture;
	private Body body;

	public int verticesBufferID;
	public int texturesBufferID;
	public FloatBuffer vertices;
	public FloatBuffer textures;

	public AGameEntity() {
		verticesBufferID = GL15.glGenBuffers();
		texturesBufferID = GL15.glGenBuffers();
	}

	public void update(float timeSinceLastGameLoop) {
		vertices = RenderUtil.createVerticesVBO(verticesBufferID, getTexture(), getPosition());
		textures = RenderUtil.createTextureVBO(texturesBufferID);
	}

	public void draw() {
		RenderUtil.drawGameObject(getTexture(), getScale(), getPosition(), (float) getRotation(), verticesBufferID,
				texturesBufferID, vertices.limit());
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
		return getBody().getAngle() * MathUtils.RAD2DEG;
	}

	public void setRotation(float rotation) {
		float rotationInRadians = rotation * MathUtils.DEG2RAD;
		getBody().setTransform(getBody().getPosition(), rotationInRadians);
	}

	public Texture getTexture() {
		if (texture == null) {
			this.texture = TextureUtil.loadEntityImage(DEFAULT_TEXTURE);
		}
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	@Override
	public Vector2f getSize() {
		return new Vector2f(getTexture().getImageWidth(), getTexture().getImageHeight());
	}

	@Override
	public Vector2f getPosition() {
		return new Vector2f(getBody().getPosition().x, getBody().getPosition().y);
	}

	@Override
	public void setPosition(Vector2f position) {
		getBody().setTransform(new Vec2(position.x, position.y), 0);
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

}
