package de.krien.twoDayZ.model;

import de.krien.twoDayZ.model.player.Player;

public enum EGameEntity {

	PLAYER(Player.class), NPC(null), ACTIVE_OBJECT(null), PASSIVE_OBJECT(null);

	private Class<? extends IGameEntity> entityClass;

	private EGameEntity(Class<? extends IGameEntity> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<? extends IGameEntity> getEntityClass() {
		return entityClass;
	}

}
