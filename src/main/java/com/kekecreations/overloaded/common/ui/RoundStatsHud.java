package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.kekecreations.overloaded.common.component.GoldAndKillsComponent;
import com.kekecreations.overloaded.common.component.RoundComponent;
import org.jspecify.annotations.NonNull;

public class RoundStatsHud extends CustomUIHud {

    RoundComponent roundData;

    GoldAndKillsComponent goldData;

    public RoundStatsHud(@NonNull PlayerRef playerRef, RoundComponent roundData, GoldAndKillsComponent goldData) {
        super(playerRef);
        this.roundData = roundData;
        this.goldData = goldData;
    }

    @Override
    protected void build(@NonNull UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.append("Hud/round_stats.ui");

        uiCommandBuilder.set("#KILLS.TextSpans", Message.raw("" + goldData.getKills()));
        uiCommandBuilder.set("#GOLD.TextSpans", Message.raw("" + goldData.getGold()));
        //Show and hide UI
        if (!roundData.isTimerFrozen()) {
            uiCommandBuilder.set("#ROUNDCOUNT.TextSpans", Message.raw("Round " + roundData.getRoundCount()));
            uiCommandBuilder.set("#ROUNDTIMER.TextSpans", Message.raw(String.valueOf(roundData.getRoundTimer())));

            if (roundData.getRoundCount() <= 10) {
                uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("THE UNDEAD RISES"));
            }
            if (roundData.getRoundCount() > 10 && roundData.getRoundCount() <= 20) {
                uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("THE DEAD ZONE"));
            }
            if (roundData.getRoundCount() > 20 && roundData.getRoundCount() <= 30) {
                uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("AHOY DEADLY"));
            }
            if (roundData.getRoundCount() > 30 && roundData.getRoundCount() <= 40) {
                if (roundData.isArachnophobiaMode()) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("WOLF PACK"));
                } else {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("ARACHNID AWAKENING"));
                }
            }
            if (roundData.getRoundCount() > 40 && roundData.getRoundCount() <= 50) {
                uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("VOID UGLIES"));
            }
            if (roundData.getRoundCount() > 51 && roundData.getRoundCount() <= 60) {
                uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("BUG SEASON"));
            }
        } else {
            uiCommandBuilder.set("#ROUNDCOUNT.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#ROUNDTIMER.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#TITLE.TextSpans", Message.raw(""));
        }
    }
}
