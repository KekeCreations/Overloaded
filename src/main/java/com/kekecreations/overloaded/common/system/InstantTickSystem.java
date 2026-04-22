package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.setup.SetTimeDilation;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.ui.ItemShopGui;
import com.kekecreations.overloaded.common.ui.RoundStatsHud;

import javax.annotation.Nonnull;
import java.util.Objects;

public class InstantTickSystem extends EntityTickingSystem<EntityStore> {

    private final ComponentType<EntityStore, RoundComponent> roundStats;


    public InstantTickSystem(ComponentType<EntityStore, RoundComponent> roundStats) {
        super();
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
            CommandManager.get().handleCommand(playerRef, "remove_items");
            RoundComponent roundData = store.getComponent(ref, roundStats);
            GoldAndKillsComponent goldData = store.getComponent(ref, GoldAndKillsComponent.getComponentType());

            Universe.get().getPlayers().forEach(playerRef1 -> {
                if (playerRef1.isValid() && playerRef1.getReference() != null) {
                    Player player1 = store.getComponent(playerRef1.getReference(), Player.getComponentType());
                    GoldAndKillsComponent goldData1 = store.getComponent(playerRef1.getReference(), GoldAndKillsComponent.getComponentType());
                    if (goldData1 != null && player1 != null) {
                        player1.getHudManager().setCustomHud(playerRef1, new RoundStatsHud(playerRef1, roundData, goldData1));
                    }
                    if (roundData.getRoundCount() == 4) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Fighter");
                        }
                    }
                    if (roundData.getRoundCount() == 6) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 8) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Zombie_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 10) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 15) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Zombie_Burnt_Variant");
                        }
                    }
                    if (roundData.getRoundCount() == 18) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Knight");
                        }
                    }
                    if (roundData.getRoundCount() == 22) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Pirate");
                        }
                    }
                    if (roundData.getRoundCount() == 24) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Pirate");
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Pirate");
                        }
                    }
                    if (roundData.getRoundCount() == 28) {
                        if (roundData.getRoundTimer() == 30) {
                            roundData.setRoundTimer(29);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Pirate");
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Knight");
                        }
                    }
                    if (roundData.getRoundCount() == 32) {
                        if (roundData.getRoundTimer() == 30) {
                            if (!roundData.isArachnophobiaMode()) {
                                roundData.setRoundTimer(29);
                                CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Cave_Spider");
                                CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Cave_Spider");
                            }
                        }
                    }
                    if (roundData.getRoundCount() == 55) {
                        if (roundData.getRoundTimer() == 30) {
                            roundData.setRoundTimer(29);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Dungeon_Scarak_Broodmother");
                        }
                    }
                    if (roundData.getRoundCount() == 65) {
                        if (roundData.getRoundTimer() == 35) {
                            roundData.setRoundTimer(34);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Goblin");
                        }
                    }
                    if (roundData.getRoundCount() == 75) {
                        if (roundData.getRoundTimer() == 42) {
                            roundData.setRoundTimer(41);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Emberwulf");
                        }
                    }
                    if (roundData.getRoundCount() == 95) {
                        if (roundData.getRoundTimer() == 41) {
                            roundData.setRoundTimer(40);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Hedera");
                        }
                    }
                    if (roundData.getRoundCount() == 98) {
                        if (roundData.getRoundTimer() == 37) {
                            roundData.setRoundTimer(36);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Hedera");
                        }
                    }
                    if (roundData.getRoundCount() == 115) {
                        if (roundData.getRoundTimer() == 41) {
                            roundData.setRoundTimer(40);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Zombie_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 116) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Zombie_Burnt_Variant");
                        }
                    }
                    if (roundData.getRoundCount() == 117) {
                        if (roundData.getRoundTimer() == 41) {
                            roundData.setRoundTimer(40);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 118) {
                        if (roundData.getRoundTimer() == 38) {
                            roundData.setRoundTimer(37);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Knight");
                        }
                    }
                }
            });


            if (goldData != null) {
                if (roundData.getRoundMenu() == "item_shop") {
                    if (store.isInThread()) {
                        player.getPageManager().openCustomPage(ref, store, new ItemShopGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, goldData));
                        Universe.get().getPlayers().forEach(playerRef1 -> {
                            if (playerRef1.getReference() != null ) {
                                Player player1 = store.getComponent(playerRef1.getReference(), Player.getComponentType());
                                if (player1 != null) {
                                    if (playerRef1.isValid() && !player1.wasRemoved()) {
                                        GoldAndKillsComponent goldData1 = store.getComponent(playerRef1.getReference(), GoldAndKillsComponent.getComponentType());
                                        RoundComponent roundComponent = store.getComponent(playerRef1.getReference(), RoundComponent.getComponentType());
                                        if (roundComponent == null && goldData1 != null) {
                                            player1.getPageManager().openCustomPage(playerRef1.getReference(), store, new ItemShopGui(playerRef1, CustomPageLifetime.CanDismissOrCloseThroughInteraction, goldData1));
                                        }
                                    }
                                }
                            }
                        });
                    }
                    if (roundData.getRoundType() == "classic" || roundData.getRoundType() == "quick" || roundData.getRoundType() == "chaos") {
                        World.setTimeDilation(0.02F, store);
                    }
                    roundData.setRoundMenu("null");
                }
            }
        }
    }
}
