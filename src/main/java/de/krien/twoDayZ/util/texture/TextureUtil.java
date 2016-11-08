package de.krien.twoDayZ.util.texture;

import de.krien.twoDayZ.model.IGameEntityModel;
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

    private final static String IMAGE_TYPE = "PNG";

    public static Texture loadGameEntityImage(IGameEntityModel model) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String fileName = model.getModelID() + "." + IMAGE_TYPE.toLowerCase();
        URL path = loader.getResource(model.getType() + "/" + fileName);
        try {
            return TextureLoader.getTexture(IMAGE_TYPE, path.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
