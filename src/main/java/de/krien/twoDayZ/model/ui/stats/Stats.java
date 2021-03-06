package de.krien.twoDayZ.model.ui.stats;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.model.game.GameEntities;
import de.krien.twoDayZ.model.ui.AUIEntity;

public class Stats extends AUIEntity {

        private Font font;
        private TrueTypeFont typeFont;

        private long lastFrameTime;
        private long frameCounter;
        private long framesPerSecond;

        public Stats() {
            font = new Font("Times New Roman", Font.BOLD, 16);
            typeFont = new TrueTypeFont(font, false);
            lastFrameTime = getTime();
        }
        
        @Override
        public void update(float timeSinceLastGameLoop) {
        }

        @Override
        public void draw() {
            drawFps();
            drawCursorPosition();
            drawPlayerPosition();
        }

        private void drawFps() {
            updateFps();
            typeFont.drawString(5, 5, "FPS: " + getFramesPerSecond(), Color.red);
        }

        private void drawCursorPosition() {
            Vector2f position = new Vector2f(Mouse.getX(), (Display.getHeight() - Mouse.getY()));
            float positionX = Math.round(position.getX()* 100f) / 100f;
            float positionY = Math.round(position.getY()* 100f) / 100f;
            typeFont.drawString(5, 25, "Mouse Position: " + positionX + "/" + positionY, Color.red);
        }

        private void drawPlayerPosition() {
            AGameEntity player = GameEntities.INSTANCE.getEntityList().get(0);
            Vector2f position = new Vector2f(player.getPosition().getX(), player.getPosition().getY());
            float positionX = Math.round(position.getX()* 100f) / 100f;
            float positionY = Math.round(position.getY()* 100f) / 100f;
            typeFont.drawString(5, 45, "Player Position: " + positionX + "/" + positionY, Color.red);
        }

        private void updateFps() {
            if (getTime() - lastFrameTime > 1000) {
                framesPerSecond = frameCounter;
                frameCounter = 0;
                lastFrameTime += 1000;
            }
            frameCounter++;
        }

        private long getTime() {
            return System.nanoTime() / 1000000;
        }

        private long getFramesPerSecond() {
            return framesPerSecond;
        }
    }
