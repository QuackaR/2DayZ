package de.krien.twoDayZ.model.ui;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import de.krien.twoDayZ.model.AEntity;
import de.krien.twoDayZ.util.render.RenderUtil;

public class AUIEntity extends AEntity {

	private Texture texture;
	private Color color;

	public int verticesBufferID;
	public int textureBufferID;
	public int colorBufferID;
	public FloatBuffer vertices;
	public FloatBuffer textures;
	public FloatBuffer colors;

	public AUIEntity() {
		verticesBufferID = GL15.glGenBuffers();
		textureBufferID = GL15.glGenBuffers();
		colorBufferID = GL15.glGenBuffers();
	}

	public AUIEntity(Vector2f position, Texture texture) {
		setPosition(position);
		setTexture(texture);
		verticesBufferID = GL15.glGenBuffers();
		textureBufferID = GL15.glGenBuffers();
	}

	public AUIEntity(Vector2f position, Vector2f size, Color color) {
		setPosition(position);
		setSize(size);
		setColor(color);

		verticesBufferID = GL15.glGenBuffers();
		colorBufferID = GL15.glGenBuffers();
	}

	public void update(float timeSinceLastGameLoop) {
		if (texture != null) {
			vertices = RenderUtil.createVerticesVBO(verticesBufferID, getTexture(), getPosition());
			textures = RenderUtil.createTextureVBO(textureBufferID);
		} else if (color != null) {
			vertices = RenderUtil.createVerticesVBO(verticesBufferID, getPosition(), getSize());
			colors = RenderUtil.createColorVBO(colorBufferID, getColor());
		}
	}

	public void draw() {
		if (texture != null) {
			RenderUtil.drawUIObject(verticesBufferID, textureBufferID, vertices.limit(), getTexture());
		} else if (color != null) {
			RenderUtil.drawUIObject(verticesBufferID, colorBufferID, vertices.limit());
		}
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
