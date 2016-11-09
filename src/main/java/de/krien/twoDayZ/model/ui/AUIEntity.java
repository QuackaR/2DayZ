package de.krien.twoDayZ.model.ui;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import de.krien.twoDayZ.util.render.RenderUtil;

public class AUIEntity {

	public Vector2f position;
	public Vector2f size;
	public Texture texture;
	public Color color;

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
		this.position = position;
		this.texture = texture;
		verticesBufferID = GL15.glGenBuffers();
		textureBufferID = GL15.glGenBuffers();
	}

	public AUIEntity(Vector2f position, Vector2f size, Color color) {
		this.position = position;
		this.size = size;
		this.color = color;

		verticesBufferID = GL15.glGenBuffers();
		colorBufferID = GL15.glGenBuffers();
	}

	public void update(float timeSinceLastGameLoop) {
		if (texture != null) {
			vertices = RenderUtil.createVerticesVBO(verticesBufferID, texture, position);
			textures = RenderUtil.createTextureVBO(textureBufferID);
		} else if (color != null) {
			vertices = RenderUtil.createVerticesVBO(verticesBufferID, position, size);
			colors = RenderUtil.createColorVBO(colorBufferID, color);
		}
	}

	public void draw() {
		if (texture != null) {
			RenderUtil.drawUIObject(texture, verticesBufferID, textureBufferID, vertices.limit());
		} else if (color != null) {
			RenderUtil.drawUIObject(verticesBufferID, colorBufferID, vertices.limit());
		}
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getSize() {
		return size;
	}

	public void setSize(Vector2f size) {
		this.size = size;
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