package de.krien.twoDayZ.model.ui.menu;

import java.awt.Font;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import de.krien.twoDayZ.model.ui.AUIEntity;
import de.krien.twoDayZ.util.render.RenderUtil;

public class ContextMenu extends AUIEntity {

	private final static float DEFAULT_WIDTH = 100;
	private final static float DEFAULT_HEIGHT = 25;
	private final static Color DEFAULT_COLOR = Color.gray;
	
    private Font font;
    private TrueTypeFont typeFont;
	private List<String> entries;
	
	public ContextMenu(Vector2f position, List<String> entries) {
		super();
		this.color = DEFAULT_COLOR;
		this.position = position;
		this.size = new Vector2f(DEFAULT_WIDTH, DEFAULT_HEIGHT * entries.size());
		this.entries = entries;
		
        font = new Font("Times New Roman", Font.BOLD, 16);
        typeFont = new TrueTypeFont(font, false);
	}
	
	@Override
	public void draw() {
		RenderUtil.drawUIObject(verticesBufferID, colorBufferID, vertices.limit());
		for(int i = 0; i < entries.size(); i++) {
			String entry = entries.get(i);
			Vector2f position = getEntryPosition(i);
			typeFont.drawString(position.getX(), position.getY(), entry, Color.red);
		}
	}
	
    private Vector2f getEntryPosition(int entryIndex) {
        float entryWidth = size.getX();
        float entryHeight = size.getY()/entries.size();

        int textWidth = typeFont.getWidth(entries.get(entryIndex));
        int textHeight = typeFont.getHeight(entries.get(entryIndex));

        float positionX = position.x + (entryWidth / 2) - (textWidth / 2);
        float positionY = position.y + (entryHeight / 2) - (textHeight / 2);

        float offset = entryHeight * entryIndex;
        positionY = positionY + offset;

        return new Vector2f(positionX, positionY);
    }

}