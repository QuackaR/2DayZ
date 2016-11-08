package de.krien.twoDayZ.model;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public interface IGameEntity {

	Texture getTexture();
	
	void update(float timeSinceLastGameLoop);
	void draw();

	Vector2f getPosition();
	void setPosition(Vector2f position);
	
	double getRotation();
	void setRotation(double rotation);
}
