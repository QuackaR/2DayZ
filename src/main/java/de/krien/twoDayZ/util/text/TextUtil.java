package de.krien.twoDayZ.util.text;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class TextUtil {

    private static TextUtil instance;

    private TrueTypeFont font;

    private TextUtil() {
        Font defaultAwtFont = new Font("Times New Roman", Font.BOLD, 48);
        font = new TrueTypeFont(defaultAwtFont, false);
    }

    public static TextUtil getInstance() {
        if(instance == null) {
            instance = new TextUtil();
        }
        return instance;
    }

    public void drawText(float positionX, float positionY, String text, Color color) {
        font.drawString(positionX, positionY, text, color);
    }

    public TrueTypeFont getFont() {
        return font;
    }
}
