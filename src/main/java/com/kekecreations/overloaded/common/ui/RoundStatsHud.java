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

            if (roundData.getRoundType() != "chaos") {
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
                if (roundData.getRoundCount() > 50 && roundData.getRoundCount() <= 60) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("BUG SEASON"));
                }
                if (roundData.getRoundCount() > 60 && roundData.getRoundCount() <= 70) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("GOLDEN LURE"));
                }
                if (roundData.getRoundCount() > 70 && roundData.getRoundCount() <= 80) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("EMBER MIGHT"));
                }
                if (roundData.getRoundCount() > 80 && roundData.getRoundCount() <= 90) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("FERAN RAID"));
                }
                if (roundData.getRoundCount() > 90 && roundData.getRoundCount() <= 100) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("SUPERNATURAL FOES"));
                }
                if (roundData.getRoundCount() > 100 && roundData.getRoundCount() <= 110) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("OUTLANDERS"));
                }
                if (roundData.getRoundCount() > 110 && roundData.getRoundCount() <= 120) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("10 ROUNDS TO LIVE"));
                }
                if (roundData.getRoundCount() > 120) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("STRANGE THINGS"));
                }
            } else {
                if (roundData.getRoundCount() <= 4) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("CHAOTIC BONES"));
                }
                if (roundData.getRoundCount() == 5) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("INFESTED MOON"));
                }
                if (roundData.getRoundCount() > 5 && roundData.getRoundCount() < 10) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("CHAOTIC INFESTATION"));
                }
                if (roundData.getRoundCount() == 10) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("CORRUPTED MOON"));
                }
                if (roundData.getRoundCount() > 10 && roundData.getRoundCount() < 15) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("THE CHAOS CURSE"));
                }
                if (roundData.getRoundCount() == 15) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("COBWEB MOON"));
                }
                if (roundData.getRoundCount() > 15 && roundData.getRoundCount() < 20) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("CHAOS VENOM"));
                }
                if (roundData.getRoundCount() == 20) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("MYTHICAL MOON"));
                }
                if (roundData.getRoundCount() > 20 && roundData.getRoundCount() < 25) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("CHAOTIC CREATURES"));
                }
                if (roundData.getRoundCount() == 25) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("MAGMA MOON"));
                }
                if (roundData.getRoundCount() > 25 && roundData.getRoundCount() < 30) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("MOLTEN CHAOS"));
                }
                if (roundData.getRoundCount() == 30) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("BURNT MOON"));
                }
                if (roundData.getRoundCount() > 30 && roundData.getRoundCount() < 35) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("THE BURNT RISING"));
                }
                if (roundData.getRoundCount() == 35) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("BURNT MOON V2"));
                }
                if (roundData.getRoundCount() > 35 && roundData.getRoundCount() < 40) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("SUPERNATURAL"));
                }
                if (roundData.getRoundCount() == 40) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("GIANT MOONS"));
                }
                if (roundData.getRoundCount() > 40) {
                    uiCommandBuilder.set("#TITLE.TextSpans", Message.raw("THE GLORIOUS CHAOS"));
                }
            }
        } else {
            uiCommandBuilder.set("#ROUNDCOUNT.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#ROUNDTIMER.TextSpans", Message.raw(""));
            uiCommandBuilder.set("#TITLE.TextSpans", Message.raw(""));
        }
    }
}
