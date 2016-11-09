package de.krien.twoDayZ.util.input;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.AGameEntity;
import de.krien.twoDayZ.model.GameEntities;
import de.krien.twoDayZ.model.menu.ContextMenu;

public class MouseUtil {

    //TODO Refactor
    public static void checkMouseActions() {
        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    Vector2f eventPosition = new Vector2f(Mouse.getEventX(), Mouse.getEventY());
                    System.out.println("Left mouse button pressed at " + eventPosition.getX() + "/" + eventPosition.getY());
                } else if (Mouse.getEventButton() == 1) {
                    Vector2f eventPosition = new Vector2f(Mouse.getEventX(), Display.getHeight() - Mouse.getEventY());
                    boolean activeMenu = false;
                    for (AGameEntity entity : GameEntities.INSTANCE.getEntityList()) {
                        if (entity instanceof ContextMenu) {
                            entity.setPosition(eventPosition);
                            activeMenu = true;
                        }
                    }
                    if (!activeMenu) {
                        GameEntities.INSTANCE.getEntityList().add(new ContextMenu(eventPosition));
                    }
                    System.out.println("Right mouse button pressed at " + eventPosition.getX() + "/" + eventPosition.getY());
                }
            }
        }
    }
}
