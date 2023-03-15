package org.example.mastaa.unr;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AdminPasswordCommand extends Command {

    private Core core;

    public AdminPasswordCommand(Core core) {
        super("admpass");
        this.core = core;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (player.hasPermission("bungeecord.command.server")) {
                if (args.length == 1) {
                    String inputPassword = args[0];
                    String correctPassword = core.getOperatorPassword();
                    if (inputPassword.equals(correctPassword)) {
                        player.sendMessage(new TextComponent("§7[§cCore§7]§f Пароль верный. Вы успешно вошли как оператор."));
                        core.getOperatorChecker().getTelegramBot().sendSuccessfulLoginMessage(player.getName(), player.getAddress().getAddress().getHostAddress(), "Регион IP здесь");
                        core.getOperatorChecker().resetAttempts(player);
                    } else {
                        player.sendMessage(new TextComponent("§7[§cCore§7]§f Неверный пароль. Попробуйте снова."));
                        core.getOperatorChecker().incrementAttempts(player);
                        core.getOperatorChecker().getTelegramBot().sendIncorrectPasswordMessage(player.getName()); // Добавлена строка
                        if (core.getOperatorChecker().getAttempts(player) >= core.getOperatorChecker().getMaxAttempts()) {
                            player.disconnect(new TextComponent("§7[§cCore§7]§f Вы были кикнуты с сервера."));
                            core.getOperatorChecker().getTelegramBot().sendSuspiciousActivityMessage(player.getName(), player.getAddress().getAddress().getHostAddress());
                        }
                    }
                } else {
                    player.sendMessage(new TextComponent("§7[§cCore§7]§f Используйте: /admpass <пароль>"));
                }
            }
        } else {
            sender.sendMessage(new TextComponent("§7[§cCore§7]§f Эта команда доступна только для игроков."));
        }
    }
}
