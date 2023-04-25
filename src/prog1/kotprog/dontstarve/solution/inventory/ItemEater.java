package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

/**
 * This will eat items.
 */
public class ItemEater {

    /**
     * This will eat an item.
     * @param type type.
     * @return cooked item.
     */
    public static int getHealthFromFood(ItemType type) {
        return switch (type) {
            case RAW_BERRY -> -5;
            case RAW_CARROT, COOKED_BERRY -> 1;
            case COOKED_CARROT -> 3;
            default -> 0;
        };
    }

    /**
     * This will check if an item can be cooked.
     * @param type item.
     * @return int if can be cooked.
     */
    public static int getFoodFromFood(ItemType type) {
        return switch (type) {
            case RAW_BERRY -> 20;
            case RAW_CARROT -> 12;
            case COOKED_BERRY, COOKED_CARROT -> 10;
            default -> 0;
        };
    }

    /**
     * This will check if an item can be cooked.
     * @param type item.
     * @return true if can be cooked.
     */
    public static boolean canEat(AbstractItem type) {
        return type != null && getFoodFromFood(type.getType()) != 0;
    }
}
