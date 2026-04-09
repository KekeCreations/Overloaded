package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
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
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;


public class SettingsGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    private static final String SPIDER_BUTTON_ID = "SPIDERMODE";
    private static final String DOUBLE_GOLD_MODE_BUTTON_ID = "DOUBLEGOLDMODE";
    private static final String BACK_BUTTON_ID = "BACK";
    RoundComponent roundData;

    public SettingsGui(@Nonnull PlayerRef playerRef, RoundComponent roundComponent, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.roundData = roundComponent;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/settings.ui");

        if (roundData.isArachnophobiaMode()) {
            uiCommandBuilder.set("#SPIDERMODE.TextSpans", Message.raw("ARACHNOPHOBIA MODE: ON"));
        } else {
            uiCommandBuilder.set("#SPIDERMODE.TextSpans", Message.raw("ARACHNOPHOBIA MODE: OFF"));
        }

        if (roundData.isDoubleGoldMode()) {
            uiCommandBuilder.set("#DOUBLEGOLDMODE.TextSpans", Message.raw("DOUBLE GOLD MODE: ON"));
        } else {
            uiCommandBuilder.set("#DOUBLEGOLDMODE.TextSpans", Message.raw("DOUBLE GOLD MODE: OFF"));
        }

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SPIDERMODE", EventData.of("OnButtonClicked", SPIDER_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#DOUBLEGOLDMODE", EventData.of("OnButtonClicked", DOUBLE_GOLD_MODE_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#BACK", EventData.of("OnButtonClicked", BACK_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull MenuWithButtonsData data) {
        super.handleDataEvent(ref, store, data);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
        if (store.getComponent(ref, RoundComponent.getComponentType()) == null) {
            store.addComponent(ref, RoundComponent.getComponentType());
        }
        if (SPIDER_BUTTON_ID.equals(data.buttonClicked)) {
            roundData.setArachnophobiaMode(!roundData.isArachnophobiaMode());
            roundData.setRoundMenu("settings");
            player.getPageManager().setPage(ref, store, Page.None);
            //player.getPageManager().openCustomPage(ref, store, new SettingsGui(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        }
        if (DOUBLE_GOLD_MODE_BUTTON_ID.equals(data.buttonClicked)) {
            roundData.setDoubleGoldMode(!roundData.isDoubleGoldMode());
            roundData.setRoundMenu("settings");
            player.getPageManager().setPage(ref, store, Page.None);
            //player.getPageManager().openCustomPage(ref, store, new SettingsGui(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
        }
        else if (BACK_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundMenu("start");
        }
    }

    @Override
    public void onDismiss(@NotNull Ref<EntityStore> ref, @NotNull Store<EntityStore> store) {
        super.onDismiss(ref, store);
        if (roundData.getRoundMenu() == "null") {
            roundData.setRoundMenu("start");
        }
    }
}