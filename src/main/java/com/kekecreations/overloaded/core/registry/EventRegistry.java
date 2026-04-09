package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.*;
import com.hypixel.hytale.protocol.packets.camera.SetServerCamera;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.StartMenuGui;

public class EventRegistry {

    private static final ServerCameraSettings cameraSettings = new ServerCameraSettings();

    public static void registerEvents(JavaPlugin javaPlugin) {
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 10.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffset;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.rotation = new Direction(0F, (-(float) Math.PI / 2F - 5.8F), 0F);
        cameraSettings.mouseInputType = MouseInputType.LookAtPlane;
        cameraSettings.planeNormal = new Vector3f(0.0F, 1.0F, 0.0F);

        javaPlugin.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            Ref<EntityStore> playerRef = event.getPlayerRef();
            Store<EntityStore> store = playerRef.getStore();
            PlayerRef ref = store.getComponent(playerRef, PlayerRef.getComponentType());
            if (ref != null) {
                player.getPageManager().openCustomPage(playerRef, playerRef.getStore(), new StartMenuGui(ref, CustomPageLifetime.CantClose));
                RoundComponent roundComponent = playerRef.getStore().ensureAndGetComponent(playerRef, RoundComponent.getComponentType());
                roundComponent.freezeRoundTimer(true);
                CommandManager.get().handleCommand(ref, "start_tp");

                ref.getPacketHandler().writeNoCache(new SetServerCamera(ClientCameraView.Custom, true, cameraSettings));
            }
        });
    }
}
