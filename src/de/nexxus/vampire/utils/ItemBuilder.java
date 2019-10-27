package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/22/19

import net.minecraft.server.v1_8_R3.Item;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(String displayName, Material material, short subID, int amount) {
        item = new ItemStack(material, amount, subID);
        itemMeta = item.getItemMeta();
        item.setAmount(amount);
        itemMeta.setDisplayName(displayName);
    }

    public ItemBuilder(String displayname, Material material, int amount) {
        this(displayname, material, (short) 0, amount);
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, int level, boolean b) {
        itemMeta.addEnchant(enchantment, level, b);
        return this;
    }

    public ItemBuilder setDisplayName(String displayName){
        itemMeta.setDisplayName(displayName);
        return this;
    }

    public ItemBuilder setItemBuilder(String displayname, Material material, int amount){
        item = new ItemStack(material, amount, (short) 0);
        itemMeta = item.getItemMeta();
        item.setAmount(amount);
        itemMeta.setDisplayName(displayname);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(itemMeta);
        return item;
    }
}
