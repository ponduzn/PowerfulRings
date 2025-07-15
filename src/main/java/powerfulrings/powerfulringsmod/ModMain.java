package powerfulrings.powerfulringsmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import powerfulrings.powerfulringsmod.item.ModItems;
import powerfulrings.powerfulringsmod.item.RingOfFlight;

public class ModMain implements ModInitializer {
    public static final String MOD_ID = "powerfulrings";

    @Override
    public void onInitialize() {
        ModItems.registerModItems();

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
