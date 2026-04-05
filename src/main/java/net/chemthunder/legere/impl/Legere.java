package net.chemthunder.legere.impl;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Legere implements ModInitializer {
    public static final String MOD_ID = "legere";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {

        LOGGER.info("Legere has been bootstrapped");
    }
}
