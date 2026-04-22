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

public class ChaosModeStartEnemySystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;

    public ChaosModeStartEnemySystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(0.5F);
        this.roundStats = roundStats;
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {

        if (roundStats != null) {
            Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
            PlayerRef playerRef = Objects.requireNonNull(store.getComponent(ref, PlayerRef.getComponentType()));
            RoundComponent roundData = store.getComponent(ref, roundStats);

            if (roundData != null) {
                if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                    if (roundData.getRoundCount() >= 1 && roundData.getRoundCount() <= 4) {
                        if (Objects.equals(roundData.getRoundType(), "chaos")) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 3);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                    }
                                    case 1 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                    }
                                    case 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                    }
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
