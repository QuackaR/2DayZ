package de.krien.twoDayZ.controller.gamestate.menu;

import de.krien.twoDayZ.controller.gamestate.IGameState;
import de.krien.twoDayZ.controller.gamestate.menu.mainMenu.EMainMenuEntry;
import de.krien.twoDayZ.util.text.TextUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

public class Menu implements IGameState {

    private static final int OFFSET = 75;
    private  static EMainMenuEntry activeEntry = EMainMenuEntry.CONNECT;

    public void update() {
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            activeEntry = activeEntry.getNext();
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            activeEntry = activeEntry.getLast();
        }
    }

    public void draw() {
        for (EMainMenuEntry menuEntry : EMainMenuEntry.values()) {
            Vector2f position = getPosition(menuEntry);
            if(menuEntry == activeEntry) {
                TextUtil.getInstance().drawText(position.x, position.y, menuEntry.getText(), Color.red);
            } else {
                TextUtil.getInstance().drawText(position.x, position.y, menuEntry.getText(), Color.white);
            }
        }
    }

    private Vector2f getPosition(EMainMenuEntry menuEntry) {
        int displayWidth = Display.getWidth();
        int displayHeight = Display.getHeight();

        int textWidth = TextUtil.getInstance().getFont().getWidth(menuEntry.getText());
        int textHight = TextUtil.getInstance().getFont().getHeight(menuEntry.getText());

        float positionX = (displayWidth / 2) - (textWidth / 2);
        float positionY = (displayHeight / 2) - (textHight / 2);

        int menuEntriesCount = EMainMenuEntry.values().length + 1;
        float midPosition = menuEntriesCount / 2;

        if (menuEntry.getPosition() < midPosition) {
            float offset = (midPosition - menuEntry.getPosition()) * OFFSET;
            positionY = positionY - offset;
        } else if (menuEntry.getPosition() > midPosition) {
            float offset = (menuEntry.getPosition() - midPosition) * OFFSET;
            positionY = positionY + offset;
        }

        return new Vector2f(positionX, positionY);
    }
}
