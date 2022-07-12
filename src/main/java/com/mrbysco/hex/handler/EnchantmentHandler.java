package com.mrbysco.hex.handler;

import com.mrbysco.hex.config.HexConfig;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import com.mrbysco.hex.util.EnchantmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnchantmentHandler {
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		final ItemStack stack = event.getItemStack();
		handleAffection(stack, event);
	}

	@SubscribeEvent
	public void onPlayerAttack(AttackEntityEvent event) {
		final Player player = event.getEntity();
		handleAffection(player.getMainHandItem(), event);
	}

	@SubscribeEvent
	public void onRightClickBlock(RightClickBlock event) {
		final BlockHitResult oldHitResult = event.getHitVec();
		final Level level = event.getLevel();
		final Player player = event.getEntity();
		final ItemStack stack = event.getItemStack();

		boolean hasCultivation = EnchantmentUtil.hasEnchantment(EnchantmentRegistry.CULTIVATION.get(), stack);
		if (hasCultivation) {
			BlockHitResult hitResult = getPlayerPOVHitResult(level, player, Fluid.ANY);
			if (!(hitResult.getType() == HitResult.Type.MISS || hitResult.getType() != HitResult.Type.BLOCK)) {
				BlockPos blockpos = hitResult.getBlockPos();
				Direction direction = hitResult.getDirection();
				BlockPos relativePos = blockpos.relative(direction);
				if (level.mayInteract(player, blockpos) && player.mayUseItemAt(relativePos, direction, stack)) {
					FluidState state = level.getFluidState(blockpos);
					if (state.is(FluidTags.WATER)) {
						int radius = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.CULTIVATION.get(), stack);
						for (int x = -radius; x <= radius; x++) {
							for (int y = -radius; y <= radius; y++) {
								Vec3 vec3 = oldHitResult.getLocation().add(x, 0, y);
								BlockPos newPos = blockpos.offset(x, 0, y);
								stack.useOn(new UseOnContext(player, event.getHand(), new BlockHitResult(vec3, Direction.UP, newPos, false)));
							}
						}
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onLeftClickBlock(LeftClickBlock event) {
		final Level level = event.getLevel();
		final Player player = event.getEntity();
		final ItemStack stack = event.getItemStack();
		final BlockPos blockpos = event.getPos();
		final InteractionHand hand = event.getHand();

		boolean hasYielding = EnchantmentUtil.hasEnchantment(EnchantmentRegistry.YIELDING.get(), stack);
		if (hasYielding) {
			int radius = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.YIELDING.get(), stack);
			for (int x = -radius; x <= radius; x++) {
				for (int y = -radius; y <= radius; y++) {
					BlockPos newPos = blockpos.offset(x, 0, y);
					BlockState state = level.getBlockState(newPos);
					if (state.getBlock() instanceof BushBlock || state.getBlock() instanceof LeavesBlock) {
						if (state.getBlock() instanceof CropBlock cropBlock) {
							if (cropBlock.isMaxAge(state)) {
								//TODO: Check if another enchantment is enabled that plants the crop back
								if (!player.isCreative()) {
									stack.hurtAndBreak(1, player, (entity) -> {
										entity.broadcastBreakEvent(hand);
										net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(entity, stack, hand);
									});
								}
								level.destroyBlock(newPos, !player.isCreative(), player);
							}
						} else {
							if (!player.isCreative()) {
								stack.hurtAndBreak(1, player, (entity) -> {
									entity.broadcastBreakEvent(hand);
									net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(entity, stack, hand);
								});
							}
							level.destroyBlock(newPos, true, player);
						}
						event.setCanceled(true);
					}
				}
			}
		}
	}

	private void handleAffection(ItemStack stack, Event event) {
		if (event.isCancelable()) {
			boolean hasAffection = EnchantmentUtil.hasEnchantment(EnchantmentRegistry.AFFECTION.get(), stack);
			if (hasAffection) {
				int maxDamage = stack.getMaxDamage();
				int damage = maxDamage - stack.getDamageValue();
				int minAllowed = (int) Math.floor(((double) maxDamage) * HexConfig.COMMON.affectionPercentage.get()) + 1;
				if (damage <= minAllowed) {
					//Cancel any interaction when durability is under configured percentage
					event.setCanceled(true);
				}
			}
		}
	}

	protected static BlockHitResult getPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluidContext) {
		float xRot = player.getXRot();
		float yRot = player.getYRot();
		Vec3 eyePosition = player.getEyePosition();
		float f2 = Mth.cos(-yRot * ((float) Math.PI / 180F) - (float) Math.PI);
		float f3 = Mth.sin(-yRot * ((float) Math.PI / 180F) - (float) Math.PI);
		float f4 = -Mth.cos(-xRot * ((float) Math.PI / 180F));
		float f5 = Mth.sin(-xRot * ((float) Math.PI / 180F));
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double reach = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
		Vec3 vec31 = eyePosition.add((double) f6 * reach, (double) f5 * reach, (double) f7 * reach);
		return level.clip(new ClipContext(eyePosition, vec31, ClipContext.Block.OUTLINE, fluidContext, player));
	}
}
