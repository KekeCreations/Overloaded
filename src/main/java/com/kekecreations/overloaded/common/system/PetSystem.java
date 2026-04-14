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
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.TargetUtil;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
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

        AtomicInteger fireball = new AtomicInteger((int) (Math.random() * 6));
        AtomicInteger iceball = new AtomicInteger((int) (Math.random() * 2));
        AtomicInteger kunai = new AtomicInteger((int) (Math.random() * 2));
        AtomicInteger acid_orb = new AtomicInteger((int) (Math.random() * 3));
        AtomicInteger trash_can = new AtomicInteger((int) (Math.random() * 2));
        AtomicInteger spear = new AtomicInteger((int) (Math.random() * 5));
        AtomicInteger iron_spear = new AtomicInteger((int) (Math.random() * 6));
        AtomicInteger gold_spear_head = new AtomicInteger((int) (Math.random() * 6));
        AtomicInteger cobalt_spear = new AtomicInteger((int) (Math.random() * 7));
        AtomicInteger flame_spear = new AtomicInteger((int) (Math.random() * 8));
        AtomicInteger crimson_dice = new AtomicInteger((int) (Math.random() * 100));
        AtomicInteger dice = new AtomicInteger((int) (Math.random() * 80));

        if (roundData != null) {
            for (PlayerRef oPlayerRef : Universe.get().getPlayers()) {
                if (oPlayerRef.getReference() != null) {
                    InventoryComponent hotbarComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-1));
                    InventoryComponent backpackComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-9));
                    InventoryComponent storageComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-2));
                    ItemContainer hotbar = hotbarComponent.getInventory();
                    ItemContainer backpack = backpackComponent.getInventory();
                    ItemContainer storage = storageComponent.getInventory();

                    EntityStatMap statMap = store.getComponent(oPlayerRef.getReference(), EntityStatMap.getComponentType());
                    GoldAndKillsComponent goldData = store.getComponent(oPlayerRef.getReference(), GoldAndKillsComponent.getComponentType());

                    Transform lookVec = TargetUtil.getLook(oPlayerRef.getReference(), commandBuffer);
                    Vector3d lookPosition = lookVec.getPosition();
                    Vector3f lookRotation = lookVec.getRotation();

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
                                        count.getAndIncrement();
                                    }
                                });
                                for (int j = 0; j < count.get(); j++) {
                                    ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation.rotateX(360 / count.floatValue()));
                                }
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation);
                                fireball.set((int) (Math.random() * 3));
                            }


                            if (statMap != null) {
                                if (itemStack.equals(new ItemStack("Lucky_Dice"))) {
                                    if (statMap.get(DefaultEntityStatTypes.getHealth()).get() <= 20F) {
                                        statMap.resetStatValue(DefaultEntityStatTypes.getHealth());
                                        hotbar.removeItemStack(new ItemStack("Lucky_Dice"));

                                    }
                                }
                            }
                        }
                    });

                    storage.forEach((slot, itemStack) -> {
                        if (itemStack.isValid()) {
                            AtomicInteger spear_head_pet = new AtomicInteger();
                            if (itemStack.equals(new ItemStack("Spear_Head_Pet"))) {
                                AtomicInteger count = new AtomicInteger();
                                storage.forEach((slot2, itemStack2) -> {
                                    if (itemStack2.equals(new ItemStack("Spear_Head_Pet"))) {
                                        spear_head_pet.getAndIncrement();
                                    }
                                });
                                if (spear_head_pet.get() > 4) {
                                    spear_head_pet.set(4);
                                }
                                if (spear_head_pet.get() > 0 && spear_head_pet.get() <= 4) {
                                    int copperSpearChance = 5 - spear_head_pet.get();
                                    spear.set((int) (Math.random() * copperSpearChance));

                                    int ironSpearChance = 6 - spear_head_pet.get();
                                    iron_spear.set((int) (Math.random() * ironSpearChance));

                                    int cobaltSpearChance = 7 - spear_head_pet.get();
                                    cobalt_spear.set((int) (Math.random() * cobaltSpearChance));

                                    int flameSpearChance = 8 - spear_head_pet.get();
                                    flame_spear.set((int) (Math.random() * flameSpearChance));
                                }
                            }

                            if (itemStack.equals(new ItemStack("War_Ready_Spear_Head_Pet")) && spear.get() == 0) {

                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.add(0, 0, 0), lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.add(0, 0.4, 0), lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.add(0, 0.6, 0), lookRotation);
                            }
                            if (itemStack.equals(new ItemStack("Overloaded_Iron_Spear")) && iron_spear.get() == 1) {

                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Iron_Spear_Throw", lookPosition.add(0, 0.2, 0), lookRotation);
                                iron_spear.set((int) (Math.random() * 4));
                            }

                            if (itemStack.equals(new ItemStack("Overloaded_Cobalt_Spear")) && cobalt_spear.get() == 1) {

                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Cobalt_Spear_Throw", lookPosition, lookRotation);
                                cobalt_spear.set((int) (Math.random() * 4));
                            }

                            if (itemStack.equals(new ItemStack("Overloaded_Flame_Spear")) && flame_spear.get() == 1) {

                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Flame_Spear_Throw", lookPosition.add(0, 0.1, 0), lookRotation);
                                flame_spear.set((int) (Math.random() * 6));
                            }

                            if (itemStack.equals(new ItemStack("Fireball_Pet")) && fireball.get() == 1) {
                                AtomicInteger count = new AtomicInteger();
                                storage.forEach((slot2, itemStack2) -> {
                                    if (itemStack2.equals(new ItemStack("Meteor_Pet"))) {
                                        count.getAndIncrement();
                                    }
                                });
                                for (int j = 0; j < count.get(); j++) {
                                    ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation.rotateX(360 / count.floatValue()));
                                }
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Fireball_Pet_Projectile", lookPosition, lookRotation);
                                fireball.set((int) (Math.random() * 3));
                            }

                            if (itemStack.equals(new ItemStack("Ice_Ball_Pet")) && iceball.get() == 1) {
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Ice_Ball_Pet_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Ice_Ball_Pet_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 3F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Ice_Ball_Pet_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 2F));
                                iceball.set((int) (Math.random() * 3));
                            }

                            if (itemStack.equals(new ItemStack("Kunai_Pack")) && kunai.get() == 1) {
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Kunai_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Kunai_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 3F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Kunai_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 2F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Kunai_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 4F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Kunai_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 5F));
                                kunai.set((int) (Math.random() * 3));
                            }

                            if (itemStack.equals(new ItemStack("Gold_Spear_Head_Pet")) && gold_spear_head.get() == 1) {
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 3F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 2F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 4F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 5F));
                                kunai.set((int) (Math.random() * 3));
                            }

                            if (itemStack.equals(new ItemStack("Acid_Orb_Pet")) && acid_orb.get() == 1) {
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Acid_Orb_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Acid_Orb_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 2F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Acid_Orb_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 4F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Acid_Orb_Projectile", lookPosition.subtract(0, 0.5, 0), lookRotation.rotateX(360 / 6F));
                                acid_orb.set((int) (Math.random() * 3));
                            }

                            if (itemStack.equals(new ItemStack("Trash_Can")) && trash_can.get() == 1) {
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation);
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 2F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 3F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 4F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 5F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 6F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 7F));
                                ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Can_Toss", lookPosition, lookRotation.rotateX(360 / 8F));
                                trash_can.set((int) (Math.random() * 3));
                            }

                            if (itemStack.equals(new ItemStack("Crimson_Dice")) && crimson_dice.get() == 1) {
                                int spawn = (int) (Math.random() * 6);
                                switch (spawn) {
                                    case 1 -> {
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Archer");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Archer");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Archer");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Archer");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Archer");
                                    }
                                    case 2 -> {
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Lancer");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Lancer");
                                    }
                                    case 3 -> {
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Knight");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Knight");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Knight");
                                    }
                                    case 4 -> {
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Gunner");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Gunner");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Gunner");
                                    }
                                    case 5 -> {
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Alchemist");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Alchemist");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Alchemist");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Alchemist");
                                    }
                                    case 0 -> {
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Praetorian");
                                        CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Praetorian");
                                    }
                                }
                                crimson_dice.set((int) (Math.random() * 20));
                            }

                            if (statMap != null) {
                                if (itemStack.equals(new ItemStack("Broken_Shield"))) {
                                    statMap.addStatValue(DefaultEntityStatTypes.getSignatureEnergy(), 0.05F);
                                    statMap.addStatValue(DefaultEntityStatTypes.getHealth(), 0.025F);
                                }
                                if (itemStack.equals(new ItemStack("King_Shield"))) {
                                    statMap.addStatValue(DefaultEntityStatTypes.getSignatureEnergy(), 0.35F);
                                    statMap.subtractStatValue(DefaultEntityStatTypes.getHealth(), 0.05F);
                                }
                                if (itemStack.equals(new ItemStack("The_Core"))) {
                                    statMap.addStatValue(DefaultEntityStatTypes.getHealth(), 0.5F);
                                    statMap.subtractStatValue(DefaultEntityStatTypes.getStamina(), 1F);
                                }
                                if (itemStack.equals(new ItemStack("Heart_Pet"))) {
                                    statMap.addStatValue(DefaultEntityStatTypes.getHealth(), 0.05F);
                                }
                            }

                            if (statMap != null && goldData != null) {
                                if (itemStack.equals(new ItemStack("Dice")) && dice.get() == 1) {
                                    int diceRoll = (int) (Math.random() * 5);
                                    switch (diceRoll) {
                                        case 0 -> {
                                            statMap.resetStatValue(DefaultEntityStatTypes.getSignatureEnergy());
                                            oPlayerRef.sendMessage(Message.raw("Dice rolled a 1, Weapon ability is now fully charged"));
                                        }
                                        case 1 -> {
                                            statMap.resetStatValue(DefaultEntityStatTypes.getHealth());
                                            oPlayerRef.sendMessage(Message.raw("Dice rolled a 2, Health is fully restocked!"));
                                        }
                                        case 2 -> {
                                            goldData.setGold(goldData.getGold() + 5);
                                            oPlayerRef.sendMessage(Message.raw("Dice rolled a 3, Enjoy 5 golden coins!"));
                                        }
                                        case 3 -> {
                                            CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            CommandManager.get().handleCommand(oPlayerRef, "spawn_enemy Skeleton_Burnt_Praetorian");
                                            oPlayerRef.sendMessage(Message.raw("Dice rolled a 4, Danger ahead!"));
                                        }
                                        case 4 -> {
                                            statMap.setStatValue(DefaultEntityStatTypes.getHealth(), 50F);
                                            oPlayerRef.sendMessage(Message.raw("Dice rolled a 5, Health crit!"));
                                        }
                                        case 5 -> {
                                            goldData.setGold(goldData.getGold() + 30);
                                            oPlayerRef.sendMessage(Message.raw("Dice rolled a 6, Enjoy 30 golden coins!"));
                                        }
                                    }
                                }
                            }
                        }
                    });

                    oPlayerRef.sendMessage(Message.raw(spear + "copperthrow chance"));
                    if (spear.get() == 0) {
                        ProjectileSpawner.spawnProjectile(commandBuffer, ref, "Spear_Throw", lookPosition.subtract(0, 0.5, 0), lookRotation);
                    }
                }
            }
        }
    }

    @Override
    public @Nullable Query<EntityStore> getQuery() {
        return Archetype.of(PlayerRef.getComponentType());
    }
}
