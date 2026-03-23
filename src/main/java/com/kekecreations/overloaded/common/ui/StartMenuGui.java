package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.camera.SetServerCamera;
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

import javax.annotation.Nonnull;
import java.util.Objects;

public class StartMenuGui extends InteractiveCustomUIPage<StartMenuGuiData> {

    private static final String PLAY_NORMAL_BUTTON_ID = "PLAYCLASSIC";
    private static final String PLAY_QUICK_BUTTON_ID = "PLAYQUICK";
    private static final String QUIT_BUTTON_ID = "QUIT";
    private static final ServerCameraSettings cameraSettings = new ServerCameraSettings();

    public StartMenuGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, StartMenuGuiData.CODEC);
    }


    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/start_menu.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYCLASSIC", EventData.of("OnButtonClicked", PLAY_NORMAL_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYQUICK", EventData.of("OnButtonClicked", PLAY_QUICK_BUTTON_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#QUIT", EventData.of("OnButtonClicked", QUIT_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull StartMenuGuiData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 8.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffset;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.rotation = new Direction(0.0F, (-(float)Math.PI / 2F), 0.0F);

        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        if (PLAY_NORMAL_BUTTON_ID.equals(data.buttonClicked)) {
            player.getPageManager().setPage(ref, store, Page.None);
            RoundComponent roundData = Objects.requireNonNull(store.getComponent(ref, RoundComponent.getComponentType()));
            if (store.getComponent(ref, RoundComponent.getComponentType()) == null) {
                store.addComponent(ref, RoundComponent.getComponentType());
            }
            if (player.getHudManager().getCustomHud() == null) {
                player.getHudManager().setCustomHud(playerRef, new RoundStatsHud(playerRef, roundData));
            }
            roundData.setRoundType("classic");
            roundData.setRoundTimer(100);
            roundData.setRoundCount(1);
            roundData.freezeRoundTimer(false);
            playerRef.getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));
        }
        else if (PLAY_QUICK_BUTTON_ID.equals(data.buttonClicked)) {
            playerRef.getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));
        }
        else if (QUIT_BUTTON_ID.equals(data.buttonClicked)) {
            System.exit(0);
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }
}