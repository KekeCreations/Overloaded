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
        super(0.30F);
        this.roundStats = roundStats;
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> archetypeChunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {

        if (roundStats != null) {
            Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
            RoundComponent roundData = store.getComponent(ref, roundStats);

            if (roundData != null) {
                if (roundData.getRoundTimer() > 0 && !roundData.isTimerFrozen()) {
                    if (Objects.equals(roundData.getRoundType(), "chaos")) {
                        if (roundData.getRoundCount() > 5 && roundData.getRoundCount() < 10) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 80);
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
                        if (roundData.getRoundCount() > 10 && roundData.getRoundCount() < 15) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 80);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                    }
                                    case 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                    }
                                    case 21, 22, 23, 24, 25 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                    }
                                    case 26, 27, 28, 29, 30 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                    }
                                    case 49 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Crawler");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 15 && roundData.getRoundCount() < 20) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 80);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                    }
                                    case 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                    }
                                    case 49 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Cave_Spider");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 20 && roundData.getRoundCount() < 25) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 20);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Duke");
                                    }
                                    case 1 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Hermit");
                                    }
                                    case 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Lobber");
                                    }
                                    case 3 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Miner");
                                    }
                                    case 4 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Ogre");
                                    }
                                    case 5 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scavenger");
                                    }
                                    case 6 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scrapper");
                                    }
                                    case 7 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Thief");
                                    }
                                    case 8 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Goblin");
                                    }
                                    case 9 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                    }
                                    case 10 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                    }
                                    case 11 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 25 && roundData.getRoundCount() < 30) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 80);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                    }
                                    case 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                    }
                                    case 21, 22, 23, 24, 25 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                    }
                                    case 26, 27, 28, 29, 30 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                    }
                                    case 31, 32, 33, 34, 35, 36, 37, 38 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                    }
                                    case 49 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Emberwulf");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 30 && roundData.getRoundCount() < 35) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 80);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                    }
                                    case 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                    }
                                    case 21, 22, 23, 24, 25 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                    }
                                    case 26, 27, 28, 29, 30 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                    }
                                    case 31, 32, 33, 34, 35, 36, 37, 38 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                    }
                                    case 49 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Emberwulf");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 35 && roundData.getRoundCount() < 40) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 25);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                    }
                                    case 0, 1, 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                    }
                                    case 3 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                    }
                                    case 4 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                    }
                                    case 5, 6, 7, 8, 9, 10 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                    }
                                    case 11 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                    }
                                    case 12 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                    }
                                    case 13 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                    }
                                    case 14 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                    }
                                    case 15 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                    }
                                    case 16 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                    }
                                    case 17 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                    }
                                    case 18 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                    }
                                    case 19 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 40) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 40);
                                switch (enemy) {
                                    default -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                    }
                                    case 0, 1, 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                    }
                                    case 3 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                    }
                                    case 4 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                    }
                                    case 5, 6, 7, 8, 9, 10 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                    }
                                    case 11 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                    }
                                    case 12 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                    }
                                    case 13 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                    }
                                    case 14 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                    }
                                    case 15 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                    }
                                    case 16 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                    }
                                    case 17 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                    }
                                    case 18 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                    }
                                    case 19 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                    }
                                    case 20 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                    }
                                    case 21 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                    }
                                    case 22 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                    }
                                    case 23 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                    }
                                    case 24 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Knight");
                                    }
                                    case 25 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Fighter");
                                    }
                                    case 26 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Skeleton_Pirate");
                                    }
                                    case 27 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                    }
                                    case 28 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                    }
                                    case 29 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Hedera");
                                    }
                                    case 30 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Emberwulf");
                                    }
                                    case 31 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Crawler");
                                    }
                                    case 32 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Cave_Spider");
                                    }
                                    case 33 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Eye");
                                    }
                                    case 34 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Goblin");
                                    }
                                    case 35 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Scarak_Fighter");
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
