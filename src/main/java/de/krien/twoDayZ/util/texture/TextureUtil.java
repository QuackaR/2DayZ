package de.krien.twoDayZ.util.texture;

import java.io.IOException;
import java.net.URL;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import de.krien.twoDayZ.model.IEntityModel;

public class TextureUtil {

    private final static String IMAGE_TYPE = "PNG";
    
    public static Texture loadEntityImage(IEntityModel model) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String fileName = model.getModelID() + "." + IMAGE_TYPE.toLowerCase();
        URL path = loader.getResource(model.getType() + "/" + fileName);
        if(path == null) {
            path = loader.getResource("resources/" + model.getType() + "/" + fileName);
        }
        try {
            Texture tex = TextureLoader.getTexture(IMAGE_TYPE, path.openStream());
    		return tex;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
