package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.ItemShopGui;
import com.kekecreations.overloaded.common.ui.RoundStatsHud;

import javax.annotation.Nonnull;
import java.util.Objects;

public class PlayerTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public PlayerTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
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


        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);
            player.getHudManager().setCustomHud(player.getPlayerRef(), new RoundStatsHud(player.getPlayerRef(), roundData));
            if (!roundData.isTimerFrozen()) {
                roundData.setRoundTimer(roundData.getRoundTimer() - 1);
                if (Objects.equals(roundData.getRoundType(), "classic")) {
                    if (roundData.getRoundTimer() <= 0) {
                        if (roundData.getRoundCount() == 1) {
                            roundData.setRoundCount(2);
                            roundData.setRoundTimer(100);
                            roundData.freezeRoundTimer(true);
                            player.getPageManager().openCustomPage(ref, store, new ItemShopGui(player.getPlayerRef(), CustomPageLifetime.CantClose));
                            //OPEN ITEM UI (UNFREEZE TIMER HERE)
                        }
                    }
                }
            }
        }
    }
}
