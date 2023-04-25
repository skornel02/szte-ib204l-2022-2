package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.ai.AiState;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.ItemEater;
import prog1.kotprog.dontstarve.solution.inventory.MyInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.List;

/**
 * Player implementation.
 */
public class Character implements BaseCharacter {

    /**
     * Name of the player
     */
    private final String name;
    /**
     * Is it a player or a robot
     */
    private final boolean player;

    /**
     * health
     */
    private float hp;

    /**
     * starvation.
     */
    private float food;

    /**
     * inventory
     */
    private final BaseInventory inventory;

    /**
     * possssxy
     */
    private Position position;

    /**
     * last action of the character
     */
    private Action lastAction;

    private AiState aiState;

    /**
     * This is my chsaracter.
     * @param name name.
     * @param player player.
     */
    public Character(String name, boolean player, Position position) {
        this.name = name;
        this.player = player;
        this.inventory = new MyInventory();
        this.hp = 100;
        this.food = 100;
        this.position = position;
        this.lastAction = null;
    }

    /**
     * This is my chsaracter.
     */
    private float getHealthSpeedModifier() {
        return getFirstLessBlablaFaszombeletek(hp, new float[]{50, 30, 10}, new float[]{1, 0.9F, 0.75F, 0.6F});
    }

    /**
     Private method that returns the food speed modifier.
     @return The food speed modifier.
     */
    private float getFoodSpeedModifier() {
        return getFirstLessBlablaFaszombeletek(food, new float[]{50, 20, 0}, new float[]{1, 0.9F, 0.8F, 0.5F});
    }

    /**

     Private method that returns the first less value from the "toMatch" array that the "value" parameter is greater or equal to.
     @param value The value to compare against the "toMatch" array.
     @param toMatch The array to compare the "value" against.
     @param result The array to return the corresponding value from when the "value" parameter is greater or equal to the value in the "toMatch" array.
     @return The first less value from the "toMatch" array that the "value" parameter is greater or equal to.
     */
    private float getFirstLessBlablaFaszombeletek(float value, float[] toMatch, float[] result) {
        for (int i = 0; i < toMatch.length; i++) {
            if (value >= toMatch[i]) {
                return result[i];
            }
        }
        return result[result.length - 1];
    }

    /**

     Returns the speed of the character.
     @return The speed of the character.
     */
    @Override
    public float getSpeed() {
        return 1 * getHealthSpeedModifier() * getFoodSpeedModifier();
    }

    /**

     Returns the current hunger of the character.
     @return The current hunger of the character.
     */
    @Override
    public float getHunger() {
        return food;
    }

    /**

     Returns the current health points of the character.
     @return The current health points of the character.
     */
    @Override
    public float getHp() {
        return hp;
    }

    /**

     Returns the inventory of the character.
     @return The inventory of the character.
     */
    @Override
    public BaseInventory getInventory() {
        return inventory;
    }

    /**

     Returns the current position of the character.
     @return The current position of the character.
     */
    @Override
    public Position getCurrentPosition() {
        return position.clone();
    }

    /**

     Sets the position of the character to the given position.
     @param position The position to set the character's position to.
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    /**

     Returns the last action performed by the character.
     @return The last action performed by the character.
     */
    @Override
    public Action getLastAction() {
        return lastAction;
    }

    /**

     Sets the last action performed by the character to the given action.
     @param lastAction The action to set the character's last action to.
     */
    @Override
    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    /**

     Returns the name of the character.
     @return The name of the character.
     */
    @Override
    public String getName() {
        return name;
    }

    /**

     Returns a boolean indicating whether the character is a player.
     @return True if the character is a player, false otherwise.
     */
    @Override
    public boolean isPlayer() {
        return player;
    }

    /**

     Adds the food value of the given item to the character's current food level, and subtracts the health value of the item from the character's current health level.
     @param food The type of food to eat.
     @return True if the character's health is still above 0 after eating the food, false otherwise.
     */
    @Override
    public boolean eat(ItemType food) {
        this.food += ItemEater.getFoodFromFood(food);

        return takeDamage(-ItemEater.getHealthFromFood(food));
    }

    /**

     Decreases the character's food level by 0.4. If the food level reaches 0, the character takes 5 damage.
     @return True if the character is dead after the tick, false otherwise.
     */
    @Override
    public boolean tickHunger() {
        food = Math.min(100, Math.max(0, food - 0.4F));

        if (food == 0) {
            takeDamage(5);
        }

        return !isAlive();
    }

    /**

     Decreases the character's health by the given amount of damage.
     @param dmg The amount of damage to take.
     @return True if the character is dead after taking the damage, false otherwise.
     */
    @Override
    public boolean takeDamage(int dmg) {
        hp = Math.min(100, Math.max(0, hp - dmg));

        return !isAlive();
    }

    /**

     Sets the AI state of the character to the given state.
     @param state The state to set the character's AI state to.
     */
    @Override
    public void setAiState(AiState state) {
        this.aiState = state;
    }

    /**

     Returns the next action to be taken by the character, according to its current AI state.
     @param level The level the character
        @param manager The game manager.
        @param characters The list of characters in the level.
        @return The next action to be taken by the character.
        */
    @Override
    public Action getNextAiAction(Level level, GameManager manager, List<BaseCharacter> characters) {
        if (this.aiState == null) {
            return lastAction;
        }

        do {
            this.aiState = aiState.nextState(this, level, manager, characters);
            System.out.println("[" + this.name + "] next ai state: " + this.aiState.toString());
        } while (this.aiState.isThinkingPhase());

        return this.aiState.getAction();
    }

    /**
     * This will set the position.
     * @return formatted.
     */
    @Override
    public String toString() {
        return "Character " + name + " {" +
                "\nhp=" + hp + ", food=" + food +
                "\ninventory=" + inventory +
                "\nposition=" + position +
                "\nlastAction=" + lastAction +
                "\n}";
    }
}
