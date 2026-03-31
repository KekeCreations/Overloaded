package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class ItemShopGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    public ItemShopGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
    }

    private static final String BUY1 = "BUY1";

    int itemChance = (int)(Math.random() * 6);
    int itemChance2 = (int)(Math.random() * 6);
    int itemChance3 = (int)(Math.random() * 6);

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/item_shop.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY1", EventData.of("OnButtonClicked", BUY1), false);

        switch(itemChance) {
            case 0, 1, 2 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans", Message.raw("EPIC"));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case  3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans", Message.raw("RARE"));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
        switch(itemChance2) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans", Message.raw("EPIC"));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case 1, 2, 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans", Message.raw("RARE"));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
        switch(itemChance3) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans", Message.raw("EPIC"));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case 1, 2, 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans", Message.raw("RARE"));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        if (BUY1.equals(data.buttonClicked)) {
            playerRef.sendMessage(Message.raw(itemChance + itemChance2 + itemChance3 + "ALL 3"));
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }
}