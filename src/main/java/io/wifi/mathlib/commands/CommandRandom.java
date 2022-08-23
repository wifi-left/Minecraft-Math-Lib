package io.wifi.mathlib.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.argument.ScoreHolderArgumentType;
import static net.minecraft.server.command.CommandManager.argument;
import net.minecraft.command.argument.ScoreboardObjectiveArgumentType;
import net.minecraft.scoreboard.ScoreboardObjective;

import static net.minecraft.server.command.CommandManager.literal;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

// import net.minecraft.text.TranslatableText;
public class CommandRandom {

    // private static final SimpleCommandExceptionType EDIT_TAG_EXCEPTION = new
    // SimpleCommandExceptionType(new
    // TranslatableText("arguments.editfunction.tag.unsupported"));
    // private static final SimpleCommandExceptionType MOD_NOT_INSTALLED_EXCEPTION =
    // new SimpleCommandExceptionType(new
    // TranslatableText("commands.editfunction.failed.modNotInstalled"));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("random")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("min", IntegerArgumentType.integer())
                        .then(argument("max", IntegerArgumentType.integer())
                                .executes(command -> {
                                    int min = IntegerArgumentType
                                            .getInteger(command, "min");
                                    int max = IntegerArgumentType
                                            .getInteger(command, "max");
                                    if (min > max) {
                                        throw new SimpleCommandExceptionType(
                                                Text.translatable(
                                                        "mathlib.command.error.minMoreThanMax",
                                                        ("" + min),
                                                        ("" + max)))
                                                .create();
                                    }
                                    int randomResult = (int) Math.round(
                                            Math.random() * (max - min)
                                                    + min);
                                    // command.getSource().sendFeedback(message,
                                    // broadcastToOps);
                                    // command.getSource().send(message,
                                    // broadcastToOps);
                                    command.getSource().sendFeedback(
                                            Text.translatable(
                                                    "mathlib.command.msg.randomResult",
                                                    ("" + randomResult)),
                                            false);
                                    return randomResult;
                                })))
                .then(argument("min_name", ScoreHolderArgumentType.scoreHolder()).then(argument(
                        "min_objective", ScoreboardObjectiveArgumentType.scoreboardObjective())
                        .then(argument("max_name", ScoreHolderArgumentType.scoreHolder())
                                .then(argument("max_objective", ScoreboardObjectiveArgumentType.scoreboardObjective())
                                        .executes(command -> {
                                            String name1 = ScoreHolderArgumentType.getScoreHolder(
                                                    command,
                                                    "min_name");
                                            String name2 = ScoreHolderArgumentType.getScoreHolder(
                                                    command,
                                                    "max_name");
                                            ScoreboardObjective objective1 = ScoreboardObjectiveArgumentType
                                                    .getObjective(
                                                            command,
                                                            "min_objective");
                                            ScoreboardObjective objective2 = ScoreboardObjectiveArgumentType
                                                    .getObjective(
                                                            command,
                                                            "max_objective");
                                            int min = objective1.getScoreboard()
                                                    .getPlayerScore(name1, objective1)
                                                    .getScore();
                                            int max = objective2.getScoreboard()
                                                    .getPlayerScore(name2, objective2)
                                                    .getScore();
                                            if (min > max) {
                                                throw new SimpleCommandExceptionType(
                                                        Text.translatable(
                                                                "mathlib.command.error.minMoreThanMax",
                                                                ("" + min),
                                                                ("" + max)))
                                                        .create();
                                            }
                                            int randomResult = (int) Math.round(
                                                    Math.random() * (max - min)
                                                            + min);
                                            // command.getSource().sendFeedback(message,
                                            // broadcastToOps);
                                            // command.getSource().send(message,
                                            // broadcastToOps);
                                            command.getSource().sendFeedback(
                                                    Text.translatable(
                                                            "mathlib.command.msg.randomResult",
                                                            ("" + randomResult)),
                                                    false);
                                            return randomResult;
                                        }))))));
    }
}
