package net.chemthunder.occidere.impl;

import net.chemthunder.occidere.impl.command.HostessCommands;
import net.chemthunder.occidere.impl.index.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Occidere implements ModInitializer {
    public static final String MOD_ID = "occidere";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public void onInitialize() {
        /* Registries */
        OccidereItems.init();
        OccidereItemGroups.init();
        OccidereEntities.init();
        OccidereParticles.init();
        OccidereSounds.init();

        /* Special */
        OccidereWeaponObtainments.init();
        OccidereUUIDs.init();

        /* Events */
        CommandRegistrationCallback.EVENT.register(new HostessCommands());

        LOGGER.info("loaded!!! [OCCIDERE]");
    }

    public static Identifier id (String path){return Identifier.of(MOD_ID, path); }
}