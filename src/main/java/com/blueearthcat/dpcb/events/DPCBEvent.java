package com.blueearthcat.dpcb.events;

import com.blueearthcat.dpcb.ConsumeBox;
import com.blueearthcat.dpcb.box.GiftBox;
import com.blueearthcat.dpcb.functions.DPCBFunction;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.lang.DLang;
import com.darksoldier1404.dppc.utils.NBT;
import com.darksoldier1404.dppc.utils.Quadruple;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import static com.blueearthcat.dpcb.box.enums.BoxType.*;
import static com.blueearthcat.dpcb.functions.DPCBFunction.*;

public class DPCBEvent implements Listener {
    private final ConsumeBox plugin = ConsumeBox.getInstance();
    private final String prefix = plugin.data.getPrefix();
    private final DLang lang = plugin.data.getLang();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) return;
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;
        if (e.getItem() == null) return;
        ItemStack item = e.getItem();
        if (NBT.hasTagKey(item, "dpcb_coupon")) {
            String name = NBT.getStringTag(item, "dpcb_coupon");
            e.setCancelled(true);
            if (!isBoxExist(name)) {
                e.getPlayer().sendMessage(prefix + lang.get("box_not_exists"));
                return;
            }
            GiftBox box = DPCBFunction.getBox(name);
            if (box.getType() == ERROR) {
                e.getPlayer().sendMessage(prefix + lang.get("box_wrong_type"));
                return;
            }
            givePrize(e.getPlayer(), name, item);

        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (!(e.getInventory() instanceof DInventory)) return;
        DInventory inv = (DInventory) e.getInventory();
        if (!inv.isValidHandler(plugin)) return;
        switch (inv.getChannel()) {
            case 0:
                if (e.getCurrentItem() == null) return;
                if (NBT.hasTagKey(e.getCurrentItem(), "current") || NBT.hasTagKey(e.getCurrentItem(), "pane")) {
                    e.setCancelled(true);
                    return;
                }
                if (NBT.hasTagKey(e.getCurrentItem(), "prev")) {
                    e.setCancelled(true);
                    ItemStack[] currentPageItems = inv.getContents();
                    inv.setPageContent(inv.getCurrentPage(), currentPageItems);
                    inv.prevPage();
                    DPCBFunction.updateCurrentPage(inv);
                    return;
                }
                if (NBT.hasTagKey(e.getCurrentItem(), "next")) {
                    e.setCancelled(true);
                    ItemStack[] currentPageItems = inv.getContents();
                    inv.setPageContent(inv.getCurrentPage(), currentPageItems);
                    inv.nextPage();
                    DPCBFunction.updateCurrentPage(inv);
                    return;
                }
                return;
            case 1: //coupon
                if (e.getClickedInventory() == null) return;
                if (e.getSlot() != 13 && e.getClickedInventory().getType() != InventoryType.PLAYER)
                    e.setCancelled(true);
                return;
            case 2: //list
                if (e.getCurrentItem() == null) return;
                e.setCancelled(true);
                if (NBT.hasTagKey(e.getCurrentItem(), "dpcb_coupon")) {
                    String name = NBT.getStringTag(e.getCurrentItem(), "dpcb_coupon");
                    p.closeInventory();
                    DPCBFunction.setGiftBoxItem(p, name);
                }
                return;
            case 3: //select
                e.setCancelled(true);
                if (e.getClickedInventory() == null) return;
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
                ItemStack item = e.getCurrentItem();
                if (NBT.hasTagKey(item, "ban")) return;
                if (inv.getObj() == null) return;
                Quadruple<ItemStack[], String, ItemStack, Integer> datas = (Quadruple<ItemStack[], String, ItemStack, Integer>)inv.getObj();

                if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                    if (NBT.hasTagKey(item, "dpcb_number")) {
                        int page = NBT.getIntegerTag(item, "dpcb_number") / 45;
                        int slot = NBT.getIntegerTag(item, "dpcb_number") - 45 * page;
                        inv.setCurrentPage(page);
                        inv.setItem(slot, item);
                        item.setType(Material.AIR);
                        datas.setD(datas.getD() + 1);
                        e.getClickedInventory().setItem(e.getSlot(), null);
                        updateCurrentPage2(inv, datas.getB(), datas.getD());
                        inv.update();
                    }
                } else {
                    if (NBT.hasTagKey(item, "dpcb_number")) {
                        int page = NBT.getIntegerTag(item, "dpcb_number") / 45;
                        if (datas.getD() == 0) {
                            p.sendMessage(prefix + lang.get("box_max_selected"));
                            return;
                        }
                        p.getInventory().addItem(item);
                        inv.setCurrentPage(page);
                        inv.setItem(e.getSlot(), getSelectedItem());
                        datas.setD(datas.getD() - 1);
                        updateCurrentPage2(inv, datas.getB(), datas.getD());
                        inv.update();
                    }
                    if (NBT.hasTagKey(item, "dpcb_select")){
                        if (datas.getD() != 0){
                            p.sendMessage(prefix + lang.get("box_not_selected"));
                            return;
                        }
                        ItemStack[] items = datas.getA();
                        int j = 0;
                        for (ItemStack i : items){
                            if (i== null) continue;
                            if (NBT.hasTagKey(i, "dpcb_number")){
                                items[j] = NBT.removeTag(i, "dpcb_number");
                            }
                            j++;
                        }
                        for (int i = 0; i < p.getInventory().getStorageContents().length; i++) {
                            if(p.getInventory().getStorageContents()[i] == null) continue;
                            p.getInventory().setItem(i, null);
                        }
                        for (ItemStack i : items) {
                            if (i != null){
                                if (i == datas.getC()) i.setAmount(i.getAmount() - 1);
                                p.getInventory().addItem(i);
                            }
                        }
                        datas.setA(null);
                        p.closeInventory();
                        p.sendMessage(prefix + datas.getB() + lang.get("box_select_give"));
                    }

                }
                return;
            default:
                break;
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (!(e.getInventory() instanceof DInventory)) return;
        DInventory inv = (DInventory) e.getInventory();
        Player p = (Player) e.getPlayer();
        if (!inv.isValidHandler(plugin)) return;
        if (inv.getChannel() == 0) { // item edit mode save
            DPCBFunction.saveBoxItems((Player) e.getPlayer(), (String) inv.getObj(), inv);
        }
        if (inv.getChannel() == 1) {
            DPCBFunction.saveCouponItem((Player) e.getPlayer(), (String) inv.getObj(), inv);
        }
        if (inv.getChannel() == 3){
            if (inv.getObj() == null) return;
            Quadruple<ItemStack[], String, ItemStack, Integer> datas = (Quadruple<ItemStack[], String, ItemStack, Integer>) inv.getObj();
            if (datas.getA() == null) return;
            else {
                if (datas.getD() == 0) {
                    ItemStack[] items = datas.getA();
                    int j = 0;
                    for (ItemStack i : items){
                        if (i== null) continue;
                        if (NBT.hasTagKey(i, "dpcb_number")){
                            items[j] = NBT.removeTag(i, "dpcb_number");
                        }
                        j++;
                    }
                    for (int i = 0; i < p.getInventory().getStorageContents().length; i++) {
                        if(p.getInventory().getStorageContents()[i] == null) continue;
                        p.getInventory().setItem(i, null);
                    }
                    for (ItemStack i : items) {
                        if (i != null){
                            if (i == datas.getC()) i.setAmount(i.getAmount() - 1);
                            p.getInventory().addItem(i);
                        }
                    }
                    p.sendMessage(prefix + lang.get("box_select_give"));
                } else {
                    p.getInventory().clear();
                    for (ItemStack i : datas.getA()) {
                        if (i != null) p.getInventory().addItem(i);
                    }
                    p.sendMessage(prefix + lang.get("box_selected_cancel"));
                }
            }
        }
    }
}
