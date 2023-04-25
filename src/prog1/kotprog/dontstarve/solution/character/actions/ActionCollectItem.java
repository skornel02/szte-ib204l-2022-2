package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * Az item begyűjtése akció leírására szolgáló osztály: egy item begyűjtése az aktuális mezőről.
 */
public class ActionCollectItem extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionCollectItem() {
        super(ActionType.COLLECT_ITEM);
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());
        AbstractItem item = field.pickupItem();
        if (item != null) {
            character.getInventory().addItem(item);
        }
    }
}
