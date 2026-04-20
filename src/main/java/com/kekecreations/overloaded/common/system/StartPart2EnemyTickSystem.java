package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;

public class StartPart2EnemyTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public StartPart2EnemyTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(1.8F);
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


        if (store.getComponent(ref, roundStats) != null) {
            RoundComponent roundData = store.getComponent(ref, roundStats);
            //Purely so Intellij doesn't annoy me
            if (roundData != null && roundData.getRoundType() == "classic" || roundData.getRoundType() == "quick" || roundData.getRoundType() == "rounds") {
                if (roundData.getRoundCount() >= 5 && roundData.getRoundCount() <= 10) {
                    if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 8);
                            switch (enemy) {
                                case 0 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                }
                                case 1, 2, 3 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                }
                                case 4, 5, 6 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                }
                                case 7 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
