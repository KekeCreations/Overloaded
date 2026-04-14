package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.kekecreations.overloaded.common.item.TimeItemInteraction;

public class ItemInteractionRegistry {

    public static void register(JavaPlugin javaPlugin) {
        javaPlugin.getCodecRegistry(Interaction.CODEC).register("Pause_Game", TimeItemInteraction.class, TimeItemInteraction.CODEC);
    }
}
