package de.krien.twoDayZ.model.objects.chest;

import de.krien.twoDayZ.model.AGameEntity;
import de.krien.twoDayZ.util.texture.TextureUtil;

public class Chest extends AGameEntity {

    public Chest() {
        super();
        this.texture = TextureUtil.loadGameEntityImage(EChestModels.MILITARY_CHEST_01);
    }
}
