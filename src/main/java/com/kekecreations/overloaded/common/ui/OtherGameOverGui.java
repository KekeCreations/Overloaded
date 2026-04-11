package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;

import javax.annotation.Nonnull;


public class OtherGameOverGui extends InteractiveCustomUIPage<MenuWithButtonsData> {

    GoldAndKillsComponent goldData;

    public OtherGameOverGui(@Nonnull PlayerRef playerRef, GoldAndKillsComponent goldData, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, MenuWithButtonsData.CODEC);
        this.goldData = goldData;
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/other_game_over.ui");

        uiCommandBuilder.set("#KILLS.TextSpans", Message.raw("ENEMIES KILLED: " + goldData.getKills()));
        uiCommandBuilder.set("#GOLD.TextSpans", Message.raw("GOLD COLLECTED: " + goldData.getGold()));
    }
}