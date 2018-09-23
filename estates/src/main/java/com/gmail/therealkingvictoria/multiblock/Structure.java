package com.gmail.therealkingvictoria.multiblock;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

/**
 * A specific MultiBlock Structure that exists in the world representing an Estate or Module
 * @author KingVictoria
 */
public class Structure {
	
	private MultiBlock multiBlock;
	private Location location;
	private int health;
	
	/**
	 * Creates a Structure of a MultiBlock, a physical instance of a type of MultiBlock
	 * @param multiBlock the type of MultiBlock this structure is
	 * @param location Location of MultiBlock
	 * @param event BlockPlaceEvent that created this structure (all structures are made by placing a base block)
	 */
	public Structure(MultiBlock multiBlock, Location location, BlockPlaceEvent event) {
		this.multiBlock = multiBlock;
		this.location = location;
		
		if(!build()) {
			event.setCancelled(true);
			return;
		} // if
		
		multiBlock.addStructure(this);
	} // Structure(MultiBlock, Location, BlockPlaceEvent)
	
	/**
	 * Loads a Structure of a MultiBlock that already exists
	 * @param multiBlock kind of MultiBlock that this Structure is
	 * @param location Location of Structure
	 * @param health int health of the structure
	 */
	public Structure(MultiBlock multiBlock, Location location, int health) {
		this.multiBlock = multiBlock;
		this.location = location;
		this.health = health;
		
		multiBlock.addStructure(this);
	} // Structure(MultiBlock, Location, int)

	/** Unimplemented */
	public boolean build() { return false; } // build
	/** Unimplemented */
	public boolean destroy() { return false; } // destroy
	/** Unimplemented */
	public boolean destroy(Player player) { return false; } // destroy(Player)
	/** Unimplemented */
	public Location partOf(Block block) { return null; } // partOf
	
	/**
	 * Sets the health of this structure (when health reaches zero it is destroyed)
	 * @param health int number to set health to
	 */
	public void setHealth(int health) { 
		this.health = health; 
	} // setHealth
	
	/**
	 * Gets the health of this structure (when health reaches zero it is destroyed)
	 * @return int health
	 */
	public int getHealth() { 
		return health; 
	} // getHealth
	
	/**
	 * Gets the Location of this Structure
	 * @return Location of structure
	 */
	public Location getLocation() { 
		return location; 
	} // getLocation
	
	/**
	 * Gets the base block of this Structure's MultiBlock as an ItemStack
	 * @return ItemStack base block
	 */
	public ItemStack getBase() {
		return multiBlock.getBase();
	} // getBase

} // class