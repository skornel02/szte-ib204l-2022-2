package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.inventory.ItemCooker;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A főzés akció leírására szolgáló osztály: egy item megfőzése.
 */
public class ActionCook extends Action {
    /**
     * A megfőzni kívánt tárgy pozíciója az inventory-ban.
     */
    private final int index;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param index a megfőzni kívánt tárgy pozíciója az inventory-ban
     */
    public ActionCook(int index) {
        super(ActionType.COOK);
        this.index = index;
    }

    /**
     * Az index gettere.
     * @return a megfőzni kívánt tárgy pozíciója az inventory-ban
     */
    public int getIndex() {
        return index;
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());
        if (!field.hasFire()) {
            return;
        }

        ItemType result = character.getInventory().cookItem(index);
        if (result == null) {
            return;
        }

        AbstractItem item = ItemCooker.cookItem(result);
        if (!character.getInventory().addItem(item)) {
            field.addItem(item);
        }
    }
}
