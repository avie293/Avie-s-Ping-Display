package com.avie29.pingdisplay.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.multiplayer.PlayerInfo;
import com.avie29.pingdisplay.PingDisplayConfig;

public class PingDisplayRender {

    public static void renderPingText(Minecraft mc, GuiGraphicsExtractor context, int width, int x, int y, PlayerInfo entry) {
        PingDisplayConfig cfg = PingDisplayConfig.get();
        int ping = entry.getLatency();

        String pingText;
        int color;

        if (ping < 0) {
            pingText = "N/A";
            color = PingDisplayConfig.parseColor(cfg.pingColorUnknown);
        } else {
            pingText = cfg.showMs ? ping + "ms" : String.valueOf(ping);
            if      (ping < 50)  color = PingDisplayConfig.parseColor(cfg.pingColorGood);
            else if (ping < 100) color = PingDisplayConfig.parseColor(cfg.pingColorOk);
            else if (ping < 200) color = PingDisplayConfig.parseColor(cfg.pingColorBad);
            else                 color = PingDisplayConfig.parseColor(cfg.pingColorTerrible);
        }

        Font font = mc.font;
        int textX = x + width - font.width(pingText);
        context.text(font, pingText, textX, y, color, cfg.textShadow);
    }

    public static String getPingText(PingDisplayConfig cfg, int ping) {
        return ping < 0 ? "N/A" : (cfg.showMs ? ping + "ms" : String.valueOf(ping));
    }

    public static int getPingColor(PingDisplayConfig cfg, int ping) {
        return PingDisplayConfig.parseColor(
                ping < 0   ? cfg.pingColorUnknown  :
                        ping < 50  ? cfg.pingColorGood     :
                                ping < 100 ? cfg.pingColorOk       :
                                        ping < 200 ? cfg.pingColorBad      :
                                                cfg.pingColorTerrible
        );
    }
}