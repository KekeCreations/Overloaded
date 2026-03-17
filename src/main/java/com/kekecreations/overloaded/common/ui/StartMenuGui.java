package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.camera.SetServerCamera;
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

public class StartMenuGui extends InteractiveCustomUIPage<StartMenuGuiData> {

    private static final String PLAY_NORMAL_BUTTON_ID = "PLAYNORMAL";
    private static final ServerCameraSettings cameraSettings = new ServerCameraSettings();

    public StartMenuGui(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, StartMenuGuiData.CODEC);
    }


    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("Pages/start_menu.ui");
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#PLAYNORMAL", EventData.of("OnButtonClicked", PLAY_NORMAL_BUTTON_ID), false);
    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull StartMenuGuiData data) {
        super.handleDataEvent(ref, store, data);
        boolean changed = false;

        if (PLAY_NORMAL_BUTTON_ID.equals(data.buttonClicked)) {
            playerRef.sendMessage(Message.raw("FUCK YES"));
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
            playerRef.getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));
        }

        if (changed) {
            this.playerRef.sendMessage(Message.raw("Changes processed."));
        }
    }
}