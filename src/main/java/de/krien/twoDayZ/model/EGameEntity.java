package de.krien.twoDayZ.model;

import de.krien.twoDayZ.model.player.Player;

public enum EGameEntity {

	PLAYER(Player.class), NPC(null), ACTIVE_OBJECT(null), PASSIVE_OBJECT(null);

	private Class<? extends AGameEntity> entityClass;

	private EGameEntity(Class<? extends AGameEntity> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<? extends AGameEntity> getEntityClass() {
		return entityClass;
	}

}
