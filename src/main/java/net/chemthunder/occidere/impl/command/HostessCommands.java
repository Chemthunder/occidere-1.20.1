package net.chemthunder.occidere.impl.command;

import com.mojang.brigadier.CommandDispatcher;
import net.chemthunder.occidere.impl.cca.entity.HostessComponent;
import net.chemthunder.occidere.impl.cca.world.FatesDominionWorldEvent;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.manager.HostessManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;

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

        commandDispatcher.register(
                CommandManager.literal("hostess:givePact")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();

                            if (player != null) {
                                player.giveItemStack(new ItemStack(OccidereItems.PACT));

                                context.getSource().sendFeedback(() -> Text.literal("Hostess has bestown herself with a Pact."), true);
                            }
                            return 1;
                        }).requires(serverCommandSource -> HostessManager.isHostess(serverCommandSource.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("hostess:fatesDominion")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();

                            if (player != null) {
                                World world = player.getWorld();
                                FatesDominionWorldEvent event = FatesDominionWorldEvent.KEY.get(world);

                                event.setActive(!event.getActive());

                                if (event.getActive()) {
                                    context.getSource().sendFeedback(() -> Text.literal("Fate's Dominion has begun."), true);
                                } else {
                                    context.getSource().sendFeedback(() -> Text.literal("Fate's Dominion has concluded."), true);
                                }
                            }
                            return 1;
                        }).requires(serverCommandSource -> HostessManager.isHostess(serverCommandSource.getEntity()))
        );

        commandDispatcher.register(
                CommandManager.literal("hostess:fatesDominionFog")
                        .executes(context -> {
                            PlayerEntity player = context.getSource().getPlayer();

                            if (player != null) {
                                World world = player.getWorld();
                                FatesDominionWorldEvent event = FatesDominionWorldEvent.KEY.get(world);

                                if (event.getActive()) {

                                    event.setFogActive(!event.getFogActive());

                                    if (event.getFogActive()) {
                                        context.getSource().sendFeedback(() -> Text.literal("Fate's Dominion's Fog has begun."), true);
                                    } else {
                                        context.getSource().sendFeedback(() -> Text.literal("Fate's Dominion's Fog has concluded."), true);
                                    }
                                }
                            }
                            return 1;
                        }).requires(serverCommandSource -> HostessManager.isHostess(serverCommandSource.getEntity()))
        );
    }
}