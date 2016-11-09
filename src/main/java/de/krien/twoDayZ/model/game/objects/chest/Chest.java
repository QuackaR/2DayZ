package de.krien.twoDayZ.model.game.objects.chest;

import de.krien.twoDayZ.model.game.AGameEntity;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class Chest extends AGameEntity {

    public Chest() {
        super();
        this.texture = TextureUtil.loadGameEntityImage(EChestModels.MILITARY_CHEST_01);
    }
}
