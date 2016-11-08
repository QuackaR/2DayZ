package de.krien.twoDayZ.controller.gamestate.play;

import de.krien.twoDayZ.controller.gamestate.IGameState;
import de.krien.twoDayZ.model.GameEntities;
import de.krien.twoDayZ.model.IGameEntity;
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
        List<IGameEntity> gameEntities = GameEntities.INSTANCE.getEntityList();
        for(IGameEntity gameEntity : gameEntities) {
            gameEntity.update(timeSinceLastGameLoop);
        }
    }


    public void draw() {
        List<IGameEntity> gameEntities = GameEntities.INSTANCE.getEntityList();
        for(IGameEntity gameEntity : gameEntities) {
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
