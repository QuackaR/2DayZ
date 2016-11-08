package de.krien.twoDayZ.util.texture;

import de.krien.twoDayZ.model.player.EPlayerModels;
import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class TextureUtil {

    private final static String MAP_PATH = "resources/map/";
    private final static String PLAYER_PATH = "player/";
    private final static String IMAGE_TYPE = "PNG";

    private static Texture loadMapTiles(String fileName) {
        try {
            return TextureLoader.getTexture(IMAGE_TYPE, new FileInputStream(new File(MAP_PATH + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Texture loadPlayerImage(EPlayerModels model) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String fileName = "PLAYER_" + model.getModelID() + "." + IMAGE_TYPE.toLowerCase();
        URL path = loader.getResource(PLAYER_PATH + fileName);
        try {
            return TextureLoader.getTexture(IMAGE_TYPE, path.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
