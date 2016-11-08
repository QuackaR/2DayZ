package de.krien.twoDayZ.controller.gamestate.play;

import de.krien.twoDayZ.controller.gamestate.IGameState;
import de.krien.twoDayZ.model.GameEntities;
import de.krien.twoDayZ.model.AGameEntity;
import de.krien.twoDayZ.util.debug.Stats;

import java.util.List;

public class Play implements IGameState {

    private long timeOfLastGameLoop = System.nanoTime();

    public Play() {
    }
    Stats stats;
    public void init() {
        stats = new Stats();
    }

    public void update() {
        float timeSinceLastGameLoop = getTimeSinceLastGameLoop();
        List<AGameEntity> gameEntities = GameEntities.INSTANCE.getEntityList();
        for(int i = 0; i < gameEntities.size(); i++) {
            AGameEntity gameEntity = gameEntities.get(i);
            gameEntity.update(timeSinceLastGameLoop);
        }
    }


    public void draw() {
        List<AGameEntity> gameEntities = GameEntities.INSTANCE.getEntityList();
        for(AGameEntity gameEntity : gameEntities) {
            gameEntity.draw();
        }
        stats.draw();
    }

    private float getTimeSinceLastGameLoop() {
        long timeOfThisGameLoop = System.nanoTime();
        long timeBetweenGameLoops = timeOfThisGameLoop - timeOfLastGameLoop;
        timeOfLastGameLoop = timeOfThisGameLoop;
        float secondsSinceLastGameLoop = (float) timeBetweenGameLoops / 1000000000;
        return secondsSinceLastGameLoop;
    }
}
