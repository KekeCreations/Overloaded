package com.kekecreations.overloaded.common.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.NPCPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpawnBossForRoundCommand extends AbstractTargetPlayerCommand {

    private final RequiredArg<String> npcId;



    public SpawnBossForRoundCommand() {
        super("spawn_boss", "Spawning NPC Command for Overloaded!");
        this.npcId = this.withRequiredArg("npcId", "Entity ID", ArgTypes.STRING);
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @Nullable Ref<EntityStore> ref, @NotNull Ref<EntityStore> ref1, @NotNull PlayerRef playerRef, @NotNull World world, @NotNull Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        int chance = (int) (Math.random() * 3);
        double negativeX = -1 * (Math.random() * 5);
        double positiveX =  (Math.random() * 5);
        double negativeZ = -1 * (Math.random() * 5);
        double positiveZ =  (Math.random() * 5);
        if (player != null) {
            TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
            if (transformComponent != null) {
                Vector3d vector;
                switch (chance) {
                    case 0 -> {
                        vector = new Vector3d(
                                transformComponent.getPosition().getX() + positiveX,
                                transformComponent.getPosition().getY() + 1,
                                transformComponent.getPosition().getZ() + positiveZ);
                    }
                    case 1 -> {
                        vector = new Vector3d(
                                transformComponent.getPosition().getX() + negativeX,
                                transformComponent.getPosition().getY() + 1,
                                transformComponent.getPosition().getZ() + negativeZ);
                    }
                    case 2 -> {
                        vector = new Vector3d(
                                transformComponent.getPosition().getX() + positiveX,
                                transformComponent.getPosition().getY() + 1,
                                transformComponent.getPosition().getZ() + negativeZ);
                    }
                    case 3 -> {
                        vector = new Vector3d(
                                transformComponent.getPosition().getX() + negativeX,
                                transformComponent.getPosition().getY() + 1,
                                transformComponent.getPosition().getZ() + positiveZ);
                    }
                    default -> {
                        vector = new Vector3d(
                                transformComponent.getPosition().getX() + positiveX,
                                transformComponent.getPosition().getY() + 1,
                                transformComponent.getPosition().getZ() + positiveZ);
                    }
                }
                NPCPlugin.get().spawnNPC(store, this.npcId.get(commandContext), null,
                        vector,
                        new Vector3f()
                );
            }
        }
    }
}