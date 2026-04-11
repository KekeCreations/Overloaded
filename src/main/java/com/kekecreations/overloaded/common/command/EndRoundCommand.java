package com.kekecreations.overloaded.common.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.RemoveReason;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EndRoundCommand extends AbstractTargetPlayerCommand {


    public EndRoundCommand() {
        super("end_round", "Goodbye NPCs!");
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @Nullable Ref<EntityStore> ref, @NotNull Ref<EntityStore> ref1, @NotNull PlayerRef playerRef, @NotNull World world, @NotNull Store<EntityStore> store) {
        RoundComponent roundComponent = store.getComponent(ref, RoundComponent.getComponentType());
        if (roundComponent != null) {
            roundComponent.freezeRoundTimer(true);
            roundComponent.setRoundMenu("item_shop");

            if (roundComponent.getRoundType() == "round_based") {
                store.forEachEntityParallel(NPCEntity.getComponentType(), (index, archetypeChunk, commandBuffer) -> commandBuffer.removeEntity(archetypeChunk.getReferenceTo(index), RemoveReason.REMOVE));
            }
            if (roundComponent.getRoundType() == "classic") {
                World.setTimeDilation(0.05F, store);
            }
        }
    }
}