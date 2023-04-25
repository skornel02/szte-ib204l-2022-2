package prog1.kotprog.dontstarve.solution.character;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.ai.AiState;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Position;

import java.util.List;

/**
 * Egy egyszerű karakter leírására szolgáló interface.
 */
public interface BaseCharacter {
    /**
     * A karakter mozgási sebességének lekérdezésére szolgáló metódus.
     * @return a karakter mozgási sebessége
     */
    float getSpeed();

    /**
     * A karakter jóllakottságának mértékének lekérdezésére szolgáló metódus.
     * @return a karakter jóllakottsága
     */
    float getHunger();

    /**
     * A karakter életerejének lekérdezésére szolgáló metódus.
     * @return a karakter életereje
     */
    float getHp();

    /**
     * Is the character alive.
     * @return yes/no
     */
    default boolean isAlive() {
        return getHp() != 0;
    }

    /**
     * A karakter inventory-jának lekérdezésére szolgáló metódus.
     * <br>
     * A karakter inventory-ja végig ugyanaz marad, amelyet referencia szerint kell visszaadni.
     * @return a karakter inventory-ja
     */
    BaseInventory getInventory();

    /**
     * A játékos aktuális pozíciójának lekérdezésére szolgáló metódus.
     * @return a játékos pozíciója
     */
    Position getCurrentPosition();

    /**
     * A játékos aktuális pozíciójának beállítására szolgáló metódus.
     * @param position a játékos pozíciója
     */
    void setPosition(Position position);

    /**
     * Az utolsó cselekvés lekérdezésére szolgáló metódus.
     * <br>
     * Egy létező Action-nek kell lennie, nem lehet null.
     * @return az utolsó cselekvés
     */
    Action getLastAction();

    /**
     * Set the last action.
     * @param actiion action.
     */
    void setLastAction(Action actiion);

    /**
     * A játékos nevének lekérdezésére szolgáló metódus.
     * @return a játékos neve
     */
    String getName();

    /**
     * Is the the player.
     * @return yes/no
     */
    boolean isPlayer();


    /**
     * A karakter étel elfogyasztására szolgáló metódus.
     * @param food kaja.
     * @return true if the character died.
     */
    boolean eat(ItemType food);

    /**
     * Hunger event.
     * @return true if the character died.
     */
    boolean tickHunger();

    /**
     * Take damage.
     * @param dmg damage.
     * @return true if the character died.
     */
    boolean takeDamage(int dmg);

    /**
     * Set the ai state.
     * @param state state.
     */
    void setAiState(AiState state);

    /**
     * Get the ai state.
     * @return state.
     */
    Action getNextAiAction(Level level, GameManager manager, List<BaseCharacter> characters);
}
