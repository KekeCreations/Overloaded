package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.kekecreations.overloaded.common.command.EndRoundCommand;
import com.kekecreations.overloaded.common.command.OverloadedTopdownCommand;
import com.kekecreations.overloaded.common.command.SpawnEntityForRoundCommand;

public class CommandRegistry {

    public static void registerCommmands(JavaPlugin javaPlugin) {
        javaPlugin.getCommandRegistry().registerCommand(new OverloadedTopdownCommand());
        javaPlugin.getCommandRegistry().registerCommand(new SpawnEntityForRoundCommand());
        javaPlugin.getCommandRegistry().registerCommand(new EndRoundCommand());

    }
}
