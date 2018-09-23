package com.gmail.therealkingvictoria.multiblock;

import java.util.List;

import org.bukkit.inventory.ItemStack;

/**
 * A blueprint for a Structure
 * @author KingVictoria
 */
public class MultiBlock {
	
	private List<Structure> structures;
	private ItemStack base;
	
	/**
	 * Adds a reference to a physical Structure of this MultiBlock to the list
	 * @param structure Structure to add
	 */
	public void addStructure(Structure structure) { 
		structures.add(structure);
	} // addStructure
	
	/**
	 * Removes a reference to a physical Structure of this MultiBlock from the list
	 * @param structure Structure to remove
	 */
	public void removeStructure(Structure structure) {
		structures.remove(structure);
	} // removeStructure
	
	/**
	 * Gets the base block of the MultiBlock as an ItemStack
	 * @return ItemStack base block
	 */
	public ItemStack getBase() { 
		return base; 
	} // getBase

} // class
