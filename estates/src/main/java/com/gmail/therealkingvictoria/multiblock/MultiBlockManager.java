package com.gmail.therealkingvictoria.multiblock;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.gmail.therealkingvictoria.estate.Estate;

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
			
			
		} // for
		
		return multiBlocks;
	} // loadMultiBlocks

	/** Unimplemented */
	public static MultiBlock getByName(String name) {
		return null;
	} // getByNAme
	
} // class