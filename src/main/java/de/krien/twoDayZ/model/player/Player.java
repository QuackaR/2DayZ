package de.krien.twoDayZ.model.player;

import de.krien.twoDayZ.model.IGameEntity;
import de.krien.twoDayZ.util.movement.MovementUtil;
import de.krien.twoDayZ.util.position.RotationUtil;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import java.nio.FloatBuffer;

public class Player implements IGameEntity {

	private final int DEFAULT_POSITION = 250; // Temporary
	private final int DEFAULT_SIZE = 1;
	private final int SNEAK_MOVEMENT_SPEED = 100;
	private final int WALK_MOVEMENT_SPEED = 200;
	private final int RUN_MOVEMENT_SPEED = 300;
	private final double DEFAULT_ROTATION = 0;

	private Vector2f position;
	private float size;
	private int movementSpeed;
	private double rotation;

	private Texture texture;
	private PlayerMovement playerMovement;

	private int verticesBufferID;
	private int texturesBufferID;
	private FloatBuffer vertices;
	private FloatBuffer textures;

	public Player() {
		super();
		this.position = new Vector2f(DEFAULT_POSITION, DEFAULT_POSITION);
		this.size = DEFAULT_SIZE;
		this.movementSpeed = WALK_MOVEMENT_SPEED;
		this.rotation = DEFAULT_ROTATION;
		this.texture = TextureUtil.loadGameEntityImage(EPlayerModels.DEFAULT);
		this.playerMovement = new PlayerMovement();

		verticesBufferID = GL15.glGenBuffers();
		texturesBufferID = GL15.glGenBuffers();
	}

	public Player(Vector2f position, float size, int movementSpeed, double rotation, Texture texture) {
		super();
		this.position = position;
		this.size = size;
		this.movementSpeed = movementSpeed;
		this.rotation = rotation;
		this.texture = texture;
		verticesBufferID = GL15.glGenBuffers();
		texturesBufferID = GL15.glGenBuffers();
	}

	@Override
	public void update(float timeSinceLastGameLoop) {
		MovementUtil.moveEntity(this, timeSinceLastGameLoop);
		RotationUtil.rotateEntityToCursor(this);
		vertices = RenderUtil.createVerticesVBO(verticesBufferID, texture, position);
		textures = RenderUtil.createTextureVBO(texturesBufferID);
	}

	@Override
	public void draw() {
		RenderUtil.drawGameObject(
				texture,
				size,
				position,
				(float)rotation,
				verticesBufferID,
				texturesBufferID,
				vertices.limit());
	}

	public void running() {
		movementSpeed = RUN_MOVEMENT_SPEED;
	}

	public void sneak() {
		movementSpeed = SNEAK_MOVEMENT_SPEED;
	}

	public void walk() {
		movementSpeed = WALK_MOVEMENT_SPEED;
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

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public PlayerMovement getPlayerMovement() {
		return playerMovement;
	}

	public void setPlayerMovement(PlayerMovement playerMovement) {
		this.playerMovement = playerMovement;
	}
}
