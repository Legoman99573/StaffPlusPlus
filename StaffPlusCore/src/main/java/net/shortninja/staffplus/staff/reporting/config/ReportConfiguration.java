package net.shortninja.staffplus.staff.reporting.config;

import net.shortninja.staffplus.common.config.GuiItemConfig;
import net.shortninja.staffplus.util.lib.Sounds;

public class ReportConfiguration {

    private final boolean enabled;
    private final int cooldown;
    private final boolean showReporter;
    private final Sounds sound;
    private final boolean closingReasonEnabled;
    private final GuiItemConfig openReportsGui;
    private final GuiItemConfig myReportsGui;
    private final GuiItemConfig closedReportsGui;


    public ReportConfiguration(boolean enabled,
                               int cooldown,
                               boolean showReporter, Sounds sound,
                               boolean closingReasonEnabled,
                               GuiItemConfig openReportsGui,
                               GuiItemConfig myReportsGui,
                               GuiItemConfig closedReportsGui
    ) {
        this.enabled = enabled;
        this.cooldown = cooldown;
        this.showReporter = showReporter;
        this.sound = sound;
        this.closingReasonEnabled = closingReasonEnabled;
        this.openReportsGui = openReportsGui;
        this.myReportsGui = myReportsGui;
        this.closedReportsGui = closedReportsGui;
    }

    public boolean isClosingReasonEnabled() {
        return closingReasonEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getCooldown() {
        return cooldown;
    }

    public boolean isShowReporter() {
        return showReporter;
    }

    public Sounds getSound() {
        return sound;
    }

    public GuiItemConfig getOpenReportsGui() {
        return openReportsGui;
    }

    public GuiItemConfig getMyReportsGui() {
        return myReportsGui;
    }

    public GuiItemConfig getClosedReportsGui() {
        return closedReportsGui;
    }
}
