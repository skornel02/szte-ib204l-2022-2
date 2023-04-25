package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A tárgy levétele akció leírására szolgáló osztály: az aktuálisan kézben lévő item visszarakása az inventory-ba.
 */
public class ActionUnequip extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionUnequip() {
        super(ActionType.UNEQUIP);
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());
        field.addItem(character.getInventory().unequipItem());
    }
}
