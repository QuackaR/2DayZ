package de.krien.twoDayZ.util.position;

import de.krien.twoDayZ.model.AGameEntity;
import org.lwjgl.util.vector.Vector2f;

public class PositionUtil {

	public static Vector2f getCenterPosition(AGameEntity entity) {
		float centerX = entity.getPosition().getX() + (entity.getTexture().getWidth() / 2);
		float centerY = entity.getPosition().getY() + (entity.getTexture().getHeight() / 2);
		Vector2f centerPosition = new Vector2f(centerX, centerY);
		return centerPosition;
	}
	
}
