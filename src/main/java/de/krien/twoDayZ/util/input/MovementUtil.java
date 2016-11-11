package de.krien.twoDayZ.util.input;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Keyboard;

import de.krien.twoDayZ.model.game.player.Player;

public class MovementUtil {

	public static void movePlayer(Player player, float timeSinceLastGameLoop) {
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
			float diffX = player.getSpeed() * timeSinceLastGameLoop;
			Vec2 newPosition = new Vec2(player.getBody().getPosition().x + diffX, player.getBody().getPosition().y);
			player.getBody().setTransform(newPosition, 0);
		}

		if (player.getPlayerMovement().isMoveLeft()) {
			float diffX = player.getSpeed() * timeSinceLastGameLoop;
			Vec2 newPosition = new Vec2(player.getBody().getPosition().x - diffX, player.getBody().getPosition().y);
			player.getBody().setTransform(newPosition, 0);
		}

		if (player.getPlayerMovement().isMoveDown()) {
			float diffY = player.getSpeed() * timeSinceLastGameLoop;
			Vec2 newPosition = new Vec2(player.getBody().getPosition().x, player.getBody().getPosition().y + diffY);
			player.getBody().setTransform(newPosition, 0);
		}

		if (player.getPlayerMovement().isMoveUp()) {
			float diffY = player.getSpeed() * timeSinceLastGameLoop;
			Vec2 newPosition = new Vec2(player.getBody().getPosition().x, player.getBody().getPosition().y - diffY);
			player.getBody().setTransform(newPosition, 0);
		}
	}

}
