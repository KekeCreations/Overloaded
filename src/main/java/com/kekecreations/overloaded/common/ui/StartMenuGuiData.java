package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class StartMenuGuiData {

    public String buttonClicked;

    public static final BuilderCodec<StartMenuGuiData> CODEC = BuilderCodec.builder(StartMenuGuiData.class, StartMenuGuiData::new)
            .append(new KeyedCodec<>("OnButtonClicked", Codec.STRING), (startMenuGuiData, s) -> startMenuGuiData.buttonClicked = s, choicePageEventData -> choicePageEventData.buttonClicked)
            .add()
            .build();
}
