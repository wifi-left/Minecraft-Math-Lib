# Minecraft Math Lib
A mod that provides some math functions that can be used in-game more easily. (suitable for datapack makers)
# Futures
## Commands
### Math

Usage:

`/math <name1> <objective1> <operation> <name2> <objective2>` 
perform operations.
The following operations are currently supported:

 - Plus (`+`)
 - Minus (`-`)
 - Multiplication (`"*"`)
 - Division (`"/"`)
 - Max (`max`)
 - Min (`min`)
 - Average (`middle`)
 - `=cos` / `=sin` / `=tan` / `=sqrt`
   Please note: these will change the value of the first provided scoreboard
 - Mod (`"%"`)
 - Power (`"^"`)

The output result will be output in the chat bar. If the value of the scoreboard has not been modified, you can store it with execute store result.
If the value is modified this value will remain constant.
**Note that all operations are rounded up, as the Minecraft scoreboard doesn't support decimals!**

### Random

Usage:

`/random <min> <max>` or `/random <min_name> <min_objective> <max_name> <max_objective>`

Get a random integer between the minimum and maximum values.