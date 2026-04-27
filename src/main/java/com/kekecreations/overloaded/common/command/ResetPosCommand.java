package com.kekecreations.overloaded.common.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public class ResetPosCommand extends AbstractTargetPlayerCommand {


    public ResetPosCommand() {
        super("start_tp", "Teleport command for Overloaded!");
        this.setPermissionGroups("hytale:Adventurer");
    }

    @Override
    protected void execute(@NotNull CommandContext commandContext, @Nullable Ref<EntityStore> ref, @NotNull Ref<EntityStore> ref1, @NotNull PlayerRef playerRef, @NotNull World world, @NotNull Store<EntityStore> store) {
        Player player = store.getComponent(ref, Player.getComponentType());
        if (player != null) {
            TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());
            var pos = transformComponent.getPosition();
            Transform transform = new Transform(
                    new Vector3d(pos.x, pos.y + 20, pos.z),      // Position
                    transformComponent.getRotation()
            );
            Teleport teleport = Teleport.createForPlayer(transform);
            store.addComponent(ref, Teleport.getComponentType(), teleport);
        }
    }
}
