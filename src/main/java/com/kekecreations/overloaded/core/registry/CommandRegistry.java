package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.kekecreations.overloaded.common.command.*;

public class CommandRegistry {

    public static void registerCommmands(JavaPlugin javaPlugin) {
        javaPlugin.getCommandRegistry().registerCommand(new OverloadedTopdownCommand());
        javaPlugin.getCommandRegistry().registerCommand(new SpawnEntityForRoundCommand());
        javaPlugin.getCommandRegistry().registerCommand(new EndRoundCommand());
        javaPlugin.getCommandRegistry().registerCommand(new ResetPosCommand());
        javaPlugin.getCommandRegistry().registerCommand(new RemoveItemsCommand());
        javaPlugin.getCommandRegistry().registerCommand(new SpawnNPCFarCommand());
        javaPlugin.getCommandRegistry().registerCommand(new SpawnBossForRoundCommand());

    }
}
