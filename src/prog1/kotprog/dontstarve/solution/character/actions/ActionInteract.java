package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.level.fields.HittableField;

import java.util.List;

/**
 * Az aktuális mezőn lévő tereptárggyal való interakcióba lépés (favágás, kőcsákányozás, gally / bogyó / répa leszedése)
 * leírására szolgáló osztály.
 */
public class ActionInteract extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionInteract() {
        super(ActionType.INTERACT);
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());
        if (field instanceof HittableField hittable) {
            hittable.hit(character.getInventory());
            character.getInventory().checkHand();
        }
    }
}
