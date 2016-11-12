package de.krien.twoDayZ.model.game.objects.chest;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.IClickableEntity;
import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.model.ui.UIEntities;
import de.krien.twoDayZ.model.ui.menu.ContextMenu;
import de.krien.twoDayZ.model.ui.menu.IContextMenuEntry;
import de.krien.twoDayZ.util.physics.PhysicsUtil;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class Chest extends AGameEntity implements IClickableEntity {

	private final float DEFAULT_SCALE = 1.0f;
	
	private ContextMenu contextMenu;
	private List<IContextMenuEntry> contextMenuEntries;;

	public Chest() {
		super();
		setTexture(TextureUtil.loadEntityImage(EChestModels.MILITARY_CHEST_01));
		setBody(PhysicsUtil.initStationaryRectangluarBody(this));
		setScale(DEFAULT_SCALE);
		initContextMenuEntries();
		this.contextMenu = new ContextMenu("Chest", contextMenuEntries);
	}

	@Override
	public void clicked(Vector2f clickPosition) {
		contextMenu.setPosition(clickPosition);
		if(!UIEntities.INSTANCE.getEntityList().contains(contextMenu)) {
			UIEntities.INSTANCE.addEntity(contextMenu);
		}
	}
	
	private void initContextMenuEntries() {
		IContextMenuEntry open = new IContextMenuEntry() {

			@Override
			public void run() {
				setTexture(TextureUtil.loadEntityImage(EChestModels.MILITARY_CHEST_02));
				UIEntities.INSTANCE.removeEntity(contextMenu);
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
