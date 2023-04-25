package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemCookedBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemCookedCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

/**
 * This will cook items.
 */
public class ItemCooker {

    /**
     * This will cook an item.
     * @param type type.
     * @return cooked item.
     */
    public static AbstractItem cookItem(ItemType type) {
        if (type == ItemType.RAW_BERRY) {
            return new ItemCookedBerry(1);
        } else if (type == ItemType.RAW_CARROT) {
            return new ItemCookedCarrot(1);
        }
        return null;
    }

    /**
     * This will check if an item can be cooked.
     * @param item item.
     * @return true if can be cooked.
     */
    public static boolean canCook(AbstractItem item) {
        return item != null && cookItem(item.getType()) != null;
    }

}
