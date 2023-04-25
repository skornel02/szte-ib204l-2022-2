package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * Az item eldobás akció leírására szolgáló osztály: egy inventory-ban lévő item eldobása az aktuális mezőre.
 */
public class ActionDropItem extends Action {
    /**
     * Az eldobandó tárgy pozíciója az inventory-ban.
     */
    private final int index;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param index az eldobandó tárgy pozíciója az inventory-ban
     */
    public ActionDropItem(int index) {
        super(ActionType.DROP_ITEM);
        this.index = index;
    }

    /**
     * Az index gettere.
     * @return az eldobandó tárgy pozíciója az inventory-ban
     */
    public int getIndex() {
        return index;
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());
        field.addItem(character.getInventory().dropItem(index));
    }
}
