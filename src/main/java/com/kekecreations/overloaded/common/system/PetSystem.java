package com.kekecreations.overloaded.common.system;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.DelayedEntitySystem;
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
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PetSystem extends DelayedEntitySystem<EntityStore> {


    public PetSystem() {
        super(1.0F);
    }

    @Override
    public void tick(float v, int i, @NonNull ArchetypeChunk<EntityStore> chunk, @NonNull Store<EntityStore> store, @NonNull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = chunk.getReferenceTo(i);
        RoundComponent roundData = store.getComponent(ref, RoundComponent.getComponentType());

        if (roundData != null) {
            if (roundData.getRoundTimer() == 1) {
                for (PlayerRef oPlayerRef : Universe.get().getPlayers()) {
                    if (oPlayerRef.getReference() != null) {
                        InventoryComponent hotbarComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-1));
                        InventoryComponent backpackComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-9));
                        InventoryComponent storageComponent = store.getComponent(oPlayerRef.getReference(), InventoryComponent.getComponentTypeById(-2));
                        ItemContainer hotbar = hotbarComponent.getInventory();
                        ItemContainer backpack = backpackComponent.getInventory();
                        ItemContainer storage = storageComponent.getInventory();

                        hotbar.forEach((slot, itemStack) -> {
                            if (itemStack.isValid()) {
                                if (itemStack.equals(new ItemStack("Anvil_Pet"))) {
                                    Player oPlayer = store.getComponent(oPlayerRef.getReference(), Player.getComponentType());
                                    oPlayer.sendMessage(Message.raw("anvil pet said Hi"));
                                    ItemStackTransaction itemStackTransaction = oPlayer.giveItem(new ItemStack("Tool_Repair_Kit_Iron"), oPlayerRef.getReference(), store);
                                    ItemStack remainder = itemStackTransaction.getRemainder();

                                    if (remainder != null && !remainder.isEmpty()) {
                                        CommandManager.get().handleCommand(oPlayerRef, "say NO INVENTORY SPACE");
                                    }
                                }
                            }
                        });
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
