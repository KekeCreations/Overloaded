package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.ItemShopGui;
import com.kekecreations.overloaded.common.ui.RoundStatsHud;

import javax.annotation.Nonnull;
import java.util.Objects;

public class InstantTickSystem extends EntityTickingSystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public InstantTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super();
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
            CommandManager.get().handleCommand(playerRef, "remove_items");
            RoundComponent roundData = store.getComponent(ref, roundStats);

            player.getHudManager().setCustomHud(playerRef, new RoundStatsHud(playerRef, roundData));
            if (roundData.getRoundMenu() == "item_shop") {
                player.getPageManager().openCustomPage(ref, store, new ItemShopGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
                roundData.setRoundMenu("null");
            }
        }
    }
}
