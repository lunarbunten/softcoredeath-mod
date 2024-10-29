package net.bunten.softcoredeath.mixin;

import net.bunten.softcoredeath.MainEntrypoint;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Redirect(method = "copyFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
    private boolean injected(GameRules instance, GameRules.Key<GameRules.BooleanRule> rule) {
        return MainEntrypoint.ENABLED || instance.getBoolean(rule);
    }
}