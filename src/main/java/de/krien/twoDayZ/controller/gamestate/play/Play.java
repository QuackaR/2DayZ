package de.krien.twoDayZ.controller.gamestate.play;

import java.util.List;

import de.krien.twoDayZ.controller.gamestate.IGameState;
import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.model.game.GameEntities;
import de.krien.twoDayZ.model.ui.AUIEntity;
import de.krien.twoDayZ.model.ui.UIEntities;

public class Play implements IGameState {

    private long timeOfLastGameLoop = System.nanoTime();

    public Play() {
    }
    
    public void init() {
    }

    public void update() {
        float timeSinceLastGameLoop = getTimeSinceLastGameLoop();
        updateGameEntities(timeSinceLastGameLoop);
        updateUIEntities(timeSinceLastGameLoop);
    }
    
    public void draw() {
        drawGameEntities();
        drawUIEntities();
    }
    
    private void updateGameEntities(float timeSinceLastGameLoop) {
		List<AGameEntity> gameEntities = GameEntities.INSTANCE.getEntityList();
        for(int i = 0; i < gameEntities.size(); i++) {
            gameEntities.get(i).update(timeSinceLastGameLoop);
        }
	}

	private void updateUIEntities(float timeSinceLastGameLoop) {
		List<AUIEntity> uiEntities = UIEntities.INSTANCE.getEntityList();
        for(int i = 0; i < uiEntities.size(); i++) {
        	uiEntities.get(i).update(timeSinceLastGameLoop);
        }
	}
	
	private void drawGameEntities() {
		List<AGameEntity> gameEntities = GameEntities.INSTANCE.getEntityList();
        for(int i = 0; i < gameEntities.size(); i++) {
        	gameEntities.get(i).draw();
        }
	}

	private void drawUIEntities() {
		List<AUIEntity> uiEntities = UIEntities.INSTANCE.getEntityList();
        for(int i = 0; i < uiEntities.size(); i++) {
        	uiEntities.get(i).draw();
        }
	}

    private float getTimeSinceLastGameLoop() {
        long timeOfThisGameLoop = System.nanoTime();
        long timeBetweenGameLoops = timeOfThisGameLoop - timeOfLastGameLoop;
        timeOfLastGameLoop = timeOfThisGameLoop;
        float secondsSinceLastGameLoop = (float) timeBetweenGameLoops / 1000000000;
        return secondsSinceLastGameLoop;
    }
}
