package de.krien.twoDayZ.util.texture;

import java.io.IOException;
import java.net.URL;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.krien.twoDayZ.model.game.IGameEntityModel;

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
