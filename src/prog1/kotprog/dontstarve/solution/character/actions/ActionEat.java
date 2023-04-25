package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * Az étel elfogyasztása akció leírására szolgáló osztály: egy étel elfogyasztása az inventory-ból.
 */
public class ActionEat extends Action {
    /**
     * A megenni kívánt étel pozíciója az inventory-ban.
     */
    private final int index;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param index a megenni kívánt étel pozíciója az inventory-ban
     */
    public ActionEat(int index) {
        super(ActionType.EAT);
        this.index = index;
    }

    /**
     * Az index gettere.
     * @return a megenni kívánt étel pozíciója az inventory-ban
     */
    public int getIndex() {
        return index;
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        if (character.getHunger() >= 100F) {
            return;
        }

        ItemType type = character.getInventory().eatItem(index);
        if (type != null) {
            if (character.eat(type)) {
                new ActionDied().handleAction(character, level, manager, null);
            }
        }
    }
}
