package io.wifi.mathlib.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.arguments.StringArgumentType;

// import static net.minecraft.command.argument.ScoreHolderArgumentType;
import static net.minecraft.server.command.CommandManager.argument;

import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.ScoreHolderArgumentType;
import net.minecraft.command.argument.ScoreboardObjectiveArgumentType;
import net.minecraft.scoreboard.ScoreboardObjective;

import static net.minecraft.server.command.CommandManager.literal;
import net.minecraft.server.command.ServerCommandSource;

import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// import net.minecraft.text.TranslatableText;
public class CommandMath {

    // private static final SimpleCommandExceptionType EDIT_TAG_EXCEPTION = new
    // SimpleCommandExceptionType(new
    // TranslatableText("arguments.editfunction.tag.unsupported"));
    // private static final SimpleCommandExceptionType MOD_NOT_INSTALLED_EXCEPTION =
    // new SimpleCommandExceptionType(new
    // TranslatableText("commands.editfunction.failed.modNotInstalled"));
    public static final SuggestionProvider<ServerCommandSource> getOperatingSuggestion = (ctx, builder) -> {
        String[] operatings = { "+", "-", "*", "/", "%", "^", "min", "max", "middle", "=cos", "=tan", "=sin", "=abs",
                "=sqrt" };
        for (int i = 0; i < operatings.length; i++) {
            builder.suggest(operatings[i]);
        }
        return builder.buildFuture();
    };

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("math")
                .requires(source -> source.hasPermissionLevel(2))
                .then(argument("name1", ScoreHolderArgumentType.scoreHolder())
                        .then(argument("objective1", ScoreboardObjectiveArgumentType.scoreboardObjective())
                                .then(argument("operating", StringArgumentType.string())
                                        .suggests(getOperatingSuggestion)
                                        .then(argument("name2", ScoreHolderArgumentType.scoreHolder())
                                                .then(argument("objective2",
                                                        ScoreboardObjectiveArgumentType.scoreboardObjective()))
                                                .executes(command -> {
                                                    String operating = StringArgumentType.getString(command,
                                                            "operating");
                                                    String name1 = ScoreHolderArgumentType.getScoreHolder(command,
                                                            "name1");
                                                    String name2 = ScoreHolderArgumentType.getScoreHolder(command,
                                                            "name2");
                                                    ScoreboardObjective objective1 = ScoreboardObjectiveArgumentType
                                                            .getObjective(
                                                                    command,
                                                                    "objective1");
                                                    ScoreboardObjective objective2 = ScoreboardObjectiveArgumentType
                                                            .getObjective(
                                                                    command,
                                                                    "objective2");
                                                    int value1 = objective1.getScoreboard()
                                                            .getPlayerScore(name1, objective1)
                                                            .getScore();
                                                    int value2 = objective2.getScoreboard()
                                                            .getPlayerScore(name2, objective2)
                                                            .getScore();
                                                    int result = 0;
                                                    switch (operating) {
                                                        case "+":
                                                            result = value1 + value2;
                                                            break;
                                                        case "-":
                                                            result = value1 - value2;
                                                            break;
                                                        case "*":
                                                            result = value1 * value2;
                                                            break;
                                                        case "/":
                                                            result = value1 / value2;
                                                            break;
                                                        case "^":
                                                            result = (int) Math.pow(value1, value2);
                                                            break;
                                                        case "%":
                                                            result = value1 % value2;
                                                            break;
                                                        case "min":
                                                            result = Math.min(value1, value2);
                                                            break;
                                                        case "max":
                                                            result = Math.max(value1, value2);
                                                            break;
                                                        case "middle":
                                                            result = (value1 + value2) / 2;
                                                            break;
                                                        case "=sin":
                                                            result = (int) Math.sin(value1);
                                                            return 1;
                                                        case "=cos":
                                                            result = (int) Math.cos(value1);
                                                            return 1;

                                                        case "=tan":
                                                            result = (int) Math.tan(value1);
                                                            return 1;

                                                        case "=abs":
                                                            result = (int) Math.abs(value1);
                                                            return 1;

                                                        case "=sqrt":
                                                            result = (int) Math.sqrt(value1);
                                                            return 1;

                                                        default:
                                                            throw new SimpleCommandExceptionType(
                                                                    Text.translatable(
                                                                            "mathlib.command.error.noSuchOperating"))
                                                                    .create();
                                                        // return 0;
                                                    }
                                                    // command.getSource().sendFeedback(message, broadcastToOps);
                                                    // command.getSource().send(message, broadcastToOps);
                                                    return result;
                                                }))))));
    }
}
