package net.shortninja.staffplus.server.command.cmd;

import net.shortninja.staffplus.IocContainer;
import net.shortninja.staffplus.StaffPlus;
import net.shortninja.staffplus.server.data.config.Messages;
import net.shortninja.staffplus.server.data.config.Options;
import net.shortninja.staffplus.util.MessageCoordinator;
import net.shortninja.staffplus.util.PermissionHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.Collections;
import java.util.List;

import static net.shortninja.staffplus.IocContainer.getMessages;

public class StaffPlusCmd extends BukkitCommand {
    private PermissionHandler permission = IocContainer.getPermissionHandler();
    private MessageCoordinator message = IocContainer.getMessage();
    private Options options = IocContainer.getOptions();
    private Messages messages = getMessages();

    public StaffPlusCmd(String name) {
        super(name);
    }

    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (!permission.has(sender, options.permissionStaff)) {
            message.send(sender, messages.noPermission, messages.prefixGeneral);
            return true;
        }
        if (args.length == 1 && permission.has(sender, options.permissionStaff)) {
            if (args[0].equalsIgnoreCase("reload")) {
                IocContainer.getMessage().sendConsoleMessage("This feature is disabled until we have implemented a robust way of reloading", true);
            }
        }
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            return Collections.singletonList("reload");
        }

        return super.tabComplete(sender, alias, args);
    }
}
