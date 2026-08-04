package com.avie29.pingdisplay.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import com.avie29.pingdisplay.PingDisplayConfig;

public class PingDisplayConfigScreen {

    public static Screen create(Screen parent) {
        PingDisplayConfig cfg = PingDisplayConfig.get();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config.pingdisplay.title"))
                .setSavingRunnable(PingDisplayConfig::save)
                .setDoesConfirmSave(false);

        ConfigEntryBuilder eb = builder.entryBuilder();
        ConfigCategory cat = builder.getOrCreateCategory(Component.translatable("config.pingdisplay.category.options"));

        cat.addEntry(eb.startColorField(Component.translatable("config.pingdisplay.pingColorGood"), parseColor(cfg.pingColorGood))
                .setDefaultValue(parseColor("#1EFF00"))
                .setSaveConsumer(v -> cfg.pingColorGood = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.translatable("config.pingdisplay.pingColorOk"), parseColor(cfg.pingColorOk))
                .setDefaultValue(parseColor("#FFF100"))
                .setSaveConsumer(v -> cfg.pingColorOk = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.translatable("config.pingdisplay.pingColorBad"), parseColor(cfg.pingColorBad))
                .setDefaultValue(parseColor("#FF9500"))
                .setSaveConsumer(v -> cfg.pingColorBad = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.translatable("config.pingdisplay.pingColorTerrible"), parseColor(cfg.pingColorTerrible))
                .setDefaultValue(parseColor("#FF3B3B"))
                .setSaveConsumer(v -> cfg.pingColorTerrible = toHex(v))
                .build());

        cat.addEntry(eb.startColorField(Component.translatable("config.pingdisplay.pingColorUnknown"), parseColor(cfg.pingColorUnknown))
                .setDefaultValue(parseColor("#555555"))
                .setSaveConsumer(v -> cfg.pingColorUnknown = toHex(v))
                .build());

        cat.addEntry(eb.startBooleanToggle(Component.translatable("config.pingdisplay.showMs"), cfg.showMs)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.showMs = v)
                .build());

        cat.addEntry(eb.startBooleanToggle(Component.translatable("config.pingdisplay.showNametagPing"), cfg.showNametagPing)
                .setDefaultValue(false)
                .setSaveConsumer(v -> cfg.showNametagPing = v)
                .build());

        cat.addEntry(eb.startBooleanToggle(Component.translatable("config.pingdisplay.textShadow"), cfg.textShadow)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.textShadow = v)
                .build());

        return builder.build();
    }

    private static int parseColor(String hex) {
        String clean = hex.startsWith("#") ? hex.substring(1) : hex;
        try {
            return Integer.parseUnsignedInt(clean, 16);
        } catch (NumberFormatException e) {
            return 0xFFFFFF;
        }
    }

    private static String toHex(int color) {
        return String.format("#%06X", color & 0xFFFFFF);
    }
}