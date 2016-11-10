package de.krien.twoDayZ.model.game.objects.chest;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.IClickableEntity;
import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.model.ui.UIEntities;
import de.krien.twoDayZ.model.ui.menu.ContextMenu;
import de.krien.twoDayZ.model.ui.menu.IContextMenuEntry;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class Chest extends AGameEntity implements IClickableEntity {

	private ContextMenu contextMenu;
	private List<IContextMenuEntry> contextMenuEntries;;

	public Chest() {
		super();
		this.texture = TextureUtil.loadGameEntityImage(EChestModels.MILITARY_CHEST_01);
	}

	@Override
	public void clicked(Vector2f clickPosition) {
		initContextMenuEntries();
		this.contextMenu = new ContextMenu(clickPosition, contextMenuEntries);
		UIEntities.INSTANCE.addEntity(contextMenu);
	}
	
	private void initContextMenuEntries() {
		IContextMenuEntry open = new IContextMenuEntry() {

			@Override
			public void run() {
				System.out.println("Open Chest-Option clicked!");
				texture = TextureUtil.loadGameEntityImage(EChestModels.MILITARY_CHEST_02);
				UIEntities.INSTANCE.removeEntity(contextMenu);
				contextMenu = null;
			}

			@Override
			public String getText() {
				return "Open Chest";
			}
		};
		IContextMenuEntry close = new IContextMenuEntry() {

			@Override
			public void run() {
				System.out.println("Close-Option clicked!");
				UIEntities.INSTANCE.removeEntity(contextMenu);
				contextMenu = null;
			}

			@Override
			public String getText() {
				return "Close";
			}
		};
		contextMenuEntries = new ArrayList<>();
		contextMenuEntries.add(open);
		contextMenuEntries.add(close);
	}

	public ContextMenu getContextMenu() {
		return contextMenu;
	}
	
	
}
