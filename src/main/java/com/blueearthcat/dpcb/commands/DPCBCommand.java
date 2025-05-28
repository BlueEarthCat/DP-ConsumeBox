package com.blueearthcat.dpcb.commands;

import com.blueearthcat.dpcb.ConsumeBox;
import com.blueearthcat.dpcb.functions.DPCBFunction;
import com.darksoldier1404.dppc.builder.command.CommandBuilder;
import com.darksoldier1404.dppc.lang.DLang;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DPCBCommand {
    private final String prefix;
    private final ConsumeBox plugin = ConsumeBox.getInstance();
    private final DLang lang;
    private final CommandBuilder builder;

    public DPCBCommand() {
        prefix = plugin.data.getPrefix();
        lang = plugin.data.getLang();
        builder = new CommandBuilder(prefix);

        builder.addSubCommand("create", "dpcb.create", lang.get("help_create"), true, (p, args) -> {
            if (args.length == 3) DPCBFunction.createBox((Player) p, args[1], args[2]);
            else p.sendMessage(prefix + lang.get("help_create"));
        });
        builder.addSubCommand("item", "dpcb.item", lang.get("help_item"), true, (p, args) -> {
            if (args.length == 2) DPCBFunction.setGiftBoxItem((Player) p, args[1]);
            else p.sendMessage(prefix + lang.get("help_item"));
        });
        builder.addSubCommand("coupon", "dpcb.coupon", lang.get("help_coupon"), true, (p, args) -> {
            if (args.length == 2) DPCBFunction.setCouponItem((Player) p, args[1]);
            else p.sendMessage(prefix + lang.get("help_coupon"));
        });
        builder.addSubCommand("type", "dpcb.type", lang.get("help_type"), true, (p, args) -> {
            if (args.length == 3) DPCBFunction.setGiftBoxType((Player) p, args[1], args[2]);
            else p.sendMessage(prefix + lang.get("help_type"));
        });
        builder.addSubCommand("give", "dpcb.give", lang.get("help_give"), false, (p, args) -> {
            if (args.length == 2) {
                if (!(p instanceof Player)){
                    p.sendMessage(prefix + lang.get("player_only"));
                    return;
                }
                DPCBFunction.giveGiftBox(p, args[1], (Player) p);
            }
            else if (args.length == 3) DPCBFunction.giveGiftBox(p, args[2],DPCBFunction.getPlayer(args[2]));
            else p.sendMessage(prefix + lang.get("help_give"));
        });
        builder.addSubCommand("list", "dpcb.list", lang.get("help_list"), true, (p, args) -> {
            if (args.length == 1) DPCBFunction.getBoxList((Player)p);
            else p.sendMessage(prefix + lang.get("help_list"));
        });
        builder.addSubCommand("delete", "dpcb.delete", lang.get("help_delete"), true, (p, args) -> {
            if (args.length == 2) DPCBFunction.deleteGiftBox((Player)p, args[1]);
            else p.sendMessage(prefix + lang.get("help_delete"));
        });
        builder.addSubCommand("drop", "dpcb.drop", lang.get("help_drop"), true, (p, args) -> {
            if (args.length == 3) DPCBFunction.setGiftBoxDrop((Player)p, args[1], args[2]);
            else p.sendMessage(prefix + lang.get("help_drop"));
        });
        builder.addSubCommand("page", "dpcb.page", lang.get("help_page"), true, (p, args) -> {
            if (args.length == 3) DPCBFunction.setGiftBoxPage((Player)p, args[1], args[2]);
            else p.sendMessage(prefix + lang.get("help_page"));
        });
        builder.addSubCommand("reload", "dpcb.reload", lang.get("help_reload"), false, (p, args) -> {
            if (args.length == 1) DPCBFunction.reloadConfig(p);
            else p.sendMessage(prefix + lang.get("help_reload"));
        });
        List<String> commands = Arrays.asList("create", "item", "delete", "type", "give", "coupon", "drop", "maxpage");
        for (String c: commands) {
            builder.addTabCompletion(c, args -> new ArrayList<>(plugin.boxes.keySet()));
        }

    }

    public CommandExecutor getExecuter() {
        return builder;
    }
}
