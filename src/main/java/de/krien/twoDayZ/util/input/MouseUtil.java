package de.krien.twoDayZ.util.input;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.AEntity;
import de.krien.twoDayZ.model.IClickableEntity;
import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.model.game.GameEntities;
import de.krien.twoDayZ.model.ui.AUIEntity;
import de.krien.twoDayZ.model.ui.UIEntities;

public class MouseUtil {

    //TODO Refactor
    public static void checkMouseActions() {
        while (Mouse.next()) {
            if (Mouse.getEventButtonState()) {
                    Vector2f eventPosition = new Vector2f(Mouse.getEventX(), Display.getHeight() - Mouse.getEventY());
                    AEntity clickedEntity = getClickedEntity(eventPosition);
                    if(clickedEntity != null && clickedEntity instanceof IClickableEntity && clickedEntity instanceof AUIEntity && Mouse.getEventButton() == 0) {
                    	((IClickableEntity)clickedEntity).clicked(eventPosition);
                    } else if(clickedEntity != null && clickedEntity instanceof IClickableEntity && clickedEntity instanceof AGameEntity && Mouse.getEventButton() == 1) {
                    	((IClickableEntity)clickedEntity).clicked(eventPosition);
                    }
            }
        }
    }

	private static AEntity getClickedEntity(Vector2f eventPosition) {
		for(AEntity entity : UIEntities.INSTANCE.getEntityList()) {
		 	if(entity.getPosition() != null && entity.getSize() != null
		 			&& eventPosition.getX() >= entity.getPosition().getX()
					&& eventPosition.getX() <= (entity.getPosition().getX() + entity.getSize().getX())
					&& eventPosition.getY() >= entity.getPosition().getY()
					&& eventPosition.getY() <= (entity.getPosition().getY() + entity.getSize().getY())
			) {
				return entity;
			}
		}
		for(AEntity entity : GameEntities.INSTANCE.getEntityList()) {
			float halfSizeX = entity.getSize().getX() / 2;
			float halfSizeY = entity.getSize().getY() / 2;
		 	if(entity.getPosition() != null && entity.getSize() != null
		 			&& eventPosition.getX() >= entity.getPosition().getX() - halfSizeX
					&& eventPosition.getX() <= (entity.getPosition().getX() + halfSizeX)
					&& eventPosition.getY() >= entity.getPosition().getY() - halfSizeY
					&& eventPosition.getY() <= (entity.getPosition().getY() + halfSizeY)
			) {
				return entity;
			}
		}
		return null;
	}
}
