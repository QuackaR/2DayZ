package de.krien.twoDayZ.model.ui.menu;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import de.krien.twoDayZ.model.ui.AUIEntity;

public class ContextMenu extends AUIEntity {

	private final static Vector2f SIZE = new Vector2f(100, 25);
	private final static Color COLOR = Color.gray;

	public ContextMenu(Vector2f position) {
		super(position, SIZE, COLOR);
	}

}
