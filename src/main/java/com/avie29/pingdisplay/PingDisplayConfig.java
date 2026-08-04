package com.avie29.pingdisplay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.*;

public class PingDisplayConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Path.of("config", "pingdisplay.json");

    private static PingDisplayConfig instance;

    public String pingColorGood     = "#00E676";
    public String pingColorOk       = "#D6CD30";
    public String pingColorBad      = "#F59042";
    public String pingColorTerrible = "#E53935";
    public String pingColorUnknown  = "#535353";
    public boolean showMs           = true;
    public boolean showNametagPing  = false;
    public boolean textShadow = true;

    public static PingDisplayConfig get() {
        if (instance == null) load();
        return instance;
    }

    public static void load() {
        instance = new PingDisplayConfig();
        if (!Files.exists(CONFIG_PATH)) {
            save();
            return;
        }
        try (Reader r = Files.newBufferedReader(CONFIG_PATH)) {
            instance = GSON.fromJson(r, PingDisplayConfig.class);
        } catch (IOException e) {
            System.err.println("[Day Display] Could not find config file, creating a default one" + e.getMessage());
        }
    }

    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer w = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(instance, w);
            }
        } catch (IOException e) {
            System.err.println("[PingDisplay] Failed to write default config file" + e.getMessage());
        }
    }

    public static int parseColor(String hex) {
        String clean = hex.startsWith("#") ? hex.substring(1) : hex;
        if (clean.length() != 6) {
            return 0xFFFFFFFF;
        }
        try {
            return 0xFF000000 | Integer.parseUnsignedInt(clean, 16);
        } catch (NumberFormatException e) {
            return 0xFFFFFFFF;
        }
    }
}