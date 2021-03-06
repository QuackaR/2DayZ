package de.krien.twoDayZ.util.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.game.AGameEntity;

public class PhysicsUtil {

	private static final float SCALE_FACTOR = 0.5f;

	private static final Vector2f DEFAULT_POSITION = new Vector2f(0, 0);

	private static World world = new World(new Vec2(0, 0));

	public static void update(float timeSinceLastGameLoop) {
		world.step(timeSinceLastGameLoop, 6, 2);
		Body body = world.getBodyList();

		while (body != null) {
			AGameEntity entity = (AGameEntity) body.getUserData();
			if (entity != null) {
				entity.setPosition(new Vector2f(body.getPosition().x, body.getPosition().y));
			}
			body = body.getNext();
		}
	}

	public static Body initCircleBody(AGameEntity entity) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position.set(DEFAULT_POSITION.x, DEFAULT_POSITION.y);

		CircleShape shape = new CircleShape();
		float radius = entity.getTexture().getImageWidth() > entity.getTexture().getImageHeight() ? entity.getTexture().getImageWidth() : entity.getTexture().getImageHeight();
		shape.m_radius = radius * SCALE_FACTOR;

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = 0.5f;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;

		Body body = world.createBody(bd);
		body.createFixture(fd);
		body.setUserData(entity);
		return (body);
	}

	public static Body initStationaryRectangluarBody(AGameEntity entity) {
		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.position.set(DEFAULT_POSITION.x, DEFAULT_POSITION.y);

		System.out.println("x: " + entity.getTexture().getImageWidth() + " / y: " + entity.getTexture().getImageHeight());
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(entity.getTexture().getImageWidth()*SCALE_FACTOR, entity.getTexture().getImageHeight()*SCALE_FACTOR);

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = 0.5f;
		fd.friction = 0.3f;
		fd.restitution = 0.5f;

		Body body = world.createBody(bd);
		body.createFixture(fd);
		body.setUserData(entity);
		return (body);
	}

	public static World getWorld() {
		return world;
	}

}
