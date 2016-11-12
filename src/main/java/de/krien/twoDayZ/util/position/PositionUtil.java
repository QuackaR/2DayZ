package de.krien.twoDayZ.util.position;

import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.game.AGameEntity;

public class PositionUtil {

	public static Vector2f getCenterPosition(AGameEntity entity) {
		float centerX = entity.getPosition().getX() + (entity.getTexture().getImageWidth() / 2);
		float centerY = entity.getPosition().getY() + (entity.getTexture().getImageHeight() / 2);
		Vector2f centerPosition = new Vector2f(centerX, centerY);
		return centerPosition;
	}
	
}
