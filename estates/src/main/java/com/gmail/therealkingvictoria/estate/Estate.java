package com.gmail.therealkingvictoria.estate;

import org.bukkit.Location;
import org.bukkit.event.block.BlockPlaceEvent;

import com.gmail.therealkingvictoria.multiblock.MultiBlock;
import com.gmail.therealkingvictoria.multiblock.Structure;

public class Estate {
	
	private String name;
	private Location location;
	private Structure structure;
	
	Estate(String name, Location location, MultiBlock multiBlock, BlockPlaceEvent event) {
		this.name = name;
		this.location = location;

		structure = new Structure(multiBlock, location, event);
	} // Estate(Sting, Location, MultiBlock, BlockPlaceEvent)
	
	Estate(String name, Location location, Structure structure) {
		this.name = name;
		this.location = location;
		this.structure = structure;
	} // Estate(String, Location, Structure)

} // class
