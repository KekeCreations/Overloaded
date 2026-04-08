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

import javax.annotation.Nonnull;
import java.util.Objects;

public class PlayerUiTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public PlayerUiTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(1.0f);
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
            player.getHudManager().setCustomHud(playerRef, new RoundStatsHud(playerRef, roundData));
            if (!roundData.isTimerFrozen()) {
                roundData.setRoundTimer(roundData.getRoundTimer() - 1);
                if (Objects.equals(roundData.getRoundType(), "classic")) {
                    if (roundData.getRoundTimer() <= 0) {
                        if (roundData.getRoundCount() == 1) {
                            roundData.freezeRoundTimer(true);
                            roundData.setRoundType("item_shop");
                        }
                    }
                }
            }
            if (roundData.getRoundType() == "item_shop") {
                player.getPageManager().openCustomPage(ref, store, new ItemShopGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, roundData));
                roundData.setRoundType("null");
            }
        }
    }
}
