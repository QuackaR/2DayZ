package de.krien.twoDayZ.controller.gamestate.menu.mainMenu;

public enum EMainMenuEntry {

    CONNECT("Connect"),
    SETTINGS("Settings"),
    EXIT("Exit");

    private String text;
    private int position;

    EMainMenuEntry(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getPosition() {
        for(int i = 0; i < EMainMenuEntry.values().length; i++) {
            if(EMainMenuEntry.values()[i] == this) {
                return i + 1;
            }
        }
        return 0;
    }

    public EMainMenuEntry getNext() {
        int position = getPosition();
        for(EMainMenuEntry menuEntry : EMainMenuEntry.values()) {
            if (menuEntry.getPosition() == position + 1) {
                return menuEntry;
            }
        }
        for(EMainMenuEntry menuEntry : EMainMenuEntry.values()) {
            if (menuEntry.getPosition() == 1) {
                return menuEntry;
            }
        }
        return null;
    }

    public EMainMenuEntry getLast() {
        int position = getPosition();
        for(EMainMenuEntry menuEntry : EMainMenuEntry.values()) {
            if (menuEntry.getPosition() == position - 1) {
                return menuEntry;
            }
        }
        for(EMainMenuEntry menuEntry : EMainMenuEntry.values()) {
            if (menuEntry.getPosition() == EMainMenuEntry.values().length) {
                return menuEntry;
            }
        }
        return null;
    }
}
