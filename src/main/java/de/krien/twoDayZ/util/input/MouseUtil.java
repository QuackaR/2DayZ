package de.krien.twoDayZ.util.input;

import de.krien.twoDayZ.model.AGameEntity;
import de.krien.twoDayZ.model.GameEntities;
import de.krien.twoDayZ.model.menu.ContextMenu;
import de.krien.twoDayZ.util.render.RenderContextMenuUtil;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

public class MouseUtil {

    public static void checkMouseActions() {
        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                if (Mouse.getEventButton() == 0) {
                    Vector2f eventPosition = new Vector2f(Mouse.getEventX(), Mouse.getEventY());
                    System.out.println("Left mouse button pressed at " + eventPosition.getX() + "/" + eventPosition.getY());
                } else if (Mouse.getEventButton() == 1) {
                    checkExistingMenu();
                    Vector2f eventPosition = new Vector2f(Mouse.getEventX(), Display.getHeight() - Mouse.getEventY());
                    GameEntities.INSTANCE.getEntityList().add(new ContextMenu(eventPosition));
                    System.out.println("Right mouse button pressed at " + eventPosition.getX() + "/" + eventPosition.getY());
                }
            }
        }
    }

    private static void checkExistingMenu() {
        for(AGameEntity entity : GameEntities.INSTANCE.getEntityList()) {
            if(entity instanceof ContextMenu) {
                GameEntities.INSTANCE.getEntityList().remove(entity);
            }
        }
    }

}
