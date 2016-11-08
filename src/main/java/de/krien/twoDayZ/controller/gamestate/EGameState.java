package de.krien.twoDayZ.controller.gamestate;

import de.krien.twoDayZ.controller.gamestate.menu.Menu;
import de.krien.twoDayZ.controller.gamestate.play.Play;

public enum EGameState {

    MENU(new Menu()),
    PLAY(new Play()),
    EXIT(null);

    private IGameState reference;

    EGameState(IGameState reference) {
        this.reference = reference;
    }

    public void update() {
        reference.update();
    }

    public void draw() {
        reference.draw();
    }

    public IGameState getReference() {
        return reference;
    }
}