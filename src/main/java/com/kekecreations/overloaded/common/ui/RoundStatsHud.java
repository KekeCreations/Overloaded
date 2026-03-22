package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

public class RoundStatsHud extends CustomUIHud {

    RoundComponent roundData;

    public RoundStatsHud(@NonNull PlayerRef playerRef, RoundComponent roundData) {
        super(playerRef);
        this.roundData = roundData;
    }

    @Override
    protected void build(@NonNull UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.append("Hud/round_stats.ui");
        uiCommandBuilder.set("#ROUNDCOUNT.TextSpans", Message.raw("Round " + roundData.getRoundCount()));
        uiCommandBuilder.set("#ROUNDTIMER.TextSpans", Message.raw(String.valueOf(roundData.getRoundTimer())));
    }
}
