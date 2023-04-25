package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A karakterek egy akciójának leírására szolgáló osztály.
 */
public abstract class Action {
    /**
     * Az akció típusa.
     * @see ActionType
     */
    private final ActionType type;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     * @param type az akció típusa
     */
    public Action(ActionType type) {
        this.type = type;
    }

    /**
     * A type gettere.
     * @return az akció típusa
     */
    public ActionType getType() {
        return type;
    }

    /**
     * Az akció végrehajtása.
     *
     * @param character  a karakter, aki az akciót végrehajtja
     * @param manager    a játékmenedzser
     * @param characters a karakterek listája
     */
    public abstract void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters);
}
