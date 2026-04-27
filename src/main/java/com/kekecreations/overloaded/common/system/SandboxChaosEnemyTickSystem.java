package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;
import java.util.Objects;

public class SandboxChaosEnemyTickSystem extends DelayedEntitySystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public SandboxChaosEnemyTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super(0.5F);
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
            GoldAndKillsComponent goldData = store.getComponent(ref, GoldAndKillsComponent.getComponentType());

            if (roundData != null) {
                if (roundData.getRoundType() == "sandbox" && roundData.getSandboxChaosMode()) {
                    if (!roundData.isTimerFrozen() && roundData.getRoundTimer() > 0) {
                        if (roundData.getRoundCount() <= 10) {
                            switch (roundData.getSandboxSection1()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 10 && roundData.getRoundCount() <= 20) {
                            switch (roundData.getSandboxSection2()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 20 && roundData.getRoundCount() <= 30) {
                            switch (roundData.getSandboxSection3()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 30 && roundData.getRoundCount() <= 40) {
                            switch (roundData.getSandboxSection4()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 40 && roundData.getRoundCount() <= 50) {
                            switch (roundData.getSandboxSection5()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 50 && roundData.getRoundCount() <= 60) {
                            switch (roundData.getSandboxSection6()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 60 && roundData.getRoundCount() <= 70) {
                            switch (roundData.getSandboxSection7()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 70 && roundData.getRoundCount() <= 80) {
                            switch (roundData.getSandboxSection8()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 80 && roundData.getRoundCount() <= 90) {
                            switch (roundData.getSandboxSection9()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 90 && roundData.getRoundCount() <= 100) {
                            switch (roundData.getSandboxSection10()) {
                                //UNDEAD RISES
                                case 0 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                        }
                                    }
                                }
                                //DEAD ZONE
                                case 1 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Soldier");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Knight");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Burnt_Lancer");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Ranger");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Knight");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Frost_Fighter");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                                //AHOY DEADLY
                                case 2 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Captain");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Gunner");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Pirate_Striker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Archer");
                                            }
                                        }
                                    }
                                }
                                // WOLF PACK
                                case 3 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_Black");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wolf_White");
                                            }
                                        }
                                    }
                                }
                                //SPIDERS
                                case 4 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 2);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spider_Cave");
                                            }
                                        }
                                    }
                                }
                                //VOID UGLIES
                                case 5 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 5);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Crawler_Void");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Eye_Void");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spawn_Void");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spectre_Void");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Larva_Void");
                                            }
                                        }
                                    }
                                }
                                //BUG SEASON
                                case 6 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Defender");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Fighter");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Scarak_Seeker");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Dungeon_Scarak_Broodmother");
                                            }
                                        }
                                    }
                                }
                                //GOLDEN LURE
                                case 7 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 8);
                                        switch (enemy) {
                                            case 0 -> {
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
                                        }
                                    }
                                }
                                //EMBER MIGHT
                                case 8 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 7);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Emberwulf");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Spirit_Ember");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Golem_Firesteel");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Fighter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Footman");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Head");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Skeleton_Incandescent_Mage");
                                            }
                                        }
                                    }
                                }
                                //FERAN RAID
                                case 9 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 4);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Burrower");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Longtooth");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Sharptooth");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Feran_Windwalker");
                                            }
                                        }
                                    }
                                }
                                //SUPERNATURAL
                                case 10 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 10);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Ghoul");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Shadow_Knight");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hedera");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Cow_Undead");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Pig_Undead");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Hound_Bleached");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Chicken_Undead");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Werewolf");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Wraith");
                                            }
                                            case 9 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Yeti");
                                            }
                                        }
                                    }
                                }
                                //OUTLANDERS
                                case 11 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Berserker");
                                            }
                                            case 1 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Brute");
                                            }
                                            case 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Cultist");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Hunter");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Marauder");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Peon");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Priest");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Sorcerer");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Outlander_Stalker");
                                            }
                                        }
                                    }
                                }
                                //10 ROUNDS TO LIVE
                                case 12 -> {
                                    for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                        int enemy = (int) (Math.random() * 9);
                                        switch (enemy) {
                                            case 0, 1, 2 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                            }
                                            case 3 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                            }
                                            case 4 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                            }
                                            case 5 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                            }
                                            case 6 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                            }
                                            case 7 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                            }
                                            case 8 -> {
                                                CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (roundData.getRoundCount() > 100) {
                            for (PlayerRef playerRef1 : Universe.get().getPlayers()) {
                                int enemy = (int) (Math.random() * 28);
                                switch (enemy) {
                                    case 0, 1, 2 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant");
                                    }
                                    case 3 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Aberrant_Small");
                                    }
                                    case 4 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie");
                                    }
                                    case 5 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt");
                                    }
                                    case 6 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Giant_Zombie_Burnt_Variant");
                                    }
                                    case 7 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Burnt");
                                    }
                                    case 8 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Sand");
                                    }
                                    case 9 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Zombie_Frost");
                                    }
                                    case 10 -> {
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
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Duke");
                                    }
                                    case 21 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Hermit");
                                    }
                                    case 22 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Lobber");
                                    }
                                    case 23 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Miner");
                                    }
                                    case 24 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Ogre");
                                    }
                                    case 25 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scavenger");
                                    }
                                    case 26 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Scrapper");
                                    }
                                    case 27 -> {
                                        CommandManager.get().handleCommand(playerRef1, "spawn_enemy Goblin_Thief");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}