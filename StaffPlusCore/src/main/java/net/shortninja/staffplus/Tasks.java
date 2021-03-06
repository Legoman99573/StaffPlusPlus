package net.shortninja.staffplus;

import net.shortninja.staffplus.session.PlayerSession;
import net.shortninja.staffplus.staff.mode.handler.GadgetHandler;
import net.shortninja.staffplus.staff.alerts.AlertCoordinator;
import net.shortninja.staffplus.server.data.config.Messages;
import net.shortninja.staffplus.server.data.config.Options;
import net.shortninja.staffplus.session.SessionManager;
import net.shortninja.staffplus.staff.freeze.FreezeHandler;
import net.shortninja.staffplus.unordered.IWarning;
import net.shortninja.staffplus.util.MessageCoordinator;
import net.shortninja.staffplus.util.PermissionHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

//TODO: Remove debug.

public class Tasks extends BukkitRunnable {
    private final PermissionHandler permission = IocContainer.getPermissionHandler();
    private final MessageCoordinator message = IocContainer.getMessage();
    private final Options options = IocContainer.getOptions();
    private final Messages messages = IocContainer.getMessages();
    private final SessionManager sessionManager = IocContainer.getSessionManager();
    private final FreezeHandler freezeHandler = IocContainer.getFreezeHandler();
    private final GadgetHandler gadgetHandler = StaffPlus.get().gadgetHandler;
    private final AlertCoordinator alertCoordinator = IocContainer.getAlertCoordinator();
    private int saveInterval;
    private int freezeInterval;
    private long now;
    private long later;

    public Tasks() {
        saveInterval = 0;
        freezeInterval = 0;
        now = System.currentTimeMillis();
        runTaskTimer(StaffPlus.get(), options.clock, options.clock);
    }

    @Override
    public void run() {
        checkWarnings();
        decideAutosave();
        freezeHandler.checkLocations();
        gadgetHandler.updateGadgets();
    }

    private void checkWarnings() {
        for (IWarning warning : IocContainer.getWarnService().getWarnings()) {
            if (warning.shouldRemove()) {
                IocContainer.getWarnService().removeWarning(warning.getId());
            }
        }
    }

    private void decideAutosave() {
        later = System.currentTimeMillis();

        if ((later - now) >= 1000) {
            int addition = (int) ((later - now) / 1000);
            saveInterval += addition;
            freezeInterval += addition;
            now = System.currentTimeMillis();
        }

        if (saveInterval >= options.autoSave && saveInterval > 0) {
            StaffPlus.get().saveUsers();
            IocContainer.getMessage().sendConsoleMessage("Staff++ is now auto saving...", false);
            alertCoordinator.clearNotified();
            saveInterval = 0;
        } else if (options.autoSave <= 0 && saveInterval >= 1800) {
            alertCoordinator.clearNotified();
        }

        if (freezeInterval >= options.modeFreezeTimer && freezeInterval > 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                PlayerSession user = sessionManager.get(player.getUniqueId());
                if (user.isFrozen() && !permission.has(player, options.permissionMember)) {
                    options.modeFreezeSound.play(player);

                    if (!options.modeFreezePrompt) {
                        message.sendCollectedMessage(player, messages.freeze, messages.prefixGeneral);
                    }
                }
            }

            freezeInterval = 0;
        }
    }
}