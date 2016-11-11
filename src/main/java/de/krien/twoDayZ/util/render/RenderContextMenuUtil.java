package de.krien.twoDayZ.util.render;

import java.awt.Font;
import java.nio.FloatBuffer;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

import de.krien.twoDayZ.model.ui.menu.IContextMenuEntry;

public class RenderContextMenuUtil {

	private static Font font = new Font("Arial", Font.PLAIN, 12);
	private static TrueTypeFont typeFont = new TrueTypeFont(font, false);

	public static void drawContextMenu(int verticesBufferID, int texturesBufferID, int verticesCount, Texture texture) {
		RenderUtil.drawUIObject(verticesBufferID, texturesBufferID, verticesCount, texture);
	}

	public static void drawContextMenuTitle(String title, Color textColor, Vector2f position, Vector2f entrySize) {
		int textHeight = typeFont.getHeight(title);
		float positionX = position.x + entrySize.getX() / 4;
		float positionY = position.y + (entrySize.getY() / 2) - (textHeight / 2);
		typeFont.drawString(positionX, positionY, title, textColor);
	}

	public static void drawContextMenuEntries(Color textColor, Vector2f position, Vector2f entrySize,
			List<IContextMenuEntry> entries) {
		for (int i = 0; i < entries.size(); i++) {
			String entry = entries.get(i).getText();
			Vector2f entryPosition = getMenuEntryPosition(i, position, entrySize, entries);
			typeFont.drawString(entryPosition.getX(), entryPosition.getY(), entry, textColor);
		}
	}

	private static Vector2f getMenuEntryPosition(int entryIndex, Vector2f position, Vector2f size, List<IContextMenuEntry> entries) {
		int textHeight = typeFont.getHeight(entries.get(entryIndex).getText());

		float positionX = position.x + size.getX() / 4;
		float positionY = position.y + (size.getY() / 2) - (textHeight / 2);

		float offset = size.getY() * (entryIndex + 1);
		positionY = positionY + offset;

		return new Vector2f(positionX, positionY);
	}

	public static FloatBuffer createMenuVerticesVBO(int bufferID, Vector2f position, Vector2f entrySize,
			List<IContextMenuEntry> entries) {
		FloatBuffer contextMenuVBO;
		contextMenuVBO = BufferUtils.createFloatBuffer(8 * (entries.size() + 2));
		
		for (int i = 0; i < entries.size() + 1; i++) {
			float[] vertexData = { position.getX(), entrySize.getY() * i + position.getY(), position.getX() + entrySize.getX(),
					entrySize.getY() * i + position.getY(), position.getX() + entrySize.getX(),
					entrySize.getY() * i + position.getY() + entrySize.getY(), position.getX(),
					entrySize.getY() * i + position.getY() + entrySize.getY() };
			contextMenuVBO.put(vertexData);
		}
		contextMenuVBO.flip();

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, contextMenuVBO, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

		return contextMenuVBO;
	}

	public static FloatBuffer createMenuTextureVBO(int bufferID, List<IContextMenuEntry> entries) {
		float[] headerData = { 0f, 0f, 1f, 0f, 1f, 0.24f, 0f, 0.24f };

		float[] textureData = { 0f, 0.25f, 1f, 0.25f, 1f, 0.5f, 0f, 0.5f };

		float[] footerData = { 0f, 0.51f, 1f, 0.51f, 1f, 0.75f, 0f, 0.75f };

		FloatBuffer textures;
		textures = BufferUtils.createFloatBuffer(textureData.length * (entries.size() + 2));
		textures.put(headerData);
		for (int i = 0; i < entries.size() - 1; i++) {
			textures.put(textureData);
		}
		textures.put(footerData);
		textures.flip();

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textures, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

		return textures;
	}

}
