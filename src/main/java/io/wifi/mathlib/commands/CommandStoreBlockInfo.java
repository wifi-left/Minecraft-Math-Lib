package io.wifi.mathlib.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.argument.ScoreHolderArgumentType;
import static net.minecraft.server.command.CommandManager.argument;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.chunk.ChunkBuilder.ChunkData;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.NbtPathArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.ScoreboardObjectiveArgumentType;
import net.minecraft.scoreboard.ScoreboardObjective;

import static net.minecraft.server.command.CommandManager.literal;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

// import net.minecraft.text.TranslatableText;
public class CommandStoreBlockInfo {

    // private static final SimpleCommandExceptionType EDIT_TAG_EXCEPTION = new
    // SimpleCommandExceptionType(new
    // TranslatableText("arguments.editfunction.tag.unsupported"));
    // private static final SimpleCommandExceptionType MOD_NOT_INSTALLED_EXCEPTION =
    // new SimpleCommandExceptionType(new
    // TranslatableText("commands.editfunction.failed.modNotInstalled"));
    private static void getBlock(BlockPos blockPos){
        // TO-DO get block
    }
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("blockstore")
                .requires(source -> source.hasPermissionLevel(2)).then(argument("blockpos",BlockPosArgumentType.blockPos()).then(
                    literal("get"))
            .executes(command -> {
                getBlock(BlockPosArgumentType.getLoadedBlockPos(command, "blockpos"));
                return 1;
            }).then(literal("id")).executes(command->{

                return 1;
            })
            .then(literal("set")).then(literal("store"))));

    }
}
