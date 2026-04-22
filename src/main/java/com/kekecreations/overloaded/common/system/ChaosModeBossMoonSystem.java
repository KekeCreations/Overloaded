package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class ChaosModeBossMoonSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;

    public ChaosModeBossMoonSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(2.0F);
        this.roundStats = roundStats;
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {

        if (roundStats != null) {
            Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
            PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));
            RoundComponent roundData = store.getComponent(ref, roundStats);

            if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                if (Objects.equals(roundData.getRoundType(), "chaos")) {
                    if (roundData.getRoundCount() == 5) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 10);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Scarak_Fighter");
                                }
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 10) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 3);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Eye");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Crawler");
                                }
                                case 2 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                }
                            }
                        }
                    }
                }
            }
        }

    }
    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
