package com.avie29.pingdisplay;

import net.fabricmc.api.ClientModInitializer;

public class PingDisplayClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PingDisplayConfig.load();
    }
}