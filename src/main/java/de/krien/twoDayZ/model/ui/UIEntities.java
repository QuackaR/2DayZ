package de.krien.twoDayZ.model.ui;

import java.util.ArrayList;
import java.util.List;

import de.krien.twoDayZ.model.ui.stats.Stats;

public enum UIEntities {

	INSTANCE();

	private List<AUIEntity> entityList;

	private UIEntities() {
		entityList = new ArrayList<>();

		// TMP
		Stats stats = new Stats();
		entityList.add(stats);
	}

	public List<AUIEntity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<AUIEntity> entityList) {
		this.entityList = entityList;
	}

	public void addEntity(AUIEntity entity) {
		entityList.add(entity);
	}

	public void removeEntity(AUIEntity entity) {
		entityList.remove(entity);
	}

}
