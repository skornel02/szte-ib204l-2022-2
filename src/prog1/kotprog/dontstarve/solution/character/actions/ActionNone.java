package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A várakozás akció leírására szolgáló osztály: a karakter nem végez cselekvést az aktuális körben.
 */
public class ActionNone extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionNone() {
        super(ActionType.NONE);
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        // Do nothing
    }
}
