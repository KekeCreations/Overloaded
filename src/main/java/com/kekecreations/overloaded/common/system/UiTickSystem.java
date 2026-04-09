package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.ItemShopGui;
import com.kekecreations.overloaded.common.ui.RoundStatsHud;
import com.kekecreations.overloaded.common.ui.SettingsGui;
import com.kekecreations.overloaded.common.ui.StartMenuGui;

import javax.annotation.Nonnull;
import java.util.Objects;

public class UiTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public UiTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(1.0F);
        this.roundStats = roundStats;
    }

    @Nonnull
    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }

    @Override
    public void tick(float dt, int index, ArchetypeChunk<EntityStore> chunk, Store<EntityStore> store, CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(index);
        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));


        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);

            if (roundData.getRoundMenu() == "start") {
                player.getPageManager().openCustomPage(ref, store, new StartMenuGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                roundData.setRoundMenu("null");
            }
            if (roundData.getRoundMenu() == "settings") {
                player.getPageManager().openCustomPage(ref, store, new SettingsGui(playerRef, roundData, CustomPageLifetime.CanDismissOrCloseThroughInteraction));
                roundData.setRoundMenu("null");
            }
        }
    }
}
