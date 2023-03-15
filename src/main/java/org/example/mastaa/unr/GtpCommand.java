package org.example.mastaa.unr;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class GtpCommand extends Command {
    private Core core;

    public GtpCommand() {
        super("gtp");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (args.length == 1) {
                String targetServerName = args[0].toLowerCase();
                if (ProxyServer.getInstance().getServers().containsKey(targetServerName)) {
                    if (!player.getServer().getInfo().getName().equalsIgnoreCase(targetServerName)) {
                        player.connect(ProxyServer.getInstance().getServerInfo(targetServerName));
                        player.sendMessage(new TextComponent("§7[§cCore§7]§f Вы были перемещены на сервер: " + targetServerName));
                    } else {
                        player.sendMessage(new TextComponent("§7[§cCore§7]§f Вы уже находитесь на этом сервере."));
                    }
                } else {
                    player.sendMessage(new TextComponent("§7[§cCore§7]§f Неверное название сервера."));
                }
            } else {
                player.sendMessage(new TextComponent("§7[§cCore§7]§f /gtp §7[§cSERVER_NAME§7]"));
            }
        } else {
            sender.sendMessage(new TextComponent("§7[§cCore§7]§f Эта команда доступна только для игроков."));
        }
    }
}
