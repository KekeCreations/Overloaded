package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandManager;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.InventoryComponent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.inventory.transaction.ItemStackTransaction;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.TargetUtil;
import com.kekecreations.overloaded.common.component.RoundComponent;
import com.kekecreations.overloaded.common.util.ProjectileSpawner;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.atomic.AtomicInteger;

public class PetSystem extends DelayedEntitySystem<EntityStore> {


    public PetSystem() {
        super(1.0F);
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> chunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(i);
        RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());

        AtomicInteger fireball = new AtomicInteger((int) (Math.random() * 4));
        AtomicInteger spear = new AtomicInteger((int) (Math.random() * 4));
        if (roundData != null) {
            for (PlayerRef oPlayerRef : Universe.get().getPlayers()) {
                if (oPlayerRef.getReference() != null) {
                    InventoryComponent hotbarComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-1));
                    InventoryComponent backpackComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-9));
                    InventoryComponent storageComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-2));
                    ItemContainer hotbar = hotbarComponent.getInventory();
                    ItemContainer backpack = backpackComponent.getInventory();
                    ItemContainer storage = storageComponent.getInventory();

                    Transform lookVec = TargetUtil.getLook(oPlayerRef.getReference(), commandBuffer);
                    Vector3d lookPosition = lookVec.getPosition();
                    Vector3f lookRotation = lookVec.getRotation();

                    if (spear.get() == 1) {
                        ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition, lookRotation);
                    }

                    hotbar.forEach((slot, itemStack) -> {
                        if (itemStack.isValid()) {
                            if (itemStack.equals(new ItemStack("Anvil_Pet")) && roundData.getRoundTimer() == 1) {
                                Player oPlayer = store.getComponent(oPlayerRef.getReference(), Player.getComponentType());
                                oPlayer.sendMessage(Message.raw("anvil pet said Hi"));
                                ItemStackTransaction itemStackTransaction = oPlayer.giveItem(new ItemStack("Tool_Repair_Kit_Iron"), oPlayerRef.getReference(), store);
                                ItemStack remainder = itemStackTransaction.getRemainder();

                                if (remainder != null && !remainder.isEmpty()) {
                                    CommandManager.get().handleCommand(oPlayerRef, "say NO INVENTORY SPACE");
                                }
                            }

                            if (itemStack.equals(new ItemStack("Fireball_Pet")) && fireball.get() == 1) {
                                AtomicInteger count = new AtomicInteger();
                                storage.forEach((slot2, itemStack2) -> {
                                    if (itemStack2.equals(new ItemStack("Meteor_Pet"))) {
                                        oPlayerRef.sendMessage(Message.raw("PET COUNTED"));
                                        count.getAndIncrement();
                                    }
                                });
                                for (int j = 0; j < count.get(); j++) {
                                    ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation.rotateX(360 / count.floatValue()));
                                }
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation);
                                fireball.set((int) (Math.random() * 3));
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
