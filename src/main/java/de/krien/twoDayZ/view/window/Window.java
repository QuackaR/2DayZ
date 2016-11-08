package de.krien.twoDayZ.view.window;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {

    private static final String WINDOW_TITLE = "2DayZ";

    public Window() {
        createWindow();
        initGL();
    }

    private void createWindow() {
        try {
            Display.setFullscreen(false);
            DisplayMode displayMode = new DisplayMode(1440, 900);
            Display.setDisplayMode(displayMode);
            Display.setTitle(WINDOW_TITLE);
            Display.create();
            //Mouse.setGrabbed(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGL() {
        // Tiefentest deaktivieren
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        // Blending aktivieren
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        // 2D Texturen aktivieren
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        // Projektionsmatrix bearbeiten
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        // Projektionsmatrix auf Matrix-Stack sichern
        GL11.glPushMatrix();
        // Projektionsmatrix zurücksetzen
        GL11.glLoadIdentity();
        // Orthographische Perspektive setzen
        GL11.glOrtho(0, Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight(), 0, -1, 1);
        // "Objekttransformationsmatrix" bearbeiten
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // "Objekttransformationsmatrix" auf Matrix-Stack sichern
        GL11.glPushMatrix();
        // "Objekttransformationsmatrix" zurücksetzen
        GL11.glLoadIdentity();
    }

    public void clearDisplay() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }


    public void updateDisplay() {
        try {
            Display.update();
            Display.sync(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DisplayMode[] getAvailableDisplayModes() {
        DisplayMode[] availableDisplayModes = null;
        try {
            availableDisplayModes = Display.getAvailableDisplayModes();
        } catch (LWJGLException e) {
            System.out.println("Could not receive available display modes: ");
            e.printStackTrace();
        }
        return availableDisplayModes;
    }

}
