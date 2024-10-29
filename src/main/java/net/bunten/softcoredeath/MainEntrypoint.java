package net.bunten.softcoredeath;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainEntrypoint implements ModInitializer {

	public static final String MOD_ID = "softcoredeath";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final boolean ENABLED = true;
	public static int XP_PERCENTAGE = 50;

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_ID + " loaded");
	}
}