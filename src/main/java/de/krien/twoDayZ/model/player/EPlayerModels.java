package de.krien.twoDayZ.model.player;

public enum EPlayerModels {

    DEFAULT(1);

    private int modelID;

    EPlayerModels(int modelID) {
        this.modelID = modelID;
    }

    public int getModelID() {
        return modelID;
    }
}
