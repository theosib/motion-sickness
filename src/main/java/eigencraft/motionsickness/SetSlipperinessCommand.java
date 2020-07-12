package eigencraft.motionsickness;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class SetSlipperinessCommand {
    public static CommandNode<ServerCommandSource> registerSubCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralCommandNode<ServerCommandSource> subCommandRootNode = CommandManager.literal("set").build();

        ArgumentCommandNode<ServerCommandSource, Float> argInterval = CommandManager.argument("slipperiness", FloatArgumentType.floatArg(0,1.0f))
                .executes(c -> execute(FloatArgumentType.getFloat(c, "slipperiness"),c.getSource()))
                .build();

        subCommandRootNode.addChild(argInterval);

        return subCommandRootNode;
    }

    private static int execute(float value, ServerCommandSource source){
        Settings.slipperiness = value;
        source.sendFeedback(new LiteralText(String.format("Set slipperiness to %f",value)),false);

        return 1;
    }
}
