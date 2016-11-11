package de.krien.twoDayZ.model.game.player;

import de.krien.twoDayZ.model.IEntityModel;

public enum EPlayerModels implements IEntityModel {

    DEFAULT("PLAYER_1");

    private static final String type = "player";
    private String modelID;

    EPlayerModels(String modelID) {
        this.modelID = modelID;
    }

    public String getModelID() {
        return modelID;
    }
    public String getType() {
        return type;
    }
}
