package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A tárgy mozgatása akció leírására szolgáló osztály: a tárgyat az inventory-ban egy másik pozícióra mozgatjuk.
 */
public class ActionMoveItem extends Action {
    /**
     * A mozgatni kívánt tárgy pozíciója az inventory-ban.
     */
    private final int oldIndex;

    /**
     * A mozgatni kívánt tárgy új pozíciója az inventory-ban.
     */
    private final int newIndex;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     * @param oldIndex a mozgatni kívánt tárgy pozíciója az inventory-ban
     * @param newIndex a mozgatni kívánt tárgy új pozíciója az inventory-ban
     */
    public ActionMoveItem(int oldIndex, int newIndex) {
        super(ActionType.MOVE_ITEM);
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
    }

    /**
     * az oldIndex gettere.
     * @return A mozgatni kívánt tárgy pozíciója az inventory-ban
     */
    public int getOldIndex() {
        return oldIndex;
    }

    /**
     * a newIndex gettere.
     * @return A mozgatni kívánt tárgy új pozíciója az inventory-ban
     */
    public int getNewIndex() {
        return newIndex;
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        character.getInventory().moveItem(oldIndex, newIndex);
    }
}
