package powerfulrings.powerfulringsmod;

import org.slf4j.LoggerFactory;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import org.slf4j.Logger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import powerfulrings.powerfulringsmod.block.ModBlocks;
import powerfulrings.powerfulringsmod.item.ModItems;
import powerfulrings.powerfulringsmod.item.RingOfFlight;
import powerfulrings.powerfulringsmod.world.gen.ModWorldGeneration;

public class ModMain implements ModInitializer {
    public static final String MOD_ID = "powerfulrings";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModWorldGeneration.generateModWorldGen();

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                boolean toggled = RingOfFlight.isFlightToggled(player);
                boolean hasRing = RingOfFlight.hasToggledRing(player);

                if (toggled && !hasRing && !player.isCreative() && !player.isSpectator()) {
                    RingOfFlight.disableFlight(player);
                }
            }
        });
    }
}
