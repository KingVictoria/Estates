package com.gmail.therealkingvictoria.multiblock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gmail.therealkingvictoria.util.Yaw;

public class Schematic {
	
	Material[][][] materials;
	
	/**
	 * Creates a Schematic from the region in between two Block references (NOTE: ASSUMES FRONT OF SCHEMATIC IS FACING WEST)
	 * @param b1 Block reference 1
	 * @param b2 Block reference 2
	 */
	public Schematic(Block b1, Block b2) {
		
		World world = b1.getLocation().getWorld();
		
		int xMin = b1.getX() < b2.getX() ? b1.getX() : b2.getX();
		int yMin = b1.getY() < b2.getY() ? b1.getY() : b2.getY();
		int zMin = b1.getZ() < b2.getZ() ? b1.getZ() : b2.getZ();
		
		int xSize = xMin == b1.getX() ? b2.getX() - xMin : b1.getX() - xMin;
		int ySize = yMin == b1.getY() ? b2.getY() - xMin : b1.getY() - xMin;
		int zSize = zMin == b1.getZ() ? b2.getZ() - xMin : b1.getZ() - xMin;
		
		materials = new Material[xSize][ySize][zSize];
		
		Block block;
		for(int i = 0; i < xSize; i++) for(int j = 0; j < ySize; j++) for(int k = 0; k < zSize; k++) {
			block = world.getBlockAt(xMin+i, yMin+j, zMin+k);
			materials[i][j][k] = block.getType();
		} // for
	} // Schematic(Block,Block)
	
	/**
	 * Creates a Schematic from a loaded 3D Material array
	 * @param materials Material[][][] array
	 */
	public Schematic(Material[][][] materials) {
		this.materials = materials;
	} // Schematic(Material[][][])
	
	/**
	 * Attempts to build this schematic at a block placed location (block placed will be player-side middle of base)
	 * @param event BlockPlaceEvent invoked by placing the MultiBlock base to build this schematic
	 * @return false if something is blocking this schematic from being built
	 */
	public boolean build(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		Yaw facing = Yaw.get(player).opposite();
		
		int half = facing == Yaw.WEST || facing == Yaw.EAST ? materials[0][0].length / 2 : materials.length / 2;
		
		for(int i = 0; i < half; i++) block = block.getRelative(facing.next().toBlockFace());
		
		return build(block, facing);
	} // build(BlockPlaceEvent)
	
	/**
	 * Called by build(BlockPlaceEvent) to build a thing at a place by a Block and facing Yaw
	 * @param block Block
	 * @param facing Yaw cardinal front
	 * @return false if unable to build
	 */
	private boolean build(Block block, Yaw facing) {
		if(!buildable(block,facing)) return false;
		
		Location location = block.getLocation();
		World world = location.getWorld();
		
		int xSize = materials.length; 
		int ySize = materials[0].length;
		int zSize = materials[0][0].length;
		
		int xMin = block.getX();
		int yMin = block.getY();
		int zMin = block.getZ();
		
		for(int i = 0; i < xSize; i++) for(int j = 0; j < ySize; j++) for(int k = 0; k < zSize; k++) {
			if(facing == Yaw.WEST) 	block = world.getBlockAt(xMin+i, yMin+j, zMin+k);
			if(facing == Yaw.NORTH) block = world.getBlockAt(xMin+xSize-i, yMin+j, zMin+k);
			if(facing == Yaw.EAST) block = world.getBlockAt(xMin+xSize-i, yMin+j, zMin+zSize-k);
			if(facing == Yaw.EAST) block = world.getBlockAt(xMin+i, yMin+j, zMin+zSize-k);
			
			block.setType(materials[i][j][k]);
		} // for
		
		return true;
	} // build
	
	/**
	 * Tests if the location given is viable for building this schematic (block would be the bottom left of the front)
	 * @param block Block on the bottom left of the structure
	 * @return false if something is blocking it
	 */
	public boolean buildable(Block block, Yaw facing) {
		Location location = block.getLocation();
		World world = location.getWorld();
		
		int xSize = materials.length; 
		int ySize = materials[0].length;
		int zSize = materials[0][0].length;
		
		int xMin = block.getX();
		int yMin = block.getY();
		int zMin = block.getZ();
		
		for(int i = 0; i < xSize; i++) for(int j = 0; j < ySize; j++) for(int k = 0; k < zSize; k++) {
			if(facing == Yaw.WEST) 	block = world.getBlockAt(xMin+i, yMin+j, zMin+k);
			if(facing == Yaw.NORTH) block = world.getBlockAt(xMin+xSize-i, yMin+j, zMin+k);
			if(facing == Yaw.EAST) block = world.getBlockAt(xMin+xSize-i, yMin+j, zMin+zSize-k);
			if(facing == Yaw.EAST) block = world.getBlockAt(xMin+i, yMin+j, zMin+zSize-k);
			
			if(block.getType() != Material.AIR && materials[i][j][k] != Material.AIR) return false;
		} // for
		
		return true;
	} // buildable
	

} // class
