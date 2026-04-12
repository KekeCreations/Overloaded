package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.TargetUtil;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.util.ProjectileSpawner;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ProjectilePetSystem extends DelayedEntitySystem<EntityStore> {


    public ProjectilePetSystem() {
        super(5.0F);
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> chunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(i);
        RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());

        if (roundData != null) {
            for (PlayerRef oPlayerRef : Universe.get().getPlayers()) {
                if (oPlayerRef.getReference() != null) {
                    InventoryComponent hotbarComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-1));
                    InventoryComponent backpackComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-9));
                    InventoryComponent storageComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-2));
                    ItemContainer hotbar = hotbarComponent.getInventory();
                    ItemContainer backpack = backpackComponent.getInventory();
                    ItemContainer storage = storageComponent.getInventory();
                    Transform lookVec = TargetUtil.getLook(ref, commandBuffer);
                    Vector3d lookPosition = lookVec.getPosition();
                    Vector3f lookRotation = lookVec.getRotation();

                    hotbar.forEach((slot, itemStack) -> {
                        if (itemStack.isValid()) {
                            if (itemStack.equals(new ItemStack("Fireball_Pet"))) {
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
