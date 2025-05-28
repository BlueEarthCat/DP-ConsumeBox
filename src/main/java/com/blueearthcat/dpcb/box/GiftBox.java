package com.blueearthcat.dpcb.box;

import com.blueearthcat.dpcb.box.enums.BoxType;
import com.darksoldier1404.dppc.api.inventory.DInventory;
import com.darksoldier1404.dppc.utils.NBT;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GiftBox implements Cloneable{
    private String name;
    private BoxType type;
    private int drops = 1;
    private Map<Integer, ItemStack[]> items; // <page, pageContent>
    private int maxPage = 0;
    private ItemStack couponItem = new ItemStack(Material.PAPER);


    public GiftBox() {
    }

    public GiftBox(String name, BoxType type) {
        this.name = name;
        this.type = type;
        items = new HashMap<>();
    }

    public GiftBox(String name, BoxType type, int drops) {
        this.name = name;
        this.type = type;
        this.drops = drops;
        items = new HashMap<>();
    }

    public ItemStack getCouponItem() {
        return NBT.setStringTag(couponItem, "dpcb_coupon", name);
    }

    public void setCouponItem(ItemStack couponItem) {
        this.couponItem = couponItem;
    }

    public Map<Integer, ItemStack[]> getItems() {
        return items;
    }

    public void setItems(DInventory inv) {
        for (int i=0; i < inv.getPages() + 1; i++ ){
            inv.setCurrentPage(i);
            inv.update();
            ItemStack[] contents = new ItemStack[45];
            for(int slot = 0; slot < inv.getContents().length - 9; slot++) {
                contents[slot] = inv.getContents()[slot];
            }
            items.put(i, contents);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoxType getType() {
        return type;
    }

    public void setType(BoxType type) {
        this.type = type;
    }

    public int getDrops() {
        return drops;
    }

    public void setDrops(int drops) {
        this.drops = drops;
    }

    public YamlConfiguration serialize() {
        YamlConfiguration data = new YamlConfiguration();
        data.set("Box.Name", name);
        data.set("Box.Type", type.toString());
        data.set("Box.Drops", drops);
        data.set("Box.MaxPage", maxPage);
        data.set("Box.CouponItem", couponItem);
        if (items.isEmpty()) {
            data.set("Box.Items", null);
            return data;
        }
        for (int page : items.keySet()) {
            for (int i = 0; i < items.get(page).length; i++) {
                data.set("Box.Items." + page + "." + i, items.get(page)[i]);
            }
        }
        return data;
    }

    public GiftBox deserialize(YamlConfiguration data) {
        GiftBox box = new GiftBox(data.getString("Box.Name"), BoxType.fromString(data.getString("Box.Type")), data.getInt("Box.Drops"));
        box.setMaxPage(data.getInt("Box.MaxPage"));
        box.setCouponItem(data.getItemStack("Box.CouponItem"));
        if (data.getConfigurationSection("Box.Items") == null) return box;
        for (String key : data.getConfigurationSection("Box.Items").getKeys(false)) {
            ItemStack[] items = new ItemStack[45];
            for (String key1 : data.getConfigurationSection("Box.Items." + key).getKeys(false)) {
                items[Integer.parseInt(key1)] = data.getItemStack("Box.Items." + key + "." + key1);
            }
            box.items.put(Integer.parseInt(key), items);
        }
        return box;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    @Override
    public GiftBox clone() {
        try {
            GiftBox clone = (GiftBox) super.clone();

            // 1. couponItem 복사
            clone.couponItem = this.couponItem.clone();

            // 2. items 깊은 복사
            Map<Integer, ItemStack[]> newItems = new HashMap<>();
            for (Map.Entry<Integer, ItemStack[]> entry : this.items.entrySet()) {
                ItemStack[] originalArray = entry.getValue();
                ItemStack[] clonedArray = new ItemStack[originalArray.length];
                for (int i = 0; i < originalArray.length; i++) {
                    clonedArray[i] = originalArray[i] != null ? originalArray[i].clone() : null;
                }
                newItems.put(entry.getKey(), clonedArray);
            }
            clone.items = newItems;

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
