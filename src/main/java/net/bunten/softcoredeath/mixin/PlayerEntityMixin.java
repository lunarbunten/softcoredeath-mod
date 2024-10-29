package net.bunten.softcoredeath.mixin;

import net.bunten.softcoredeath.MainEntrypoint;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.bunten.softcoredeath.MainEntrypoint.XP_PERCENTAGE;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	private final PlayerEntity player = (PlayerEntity) (Object) this;

	@Redirect(method = "dropInventory", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
	private boolean injected(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
		return MainEntrypoint.ENABLED || instance.getBoolean(rule);
	}

	@Inject(at = @At("RETURN"), method = "getXpToDrop", cancellable = true)
	protected void getXpToDrop(ServerWorld world, CallbackInfoReturnable<Integer> cir) {
		if (MainEntrypoint.ENABLED && !world.getGameRules().getBoolean(GameRules.KEEP_INVENTORY)) {
			cir.setReturnValue(player.totalExperience * (XP_PERCENTAGE / 100));
		}
	}
}