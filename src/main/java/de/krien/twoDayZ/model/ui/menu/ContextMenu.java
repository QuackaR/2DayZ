package de.krien.twoDayZ.model.ui.menu;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

import de.krien.twoDayZ.model.IClickableEntity;
import de.krien.twoDayZ.model.ui.AUIEntity;
import de.krien.twoDayZ.model.ui.EUIModel;
import de.krien.twoDayZ.util.render.RenderContextMenuUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class ContextMenu extends AUIEntity implements IClickableEntity {

	private final static float DEFAULT_ENTRY_WIDTH = 120;
	private final static float DEFAULT_ENTRY_HEIGHT = 30;
	private final static Color DEFAULT_TEXT_COLOR = Color.black;

	private String title;
	private Color textColor;
	private List<IContextMenuEntry> entries;
	private Vector2f entrySize;

	public ContextMenu(String title, List<IContextMenuEntry> entries) {
		super();
		this.title = title;
		this.textColor = DEFAULT_TEXT_COLOR;
		this.texture = TextureUtil.loadEntityImage(EUIModel.MENU);
		this.size = new Vector2f(DEFAULT_ENTRY_WIDTH, DEFAULT_ENTRY_HEIGHT*(entries.size()+1));
		this.entrySize = new Vector2f(DEFAULT_ENTRY_WIDTH, DEFAULT_ENTRY_HEIGHT);
		this.entries = entries;
	}

	@Override
	public void update(float timeSinceLastGameLoop) {
		vertices = RenderContextMenuUtil.createMenuVerticesVBO(verticesBufferID, position, entrySize, entries);
		textures = RenderContextMenuUtil.createMenuTextureVBO(textureBufferID, entries);
	}

	@Override
	public void draw() {
		RenderContextMenuUtil.drawContextMenu(verticesBufferID, textureBufferID, vertices.limit(), texture);
		RenderContextMenuUtil.drawContextMenuTitle(title, textColor, position, entrySize);
		RenderContextMenuUtil.drawContextMenuEntries(textColor, position, entrySize, entries);
	}



	@Override
	public void clicked(Vector2f clickPosition) {
		float entryHeight = entrySize.getY();
		for (int i = 0; i < entries.size(); i++) {
			float positionMin = position.getY() + entryHeight * (i + 1);
			float positionMax = position.getY() + entryHeight * (i + 2);
			if (clickPosition.getY() >= positionMin && clickPosition.getY() <= positionMax) {
				entries.get(i).run();
			}

		}
	}

}
