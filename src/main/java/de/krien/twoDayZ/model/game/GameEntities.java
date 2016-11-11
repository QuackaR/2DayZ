package de.krien.twoDayZ.model.game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import de.krien.twoDayZ.model.game.objects.chest.Chest;
import de.krien.twoDayZ.model.game.player.Player;

public enum GameEntities {

	INSTANCE();

	private List<AGameEntity> entityList;

	private GameEntities() {
	}
	
	private void init() {
		entityList = new ArrayList<>();
		// TODO TMP
		Player player = new Player();
		player.setPosition(new Vector2f(250, 250));
		entityList.add(player);
		Chest chest = new Chest();
		chest.setPosition(new Vector2f(500, 500));
		entityList.add(chest);
		//
	}

	public List<AGameEntity> getEntityList() {
		if(entityList == null)  {
			init();
		}
		return entityList;
	}

	public void setEntityList(List<AGameEntity> entityList) {
		this.entityList = entityList;
	}

	public void addEntity(AGameEntity entity) {
		entityList.add(entity);
	}

	public void removeEntity(AGameEntity entity) {
		entityList.remove(entity);
	}

}
