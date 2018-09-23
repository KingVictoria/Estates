package com.gmail.therealkingvictoria.multiblock;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

/**
 * A blueprint for a Structure
 * @author KingVictoria
 */
public class MultiBlock {
	
	private String name;
	private List<Structure> structures;
	private ItemStack base;
	
	/**
	 * Creates a MultiBlock
	 * @param name String name (must be unique!)
	 * @param structures List of Structures of this MultiBlock
	 * @param base ItemStack of base block for this MultiBlock
	 */
	public MultiBlock(String name, List<Structure> structures, ItemStack base) {
		this.name = name;
		this.structures = structures;
		this.base = base;
	} // MultiBlock
	
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
	 * Gets a Structure by its location
	 * @param location Location of Structure
	 * @return null if unable to find Structure
	 */
	public Structure getByLocation(Location location) {
		for(Structure structure: structures) if(structure.getLocation().equals(location)) return structure;
		
		return null;
	} // getByLocation
	
	/**
	 * Gets the base block of the MultiBlock as an ItemStack
	 * @return ItemStack base block
	 */
	public ItemStack getBase() { 
		return base; 
	} // getBase
	
	/**
	 * Gets the List of Structures of this MultiBlock
	 * @return List of Structure
	 */
	public List<Structure> getStructures() {
		return structures;
	} // getStructures
	
	/**
	 * Gets the unique name of this MultiBlock
	 * @return String name
	 */
	public String getName() {
		return name;
	} // getName
	
	/**
	 * Sets the name of this MultiBlock (must be unique!)
	 * @param name String name
	 */
	public void setName(String name) {
		this.name = name;
	} // setName

} // class
