package de.krien.twoDayZ.util.input;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;
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
//		float testDiffX = player.getSpeed() * timeSinceLastGameLoop * 5;
//		float diffX = player.getSpeed() * timeSinceLastGameLoop;
//		float testDiffY = player.getSpeed() * timeSinceLastGameLoop * 5;
//		float diffY = player.getSpeed() * timeSinceLastGameLoop;
		player.getBody().setLinearVelocity(new Vec2(0,0));

		if (player.getPlayerMovement().isMoveRight()) {
//			Vec2 testPosition = new Vec2(player.getBody().getPosition().x + testDiffX, player.getBody().getPosition().y);
//			if (!checkCollisions(player, testPosition)) {
//				Vec2 newPosition = new Vec2(player.getBody().getPosition().x + diffX, player.getBody().getPosition().y);
//				player.getBody().setTransform(newPosition, player.getRotation());
//			} else {
//				System.out.println("Collision Right");
//			}
			player.getBody().setLinearVelocity(new Vec2(player.getSpeed(), player.getBody().getLinearVelocity().y));
		}

		if (player.getPlayerMovement().isMoveLeft()) {
//			Vec2 testPosition = new Vec2(player.getBody().getPosition().x - testDiffX, player.getBody().getPosition().y);
//			if (!checkCollisions(player, testPosition)) {
//				Vec2 newPosition = new Vec2(player.getBody().getPosition().x - diffX, player.getBody().getPosition().y);
//				player.getBody().setTransform(newPosition, player.getRotation());
//			} else {
//				System.out.println("Collision Left");
//			}
			player.getBody().setLinearVelocity(new Vec2(-player.getSpeed(), player.getBody().getLinearVelocity().y));
		}

		if (player.getPlayerMovement().isMoveDown()) {
//			Vec2 testPosition = new Vec2(player.getBody().getPosition().x, player.getBody().getPosition().y + testDiffY);
//			if (!checkCollisions(player, testPosition)) {
//				diffY = player.getSpeed() * timeSinceLastGameLoop;
//				Vec2 newPosition = new Vec2(player.getBody().getPosition().x, player.getBody().getPosition().y + diffY);
//				player.getBody().setTransform(newPosition, player.getRotation());
//			} else {
//				System.out.println("Collision Down");
//			}
			player.getBody().setLinearVelocity(new Vec2(player.getBody().getLinearVelocity().x, player.getSpeed()));
		}

		if (player.getPlayerMovement().isMoveUp()) {
//			Vec2 testPosition = new Vec2(player.getBody().getPosition().x, player.getBody().getPosition().y - testDiffY);
//			if (!checkCollisions(player, testPosition)) {
//				diffY = player.getSpeed() * timeSinceLastGameLoop;
//				Vec2 newPosition = new Vec2(player.getBody().getPosition().x, player.getBody().getPosition().y - diffY);
//				player.getBody().setTransform(newPosition, player.getRotation());
//			} else {
//				System.out.println("Collision Up");
//			}
			player.getBody().setLinearVelocity(new Vec2(player.getBody().getLinearVelocity().x, -player.getSpeed()));
		}

	}

	private static boolean checkCollisions(Player player, Vec2 position) {
		Vec2 oldPosition = player.getBody().getPosition().clone();
		player.getBody().setTransform(position, player.getRotation());
		ContactEdge ce = player.getBody().getContactList();
		while (ce != null) {
			if (ce.contact.isTouching()) {
				player.getBody().setTransform(oldPosition, player.getRotation());
				return true;
			}
			ce = ce.next;
		}
		player.getBody().setTransform(oldPosition, player.getRotation());
		return false;
	}

}
