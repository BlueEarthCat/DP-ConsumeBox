package com.blueearthcat.dpcb;

import com.blueearthcat.dpcb.box.GiftBox;
import com.blueearthcat.dpcb.commands.DPCBCommand;
import com.blueearthcat.dpcb.events.DPCBEvent;
import com.blueearthcat.dpcb.functions.DPCBFunction;
import com.darksoldier1404.dppc.utils.DataContainer;
import com.darksoldier1404.dppc.utils.PluginUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class ConsumeBox extends JavaPlugin {
    private static ConsumeBox plugin;
    public static DataContainer data;
    public static Map<String, GiftBox> boxes = new HashMap<>();

    public static ConsumeBox getInstance() {
        return plugin;
    }

    @Override
    public void onLoad() {
        plugin = this;
        PluginUtil.addPlugin(plugin, 25979);
    }

    @Override
    public void onEnable() {
        data = new DataContainer(plugin, true);
        plugin.getServer().getPluginManager().registerEvents(new DPCBEvent(), plugin);
        getCommand("dpcb").setExecutor(new DPCBCommand().getExecuter());
        DPCBFunction.init();
    }

    @Override
    public void onDisable() {
        data.save();
        DPCBFunction.saveAllBox();
    }
}
