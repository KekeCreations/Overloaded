package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class RoundTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public RoundTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
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
        PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));

        //Regen
        EntityStatMap entityStat = store.getComponent(ref, EntityStatMap.getComponentType());
        if (entityStat != null) {
            entityStat.addStatValue(DefaultEntityStatTypes.getHealth(), 2.0F);
        }

        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);

            if (!roundData.isTimerFrozen()) {
                roundData.setRoundTimer(roundData.getRoundTimer() - 1);
                if (roundData.getRoundTimer() <= 0 && !roundData.isTimerFrozen()) {
                    CommandManager.get().handleCommand(playerRef, "end_round");
                }
            }
        }
    }
}

