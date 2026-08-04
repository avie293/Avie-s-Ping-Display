package com.avie29.pingdisplay;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.avie29.pingdisplay.config.PingDisplayConfigScreen;

public class PingDisplayModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return PingDisplayConfigScreen::create;
    }
}