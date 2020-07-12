package eigencraft.motionsickness.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import eigencraft.motionsickness.SetSlipperinessCommand;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class MixinCommandManager {
    @Inject(method = "<init>(Lnet/minecraft/server/command/CommandManager$RegistrationEnvironment;)V", at = @At("RETURN"))
    private void onInit(CommandManager.RegistrationEnvironment environment, CallbackInfo ci) {
        CommandDispatcher<net.minecraft.server.command.ServerCommandSource> dispatcher = ((CommandManager) (Object) this).getDispatcher();

        dispatcher.register(
                CommandManager.literal("motion-slipperiness")
                        .then(SetSlipperinessCommand.registerSubCommand(dispatcher))
        );
    }
}
