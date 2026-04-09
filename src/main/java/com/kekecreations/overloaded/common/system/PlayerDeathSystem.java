package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDeathSystem extends DeathSystems.OnDeathSystem {


    public PlayerDeathSystem() {
        super();
    }

    @Override
    public void onComponentAdded(@NotNull Ref<EntityStore> ref, @NotNull DeathComponent deathComponent, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> commandBuffer) {
    }

    @Override
    public void onComponentRemoved(@NotNull Ref<EntityStore> ref, @NotNull DeathComponent component, @NotNull Store<EntityStore> store, @NotNull CommandBuffer<EntityStore> commandBuffer) {
        Player player = store.getComponent(ref, Player.getComponentType());
        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
        if (player != null) {
            RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());
            if (roundData != null) {
                roundData.setRoundType("null");
                roundData.setRoundMenu("start");
                roundData.setRoundTimer(-1);
                roundData.setKills(0);
                roundData.setGold(0);
                //roundData.freezeRoundTimer(true);
                //OPEN SCREEN TO DISPLAY GAME STATS AND THEN DELETE THEM LATER
            }
        }
        if (playerRef != null) {
            //CommandManager.get().handleCommand(playerRef, "start_tp");
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Query.any();
    }
}
