package de.krien.twoDayZ.model.game.player;

import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.util.input.MouseUtil;
import de.krien.twoDayZ.util.input.MovementUtil;
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
        MovementUtil.movePlayer(this, timeSinceLastGameLoop);
        RotationUtil.rotateEntityToCursor(this);
        MouseUtil.checkMouseActions();
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
