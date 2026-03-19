package com.kekecreations.overloaded.core.registry;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.kekecreations.overloaded.common.command.OverloadedTopdownCommand;

public class CommandRegistry {

    public static void registerCommmands(JavaPlugin javaPlugin) {
        javaPlugin.getCommandRegistry().registerCommand(new OverloadedTopdownCommand());

    }
}
