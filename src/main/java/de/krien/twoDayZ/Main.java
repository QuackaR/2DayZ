package de.krien.twoDayZ;

import de.krien.twoDayZ.controller.Game;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }

}
