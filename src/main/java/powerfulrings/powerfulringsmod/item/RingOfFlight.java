package powerfulrings.powerfulringsmod.item;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import java.util.function.Consumer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

public class RingOfFlight extends Item {

    private static final Set<UUID> toggledPlayers = new HashSet<>();

    public RingOfFlight(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            UUID id = player.getUuid();
            if (toggledPlayers.contains(id)) {
                disableFlight(player);
            } else {
                toggledPlayers.add(id);
                player.getAbilities().allowFlying = true;
                player.getAbilities().flying = true;
                player.sendAbilitiesUpdate();
            }
        }
        return ActionResult.SUCCESS;
    }

    public static void disableFlight(PlayerEntity player) {
        toggledPlayers.remove(player.getUuid());
        player.getAbilities().allowFlying = false;
        player.getAbilities().flying = false;
        player.sendAbilitiesUpdate();
    }

    public static boolean isFlightToggled(PlayerEntity player) {
        return toggledPlayers.contains(player.getUuid());
    }

    public static boolean hasToggledRing(ServerPlayerEntity player) {
        for (ItemStack stack : player.getInventory().getMainStacks()) {
            if (stack.getItem() instanceof RingOfFlight) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return RingOfFlight.toggledPlayers.size() > 0;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, net.minecraft.entity.Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof ServerPlayerEntity player)) return;

        boolean hasRing = hasToggledRing(player);
        boolean toggled = isFlightToggled(player);

        if (toggled && !hasRing && !player.isCreative() && !player.isSpectator()) {
            disableFlight(player);
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        String RING_ENABLED = "ON";
        String RING_DISABLED = "OFF";
        
	    if (hasGlint(stack) == true) {
        textConsumer.accept(Text.translatable("Ring is toggled: " + RING_ENABLED).formatted(Formatting.RED));
        }
        else {
            textConsumer.accept(Text.translatable("Ring is toggled: " + RING_DISABLED).formatted(Formatting.RED));
	    }
    }
}
