package powerfulrings.powerfulringsmod.item;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BaseEffectRing extends Item {
    private final RegistryEntry<StatusEffect> effect;
    private final int amplifier;
    private boolean toggled = false;

    public BaseEffectRing(Settings settings, RegistryEntry<StatusEffect> effect, int amplifier) {
        super(settings);
        this.effect = effect;
        this.amplifier = amplifier;
    }

    public boolean isToggledOn() {
        return toggled;
    }

    public void toggle() {
        toggled = !toggled;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
    return isToggledOn();
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            toggle();
        }
        return ActionResult.SUCCESS;
    }

    public boolean hasEnchantmentGlint(ItemStack stack) {
        return isToggledOn();
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, net.minecraft.entity.Entity entity, @Nullable EquipmentSlot slot) {
        if (!(entity instanceof ServerPlayerEntity player)) {
            super.inventoryTick(stack, world, entity, slot);
            return;
        }

        boolean hasThisRing = isInInventory(player, this);
        boolean toggledOn = isToggledOn();

        if (hasThisRing && toggledOn) {
            player.addStatusEffect(new StatusEffectInstance(effect, 210, amplifier, true, true, true));
        }

        super.inventoryTick(stack, world, entity, slot);
    }

    private boolean isInInventory(ServerPlayerEntity player, Item item) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().getStack(i).getItem() == item) {
                return true;
            }
        }
        return false;
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
