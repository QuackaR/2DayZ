package de.krien.twoDayZ.util.debug;

import de.krien.twoDayZ.model.IGameEntityModel;


public enum EDebugModels implements IGameEntityModel {

    NOT_FOUND("NOT_FOUND");

    private static final String type = "debug";
    private String modelID;

    EDebugModels(String modelID) {
        this.modelID = modelID;
    }

    @Override
    public String getModelID() {
        return modelID;
    }

    @Override
    public String getType() {
        return type;
    }
}
