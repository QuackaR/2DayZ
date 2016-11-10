package de.krien.twoDayZ.model;

import org.lwjgl.util.vector.Vector2f;

public class AEntity {

	public Vector2f position;
	public Vector2f size;
	
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
    
	
}
