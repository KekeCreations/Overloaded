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

public class ChaosModeEnemySystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;

    public ChaosModeEnemySystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(0.25F);
        this.roundStats = roundStats;
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {

        if (roundStats != null) {
            Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
            RoundComponent roundData = store.getComponent(ref, roundStats);

            if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                if (roundData.getRoundCount() > 5 && roundData.getRoundCount() < 10) {
                    if (Objects.equals(roundData.getRoundType(), "chaos")) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 50);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                }
                                case 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                }
                                case 49 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
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
