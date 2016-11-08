package de.krien.twoDayZ.model.player;

import de.krien.twoDayZ.model.AGameEntity;
import de.krien.twoDayZ.util.movement.MovementUtil;
import de.krien.twoDayZ.util.position.RotationUtil;
import de.krien.twoDayZ.util.render.RenderUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class Player extends AGameEntity {

    private final int SNEAK_MOVEMENT_SPEED = 100;
    private final int WALK_MOVEMENT_SPEED = 200;
    private final int RUN_MOVEMENT_SPEED = 300;

    private PlayerMovement playerMovement;

    public Player() {
        super();
        this.speed = WALK_MOVEMENT_SPEED;
        this.texture = TextureUtil.loadGameEntityImage(EPlayerModels.DEFAULT);
        this.playerMovement = new PlayerMovement();
    }

    @Override
    public void update(float timeSinceLastGameLoop) {
        MovementUtil.moveEntity(this, timeSinceLastGameLoop);
        RotationUtil.rotateEntityToCursor(this);
        vertices = RenderUtil.createVerticesVBO(verticesBufferID, texture, position);
        textures = RenderUtil.createTextureVBO(texturesBufferID);
    }

    public void running() {
        speed = RUN_MOVEMENT_SPEED;
    }

    public void sneak() {
        speed = SNEAK_MOVEMENT_SPEED;
    }

    public void walk() {
        speed = WALK_MOVEMENT_SPEED;
    }

    public PlayerMovement getPlayerMovement() {
        return playerMovement;
    }
}
