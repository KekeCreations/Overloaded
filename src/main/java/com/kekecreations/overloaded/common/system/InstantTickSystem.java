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
                    if (roundData.getRoundCount() == 5) {
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
                    if (roundData.getRoundCount() == 7) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Zombie_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 8) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                        }
                    }
                    if (roundData.getRoundCount() == 9) {
                        if (roundData.getRoundTimer() == 40) {
                            roundData.setRoundTimer(39);
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Zombie_Burnt");
                            CommandManager.get().handleCommand(playerRef1, "spawn_boss Giant_Skeleton_Burnt");
                        }
                    }
                }
            });


            if (goldData != null) {
                if (roundData.getRoundMenu() == "item_shop") {
                    player.getPageManager().openCustomPage(ref, store, new ItemShopGui(playerRef, CustomPageLifetime.CanDismissOrCloseThroughInteraction, goldData));
                    Universe.get().getPlayers().forEach(playerRef1 -> {
                        if (playerRef1.isValid() && playerRef1.getReference() != null) {
                            Player player1 = store.getComponent(playerRef1.getReference(), Player.getComponentType());
                            GoldAndKillsComponent goldData1 = store.getComponent(playerRef1.getReference(), GoldAndKillsComponent.getComponentType());
                            RoundComponent roundComponent = store.getComponent(playerRef1.getReference(), RoundComponent.getComponentType());
                            if (roundComponent == null && goldData1 != null && player1 != null) {
                                player1.getPageManager().openCustomPage(playerRef1.getReference(), store, new ItemShopGui(playerRef1, CustomPageLifetime.CanDismissOrCloseThroughInteraction, goldData1));
                            }
                        }
                    });
                    World.setTimeDilation(0.02F, store);
                    roundData.setRoundMenu("null");
                }
            }
        }
    }
}
