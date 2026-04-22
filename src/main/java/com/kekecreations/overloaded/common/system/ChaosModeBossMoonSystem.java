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
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 15) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 5);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Cave_Spider");
                                }
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 20) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 3);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Goblin");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Hedera");
                                }
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 25) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 4);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Emberwulf");
                                }
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 30) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 4);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Burnt");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Knight");
                                }
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 35) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 10);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Burnt");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Knight");
                                }
                                case 2 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Fighter");
                                }
                                case 3 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Pirate");
                                }
                                case 4 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                }
                                case 5 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                }
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 40) {
                        for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                            int enemy = (int) (Math.random() * 15);
                            switch (enemy) {
                                default -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Burnt");
                                }
                                case 1 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Knight");
                                }
                                case 2 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Fighter");
                                }
                                case 3 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Pirate");
                                }
                                case 4 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                }
                                case 6 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                }
                                case 7 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Hedera");
                                }
                                case 8 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Emberwulf");
                                }
                                case 9 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Crawler");
                                }
                                case 10 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Cave_Spider");
                                }
                                case 11 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Eye");
                                }
                                case 12 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Goblin");
                                }
                                case 13 -> {
                                    CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Scarak_Fighter");
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
