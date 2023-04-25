package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.inventory.InventoryConstant;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

public class ActionDied extends Action {
    public ActionDied() {
        super(ActionType.NONE);
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());

        field.addItem(character.getInventory().equippedItem());
        for (int i = 0; i < InventoryConstant.maxSize() ; i++) {
            field.addItem(character.getInventory().getItem(i));
        }
    }
}
