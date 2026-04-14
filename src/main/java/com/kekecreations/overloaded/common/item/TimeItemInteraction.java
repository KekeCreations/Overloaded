package com.kekecreations.overloaded.common.item;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.kekecreations.overloaded.common.component.RoundComponent;

import javax.annotation.Nonnull;

public class TimeItemInteraction extends SimpleInstantInteraction {


    public static final BuilderCodec<TimeItemInteraction> CODEC = BuilderCodec.builder(
            TimeItemInteraction.class, TimeItemInteraction::new, SimpleInstantInteraction.CODEC
    ).build();

    @Override
    protected void firstRun(@Nonnull InteractionType interactionType, @Nonnull InteractionContext interactionContext, @Nonnull CooldownHandler cooldownHandler) {

        CommandBuffer<EntityStore> commandBuffer = interactionContext.getCommandBuffer();
        if (commandBuffer == null) {
            return;
        }
        Store<EntityStore> store = commandBuffer.getExternalData().getStore();
        Ref<EntityStore> ref = interactionContext.getEntity();
        Player player = commandBuffer.getComponent(ref, Player.getComponentType());
        RoundComponent roundComponent = commandBuffer.getComponent(ref, RoundComponent.getComponentType());

        if (player != null) {
            if (roundComponent != null) {
                if (roundComponent.isPaused()) {
                    roundComponent.setPausee(false);
                    World.setTimeDilation(1.0F, store);
                } else {
                    roundComponent.setPausee(true);
                    World.setTimeDilation(0.2F, store);
                }
            } else {
                player.sendMessage(Message.raw("MUST BE PLAYER ONE TO USE"));
            }
        }
    }
}
