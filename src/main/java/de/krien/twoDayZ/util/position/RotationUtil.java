package de.krien.twoDayZ.util.position;

import de.krien.twoDayZ.model.AGameEntity;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

public class RotationUtil {
	
	public static void rotateEntityToOtherEntity(AGameEntity rotateEntity, AGameEntity destinationEntity) {
		double degree = getDirectionInDegree(
				PositionUtil.getCenterPosition(rotateEntity), 
				PositionUtil.getCenterPosition(destinationEntity)
		);
		rotateEntity.setRotation((float)degree);
	}

	public static void rotateEntityToCursor(AGameEntity entity) {
		Vector2f playerCenterPosition = entity.getPosition();//PositionUtil.getCenterPosition(entity);
		Vector2f cursorPosition = new Vector2f(Mouse.getX(), (Display.getHeight() - Mouse.getY()));
		double degree = getDirectionInDegree(playerCenterPosition, cursorPosition);
		entity.setRotation((float)degree);
		// Temp
		Color.red.bind();
		GL11.glBegin(GL11.GL_LINE_STRIP);
		GL11.glVertex2f(playerCenterPosition.getX(),playerCenterPosition.getY());
		GL11.glVertex2f(cursorPosition.getX(),cursorPosition.getY());
		GL11.glEnd();
		//
	}

	private static double getDirectionInDegree(Vector2f playerCenterPosition, Vector2f mousePosition) {
		double radians = getDirectionInRadians(playerCenterPosition, mousePosition);
		double degree = (360 + ((radians * (180 / Math.PI) * -1) + 90)) % 360;
		return degree;
	}

	private static double getDirectionInRadians(Vector2f playerCenterPosition, Vector2f mousePosition) {
		double radians = Math.atan2(mousePosition.getX() - playerCenterPosition.getX(),
				mousePosition.getY() - playerCenterPosition.getY());
		return radians;
	}

}
