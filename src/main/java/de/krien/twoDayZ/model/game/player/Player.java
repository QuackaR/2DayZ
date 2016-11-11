package de.krien.twoDayZ.model.game.player;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.ContactEdge;

import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.util.input.MouseUtil;
import de.krien.twoDayZ.util.input.MovementUtil;
import de.krien.twoDayZ.util.physics.PhysicsUtil;
import de.krien.twoDayZ.util.position.RotationUtil;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class Player extends AGameEntity {

	private final float DEFAULT_SCALE = 1.0f;
	
    private final int SNEAK_MOVEMENT_SPEED = 100;
    private final int WALK_MOVEMENT_SPEED = 200;
    private final int RUN_MOVEMENT_SPEED = 300;

    private PlayerMovement playerMovement;

    public Player() {
        super();
       	setBody(PhysicsUtil.initCircleBody(this));
		setScale(DEFAULT_SCALE);
        setSpeed(WALK_MOVEMENT_SPEED);
       	setTexture(TextureUtil.loadEntityImage(EPlayerModels.DEFAULT));
        this.playerMovement = new PlayerMovement();
    }

    @Override
    public void update(float timeSinceLastGameLoop) {
    	if(!checkCollisions()) {
            MovementUtil.movePlayer(this, timeSinceLastGameLoop);
    	}
        RotationUtil.rotateEntityToCursor(this);
        MouseUtil.checkMouseActions();
        vertices = RenderUtil.createVerticesVBO(verticesBufferID, getTexture(), getPosition());
        textures = RenderUtil.createTextureVBO(texturesBufferID);
    }
    
    private boolean checkCollisions() {
        getBody().setTransform(new Vec2(getPosition().x, getPosition().y), getRotation());
		ContactEdge ce = getBody().getContactList();
		while(ce != null){
			if(ce.contact.isTouching()) {
				System.out.println("Collision");
				return true;
			}
			ce = ce.next;
		}
		System.out.println("Keine Collision");
		return false;
    }

    public void running() {
        setSpeed(RUN_MOVEMENT_SPEED);
    }

    public void sneak() {
    	setSpeed(SNEAK_MOVEMENT_SPEED);
    }

    public void walk() {
    	setSpeed(WALK_MOVEMENT_SPEED);
    }

    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }
}
