package com.gmail.therealkingvictoria.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Used to describe the cost of something in Estates
 * @author KingVictoria
 */
public class Cost {
	
	private List<ItemInfo> cost;
	
	private class ItemInfo {
		public String name;
		public Material mat;
		public int amount;
	} // ItemInfo
	
	/**
	 * Creates a Cost object
	 * @param cost List of ItemInfo objects that describes the cost of an item
	 */
	public Cost(List<ItemInfo> cost) { 
		this.cost = cost;
	} // Cost
	
	/**
	 * Determines whether a group of items is sufficient for this Cost
	 * @param reserve Array of ItemStack objects that makes up the group of items
	 * @return false if the group of items is insufficient
	 */
	public boolean sufficient(ItemStack[] reserve) { 
		Map<String, Integer> amounts = new HashMap<>();
		
		// Gets the amounts of all of the items of interest
		getAmounts:
		for(ItemStack stack: reserve) {
			if(!stack.hasItemMeta()) continue;
			
			for(ItemInfo info: cost) {
				if(stack.getItemMeta().getDisplayName().equals(info.name)) {
					if(amounts.containsKey(info.name)) {
						amounts.put(info.name, amounts.get(info.name) + stack.getAmount());
					} else {
						amounts.put(info.name, stack.getAmount());
					} // if/else
					
					continue getAmounts;
				} // if
			} // for
		} // for
		
		// Determines whether there is enough of each item present
		for(ItemInfo info: cost) {
			if(!amounts.containsKey(info.name)) return false;
			if(amounts.get(info.name) < info.amount) return false;
		} // for
		
		return true;
	} // sufficient
	
	/**
	 * Attempts to evaluate the transaction of the cost
	 * @param reserve Array of ItemStack objects that makes up the group of items used to attempt a transaction
	 * @return false if the group of items is insufficient
	 */
	public boolean evaluate(ItemStack[] reserve) { 
		if(!sufficient(reserve)) return false;
		
		for(ItemStack stack: reserve) {
			if(!stack.hasItemMeta()) continue;
			
			for(ItemInfo info: cost) {
				if(info.amount <= 0) continue;
				if(!stack.getItemMeta().getDisplayName().equals(info.name)) continue;
				
				if(info.amount >= stack.getAmount()) {
					info.amount -= stack.getAmount();
					stack.setAmount(0);
				} else {
					stack.setAmount(stack.getAmount() - info.amount);
					info.amount = 0;
				} // if/else
			} // for
		} // for
		
		return true;
	} // evaluate
	
	/**
	 * Gets the Cost as an Array of ItemStack objects
	 * @return Array of ItemStack objects
	 */
	public ItemStack[] getCost() { 
		List<ItemStack> stacks = new ArrayList<>();
		
		for(ItemInfo info: cost) {
			while(info.amount > 64) {
				ItemStack stack = new ItemStack(info.mat);
				stack.setAmount(64);
				info.amount -= 64;
				
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(info.name);
				stack.setItemMeta(meta);
				
				stacks.add(stack);
			} // for
			
			if(info.amount > 0) {
				ItemStack stack = new ItemStack(info.mat);
				stack.setAmount(info.amount);
				info.amount = 0;
				
				ItemMeta meta = stack.getItemMeta();
				meta.setDisplayName(info.name);
				stack.setItemMeta(meta);
			}
		} // for
		
		return (ItemStack[]) stacks.toArray();
	} // getCost	

} // class