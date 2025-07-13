package powerfulrings.powerfulringsmod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public class RingOfFlight extends Item {

    public RingOfFlight(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof ServerPlayerEntity player) {
            boolean hasRing = player.getInventory().contains(stack);

            // Give or remove flight
            if (hasRing) {
                if (!player.getAbilities().allowFlying) {
                    player.getAbilities().allowFlying = true;
                    player.sendAbilitiesUpdate();
                }
            } else {
                if (!player.isCreative() && !player.isSpectator()) {
                    if (player.getAbilities().flying) {
                        player.getAbilities().flying = false;
                    }
                    player.getAbilities().allowFlying = false;
                    player.sendAbilitiesUpdate();
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }
}