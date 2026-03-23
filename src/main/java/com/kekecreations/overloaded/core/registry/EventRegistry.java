package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.StartMenuGui;

public class EventRegistry {

    public static void registerEvents(JavaPlugin javaPlugin) {

        javaPlugin.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Player player = event.getPlayer();
            Ref<EntityStore> playerRef = event.getPlayerRef();
            player.getPageManager().openCustomPage(playerRef, playerRef.getStore(), new StartMenuGui(player.getPlayerRef(), CustomPageLifetime.CantClose));
            RoundComponent roundComponent = playerRef.getStore().ensureAndGetComponent(playerRef, RoundComponent.getComponentType());
            roundComponent.freezeRoundTimer(true);
        });

    }
}
