package eigencraft.motionsickness.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import eigencraft.motionsickness.Settings;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class MixinCommandManager {
    @Inject(method = "<init>(Lnet/minecraft/server/command/CommandManager$RegistrationEnvironment;)V", at = @At("RETURN"))
    private void onInit(CommandManager.RegistrationEnvironment environment, CallbackInfo ci) {
        CommandDispatcher<net.minecraft.server.command.ServerCommandSource> dispatcher = ((CommandManager) (Object) this).getDispatcher();

        ArgumentCommandNode<ServerCommandSource, Float> argInterval = CommandManager.argument("slipperiness", FloatArgumentType.floatArg(0,1.0f))
                .executes(c -> {
                    Settings.slipperiness = FloatArgumentType.getFloat(c, "slipperiness");
                    c.getSource().sendFeedback(new LiteralText(String.format("Set slipperiness to %f",Settings.slipperiness)),false);
                    return 1;
                }).build();

        dispatcher.register(
                CommandManager.literal("motion-slipperiness").then(argInterval)
                        //.then(SetSlipperinessCommand.registerSubCommand(dispatcher))
        );

    }
}
