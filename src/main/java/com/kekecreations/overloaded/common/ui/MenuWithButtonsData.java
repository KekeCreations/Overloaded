package com.kekecreations.overloaded.common.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class MenuWithButtonsData {

    public String buttonClicked;

    public static final BuilderCodec<MenuWithButtonsData> CODEC = BuilderCodec.builder(MenuWithButtonsData.class, MenuWithButtonsData::new)
            .append(new KeyedCodec<>("OnButtonClicked", Codec.STRING), (menuData, s) -> menuData.buttonClicked = s, choicePageEventData -> choicePageEventData.buttonClicked)
            .add()
            .build();
}
