package de.krien.twoDayZ.model.menu;

import de.krien.twoDayZ.model.AGameEntity;
import de.krien.twoDayZ.util.render.RenderContextMenuUtil;
import org.lwjgl.util.vector.Vector2f;

public class ContextMenu extends AGameEntity {

    public ContextMenu(Vector2f position) {
        super();
        this.position = position;
    }

    @Override
    public void update(float timeSinceLastGameLoop) {
        vertices = RenderContextMenuUtil.createVerticesVBO(verticesBufferID, position);
        textures = RenderContextMenuUtil.createColorVBO(texturesBufferID);
    }

    public void draw() {
        RenderContextMenuUtil.drawContextMenu(
                verticesBufferID,
                texturesBufferID,
                vertices.limit());
    }
}
