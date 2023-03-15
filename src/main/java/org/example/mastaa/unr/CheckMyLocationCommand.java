package org.example.mastaa.unr;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CheckMyLocationCommand extends Command {

    private Core core;

    public CheckMyLocationCommand() {
        super("checkmylocation");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            String serverName = player.getServer().getInfo().getName();
            player.sendMessage(new TextComponent("§7[§cCore§7]§f Вы находитесь на сервере: " + serverName));
        } else {
            sender.sendMessage(new TextComponent("§7[§cCore§7]§f Эта команда доступна только для игроков."));
        }
    }
}
