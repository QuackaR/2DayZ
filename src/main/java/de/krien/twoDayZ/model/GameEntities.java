package de.krien.twoDayZ.model;

import de.krien.twoDayZ.model.objects.chest.Chest;
import de.krien.twoDayZ.model.player.Player;
import de.krien.twoDayZ.util.debug.Stats;

import java.util.ArrayList;
import java.util.List;


public enum GameEntities {

	INSTANCE();

	private List<IGameEntity> entityList;

	private GameEntities() {
		entityList = new ArrayList<>();

		//TMP
		Player player = new Player();
		entityList.add(player);
		Chest chest = new Chest();
		entityList.add(chest);
		//
	}

	public List<IGameEntity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<IGameEntity> entityList) {
		this.entityList = entityList;
	}

}
