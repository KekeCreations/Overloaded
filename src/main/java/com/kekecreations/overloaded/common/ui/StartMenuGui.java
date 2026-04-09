package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.connection.DisconnectType;
import com.hypixel.hytale.protocol.packets.connection.ServerDisconnect;
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
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class StartMenuGui extends InteractiveCustomUIPage<StartMenuGuiData> {

    private static final String PLAY_NORMAL_BUTTON_ID = "PLAYCLASSIC";
    private static final String PLAY_QUICK_BUTTON_ID = "PLAYQUICK";
    private static final String QUIT_BUTTON_ID = "QUIT";

    private static final String SETTINGS = "SETTINGS";

    public StartMenuGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, StartMenuGuiData.CODEC);
    }


    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/start_menu.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#SETTINGS", EventData.of("OnButtonClicked", SETTINGS), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYCLASSIC", EventData.of("OnButtonClicked", PLAY_NORMAL_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYQUICK", EventData.of("OnButtonClicked", PLAY_QUICK_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#QUIT", EventData.of("OnButtonClicked", QUIT_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull StartMenuGuiData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));

        if (PLAY_NORMAL_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundType("classic");
            roundData.setRoundTimer(10);
            roundData.setRoundCount(1);
            roundData.freezeRoundTimer(false);
        }
        else if (PLAY_QUICK_BUTTON_ID.equals(data.buttonClicked)) {
        }
        else if (SETTINGS.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            roundData.setRoundMenu("settings");
        }
        else if (QUIT_BUTTON_ID.equals(data.buttonClicked)) {
            playerRef.getPacketHandler().writeNoCache(new ServerDisconnect(null, DisconnectType.Disconnect));
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }
}