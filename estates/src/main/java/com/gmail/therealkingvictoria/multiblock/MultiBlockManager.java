package com.gmail.therealkingvictoria.multiblock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Manages MultiBlocks and their Structures
 * @author KingVictoria
 */
public class MultiBlockManager {
	
	private static File configFile;
	private static FileConfiguration config;

	public static List<MultiBlock> multiBlocks;

	/**
	 * Loads from config and creates the multiBlocks List
	 * @param file config File
	 * @author KingVictoria
	 */
	public static void init(File file) {
		configFile = file;

		try {
			config = YamlConfiguration.loadConfiguration(configFile);
			multiBlocks = loadMultiBlocks();
		} catch (IllegalArgumentException e1) {
			Bukkit.getLogger().severe("MultiBlock config does not exist! Creating...");
			try {
				configFile.createNewFile();
				multiBlocks = new ArrayList<>();
			} catch (IOException e2) {
				Bukkit.getLogger().severe("Unable to create MultiBlock config!");
				e2.printStackTrace();
			} // try/catch
		} // try/catch
	} // init
	
	public static List<MultiBlock> loadMultiBlocks() {
		List<MultiBlock> multiBlocks = new ArrayList<>();
		
		for(String multiBlockKey: config.getKeys(false)) {
			ConfigurationSection multiBlockConfig = config.getConfigurationSection(multiBlockKey);
			
			/* estateMultiBlock:
			 *   structures:
			 *     structure1:
			 *       x: 20
			 *       y: 50
			 *       z: -42
			 *       world: nether
			 *     structure2:
			 *       x: -2
			 *       y: 30
			 *       z: 400
			 *       world: CivEx III Final
			 *   baseItemName: §eEstate
			 *   schematic: [[[DIRT, STONE], [DIRT, GOLD_BLOCK]], [[GOLD_BLOCK, STONE], [STONE, GOLD_BLOCK]]]
			 */
			
			String name = multiBlockKey;
			
			ConfigurationSection structuresConfig = config.getConfigurationSection("structures");
			List<Structure> structures = new ArrayList<>();
			
			String baseItem = multiBlockConfig.getString("baseItemName");
			
			Material[][][] array = (Material[][][]) multiBlockConfig.get("schematic");
			Schematic schematic = new Schematic(array);
			
			MultiBlock multiBlock = new MultiBlock(name, structures, baseItem, schematic);
			
			multiBlocks.add(new MultiBlock(name, structures, baseItem, schematic));
			
			for(String key: structuresConfig.getKeys(false)) {
				ConfigurationSection structureConfig = structuresConfig.getConfigurationSection(key);
				
				ConfigurationSection locationConfig = structureConfig.getConfigurationSection("location");
				int x = locationConfig.getInt("x");
				int y = locationConfig.getInt("y");
				int z = locationConfig.getInt("z");
				World world = Bukkit.getWorld(locationConfig.getString("world"));
				Location location = new Location(world, x, y, z);
				
				int health = structureConfig.getInt("health");
				
				structures.add(new Structure(multiBlock, location, health));
			} // for
		} // for
		
		return multiBlocks;
	} // loadMultiBlocks

	/** Unimplemented */
	public static MultiBlock getByName(String name) {
		return null;
	} // getByNAme
	
} // class