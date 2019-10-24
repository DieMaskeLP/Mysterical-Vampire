package de.nexxus.vampire.utils;

//Created by MrKompetnz on 10/22/19

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(String displayname, Material material, short subID, int amount) {
        item = new ItemStack(material, amount, subID);
        itemMeta = item.getItemMeta();
        item.setAmount(amount);
        itemMeta.setDisplayName(displayname);
    }

    public ItemBuilder(String displayname, Material material, int amount) {
        this(displayname, material, (short) 0, amount);
    }

    public ItemBuilder setEnchantment(Enchantment enchantment, int level, boolean b) {
        itemMeta.addEnchant(enchantment, level, b);
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
