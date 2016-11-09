package de.krien.twoDayZ.controller;

import org.lwjgl.opengl.Display;

import de.krien.twoDayZ.controller.gamestate.EGameState;
import de.krien.twoDayZ.controller.gamestate.play.Play;
import de.krien.twoDayZ.view.window.Window;

public class Game {

    private EGameState gameState;
    private Window window;

    public Game() {
        gameState = EGameState.PLAY;
    }

    public void init() {
        window = new Window();
        ((Play) EGameState.PLAY.getReference()).init();
    }

    public void run() {
        while (!Display.isCloseRequested()) {
            update();
            window.clearDisplay();
            draw();
            window.updateDisplay();
        }
        Display.destroy();
    }

    private void update() {
        gameState.update();
    }

    private void draw() {
        gameState.draw();
    }
}
