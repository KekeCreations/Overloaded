package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.protocol.packets.interface_.Page;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class ItemShopGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    RoundComponent roundComponent;

    public ItemShopGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime, RoundComponent roundComponent) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundComponent = roundComponent;
    }

    private static final String BUY1 = "BUY1";
    private static final String BUY2 = "BUY2";
    private static final String BUY3 = "BUY3";
    private static final String CONTINUE = "CONTINUE";

    int itemChance = (int)(Math.random() * 6);
    int itemChance2 = (int)(Math.random() * 6);
    int itemChance3 = (int)(Math.random() * 6);

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/item_shop.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BUY1", EventData.of("OnButtonClicked", BUY1), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#CONTINUE", EventData.of("OnButtonClicked", CONTINUE), false);

        switch(itemChance) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case 1, 2, 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY1.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC1.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE1.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
        switch(itemChance2) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case 1, 2, 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY2.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC2.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE2.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
        switch(itemChance3) {
            case 0 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("EPIC", null, null, null, null,
                                "#8421FC", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("FLAMETHROWER"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Flamethrower_Goblin.png");
            }
            case 1, 2, 3, 4, 5 -> {
                uiCommandBuilder.set("#RARITY3.TextSpans",
                        new Message(new FormattedMessage("RARE", null, null, null, null,
                                "#1E98F7", MaybeBool.False, MaybeBool.False, MaybeBool.False, MaybeBool.False,
                                null, false,  null)));
                uiCommandBuilder.set("#DESC3.TextSpans", Message.raw("SLOWING TOTEM"));
                uiCommandBuilder.set("#IMAGE3.AssetPath", "Icons/ItemsGenerated/Furniture_Temple_Emerald_Statue.png");
            }
        }
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        Player player = store.getComponent(ref, Player.getComponentType());

        if (player != null) {
            if (BUY1.equals(data.buttonClicked)) {


            }
            if (CONTINUE.equals(data.buttonClicked)) {
                player.getPageManager().setPage(ref, store, Page.None);
                if (roundComponent.getRoundType() == "classic") {
                    World.setTimeDilation(1F, store);
                }
                roundComponent.setRoundMenu("null");
            }
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }

    @Override
    public void onDismiss(@NonNull Ref<EntityStore> ref, @NonNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        roundComponent.setRoundCount(roundComponent.getRoundCount() + 1);
        roundComponent.setRoundTimer(60);
        roundComponent.freezeRoundTimer(false);
        roundComponent.setRoundMenu("null");
        if (roundComponent.getRoundType() == "classic") {
            World.setTimeDilation(1F, store);
        }
    }
}