package com.kekecreations.overloaded.common.util;

import com.hypixel.hytale.component.AddReason;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Holder;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.math.vector.Rotation3f;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.ProjectileComponent;
import com.hypixel.hytale.server.core.modules.entity.component.Intangible;
import com.hypixel.hytale.server.core.modules.time.TimeResource;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.joml.Vector3d;

public class ProjectileSpawner {


    public static void spawnProjectile(CommandBuffer<EntityStore> commandBuffer,
                                Ref<EntityStore> ref,
                                String projectileId,
                                Vector3d lookPosition,
                                Rotation3f lookRotation) {

        TimeResource timeResource = commandBuffer.getResource(TimeResource.getResourceType());
        Holder<EntityStore> holder = ProjectileComponent.assembleDefaultProjectile(timeResource, projectileId, lookPosition, lookRotation);
        ProjectileComponent projectileComponent = holder.getComponent(ProjectileComponent.getComponentType());
        UUIDComponent sourceUuidComponent = commandBuffer.getComponent(ref, UUIDComponent.getComponentType());


        if (projectileComponent != null) {
            holder.ensureComponent(Intangible.getComponentType());
            if (projectileComponent.getProjectile() == null) {
                projectileComponent.initialize();
                if (projectileComponent.getProjectile() == null) {
                    return;
                }
            }

            if (sourceUuidComponent != null) {
                projectileComponent.getProjectile().getDamage();
                projectileComponent.shoot(holder, sourceUuidComponent.getUuid(), lookPosition.x(), lookPosition.y(), lookPosition.z(), lookRotation.yaw(), lookRotation.pitch());
                commandBuffer.addEntity(holder, AddReason.SPAWN);
            }
        }
    }
}
