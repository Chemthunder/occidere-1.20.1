package net.chemthunder.occidere.impl.command;

import com.mojang.brigadier.CommandDispatcher;
import net.chemthunder.occidere.impl.cca.entity.HostessComponent;
import net.chemthunder.occidere.impl.manager.HostessManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class HostessCommands implements CommandRegistrationCallback {
    public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        commandDispatcher.register(
                CommandManager.literal("hostess:toggleInvincibility")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();

                            if (player != null) {
                                HostessComponent component = HostessComponent.KEY.get(player);
                                if (HostessManager.isHostess(player)) {
                                    component.setInvincible(!component.isInvincible());
                                }

                                context.getSource().sendFeedback(() -> Text.literal("Hostess has modified her invincibility to " + component.isInvincible()), true);
                            }
                            return 1;
                        }).requires(serverCommandSource -> HostessManager.isHostess(serverCommandSource.getEntity()))
        );
    }
}