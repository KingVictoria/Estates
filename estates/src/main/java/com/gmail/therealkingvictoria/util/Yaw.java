package com.gmail.therealkingvictoria.util;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 * Cardinals
 * @author KingVictoria
 */
public enum Yaw {
	SOUTH, WEST, NORTH, EAST;
	
	/**
	 * Gets the Yaw facing direction of a Player
	 * @param player Player to get facing cardinal of
	 * @return Yaw
	 */
	public static Yaw get(Player player) {
	    float yaw = player.getLocation().getYaw();
	    if (yaw < 0) yaw += 360;
	    
	    if (yaw >= 315 || yaw < 45) return Yaw.SOUTH;
	    else if (yaw < 135) return Yaw.WEST;
	    else if (yaw < 225) return Yaw.NORTH;
	    else if (yaw < 315) return Yaw.EAST;
	    else return Yaw.NORTH;
	} // get(Player)
	
	/**
	 * Gets the Yaw from a BlockFace direction (defaults to WEST if BlockFace is not NESW)
	 * @param face BlockFace to get Yaw from
	 * @return Yaw cardinal direction
	 */
	public static Yaw get(BlockFace face) {
		switch(face) {
			case NORTH: return NORTH;
			case SOUTH: return SOUTH;
			case EAST: 	return EAST;
			default:	return WEST;
		} // switch
	} // get(BlockFace)
	
	/**
	 * Gets the opposite cardinal Yaw to another Yaw
	 * @param yaw Yaw to get the opposite of
	 * @return Yaw opposite cardinal
	 */
	public static Yaw opposite(Yaw yaw) {
		switch(yaw) {
			case SOUTH: return NORTH;
			case NORTH: return SOUTH;
			case EAST:  return WEST;
			default:    return EAST;
		} // switch
	} // opposite(Yaw)
	
	/**
	 * Gets the next cardinal, clockwise as Yaw, of a given Yaw
	 * @param yaw Yaw input
	 * @return Yaw next cardinal, clockwise
	 */
	public static Yaw next(Yaw yaw) {
		switch(yaw) {
			case SOUTH: return WEST;
			case NORTH: return EAST;
			case EAST:  return SOUTH;
			default:    return NORTH;
		} // switch
	} // next(Yaw)
	
	/**
	 * Gets the next cardinal, clockwise as Yaw, of this Yaw
	 * @return Yaw next cardinal, clockwise
	 */
	public Yaw next() {
		return next(this);
	} // next
	
	/**
	 * Gets the opposite cardinal Yaw of this Yaw
	 * @return Yaw opposite cardinal
	 */
	public Yaw opposite() {
		return opposite(this);
	} // opposite
	
	/**
	 * Gets the BlockFace equivalent of this Yaw
	 * @return BlockFace
	 */
	public BlockFace toBlockFace() {
		switch(this) {
			case NORTH: return BlockFace.NORTH;
			case SOUTH: return BlockFace.SOUTH;
			case EAST:  return BlockFace.EAST;
			default:    return BlockFace.WEST;
		} // switch
	} // toBlockFace
} // enum