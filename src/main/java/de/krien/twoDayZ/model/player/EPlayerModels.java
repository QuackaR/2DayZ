package de.krien.twoDayZ.model.player;

import de.krien.twoDayZ.model.IGameEntityModel;

public enum EPlayerModels implements IGameEntityModel {

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
