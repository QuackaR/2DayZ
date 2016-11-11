package de.krien.twoDayZ.model.ui;

import de.krien.twoDayZ.model.IEntityModel;

public enum EUIModel implements IEntityModel {

    MENU("MENU");

    private static final String type = "ui";
    private String modelID;

    EUIModel(String modelID) {
        this.modelID = modelID;
    }

    public String getModelID() {
        return modelID;
    }
    public String getType() {
        return type;
    }
	
}
