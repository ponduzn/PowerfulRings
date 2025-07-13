package powerfulrings.powerfulringsmod.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.WeakHashMap;

public class BaseEffectRing extends Item {
    private static final String NBT_TOGGLED = "Toggled"; // kept for clarity but unused now

    // Runtime map to store toggled state per ItemStack (non-persistent)
    private static final WeakHashMap<ItemStack, Boolean> toggledStates = new WeakHashMap<>();
    private final StatusEffect effect;
    private final int amplifier;

    public BaseEffectRing(net.minecraft.item.Item.Settings settings, StatusEffect effect, int amplifier) {
        super(settings);
        this.effect = effect;
        this.amplifier = amplifier;
    }

    public static boolean isToggledOn(ItemStack stack) {
        return toggledStates.getOrDefault(stack, false);
    }

    public static void toggle(ItemStack stack) {
        toggledStates.put(stack, !isToggledOn(stack));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient && player.getInventory().contains(player.getStackInHand(hand))) {
            toggle(player.getStackInHand(hand));
        }
        return ActionResult.SUCCESS;
    }


    public boolean hasEnchantmentGlint(ItemStack stack) {
        return isToggledOn(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof ServerPlayerEntity player
                && player.getInventory().contains(stack)
                && isToggledOn(stack)) {
            player.addStatusEffect(new StatusEffectInstance(effect, 210, amplifier, true, true, true));
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
