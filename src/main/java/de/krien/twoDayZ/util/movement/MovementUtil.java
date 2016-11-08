package de.krien.twoDayZ.util.movement;

import de.krien.twoDayZ.model.player.Player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

public class MovementUtil {

	public static void moveEntity(Player player, float timeSinceLastGameLoop) {
		checkMovement(player);
		doMovement(player, timeSinceLastGameLoop);
	}

	private static void checkMovement(Player player) {
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.getPlayerMovement().setMoveRight(true);
		} else {
			player.getPlayerMovement().setMoveRight(false);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.getPlayerMovement().setMoveLeft(true);
		} else {
			player.getPlayerMovement().setMoveLeft(false);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.getPlayerMovement().setMoveDown(true);
		} else {
			player.getPlayerMovement().setMoveDown(false);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.getPlayerMovement().setMoveUp(true);
		} else {
			player.getPlayerMovement().setMoveUp(false);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			player.sneak();
		} else if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			player.running();
		} else {
			player.walk();
		}
	}

	private static void doMovement(Player player, float timeSinceLastGameLoop) {
		if (player.getPlayerMovement().isMoveRight()) {
			float diffX = player.getMovementSpeed() * timeSinceLastGameLoop;
			Vector2f newPosition = new Vector2f(player.getPosition().getX() + diffX, player.getPosition().getY());
			player.setPosition(newPosition);
		}

		if (player.getPlayerMovement().isMoveLeft()) {
			float diffX = player.getMovementSpeed() * timeSinceLastGameLoop;
			Vector2f newPosition = new Vector2f(player.getPosition().getX() - diffX, player.getPosition().getY());
			player.setPosition(newPosition);
		}

		if (player.getPlayerMovement().isMoveDown()) {
			float diffY = player.getMovementSpeed() * timeSinceLastGameLoop;
			Vector2f newPosition = new Vector2f(player.getPosition().getX(), player.getPosition().getY() + diffY);
			player.setPosition(newPosition);
		}

		if (player.getPlayerMovement().isMoveUp()) {
			float diffY = player.getMovementSpeed() * timeSinceLastGameLoop;
			Vector2f newPosition = new Vector2f(player.getPosition().getX(), player.getPosition().getY() - diffY);
			player.setPosition(newPosition);
		}
	}

}
