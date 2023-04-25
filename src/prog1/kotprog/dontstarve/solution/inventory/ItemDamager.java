package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;

/**
 * Az itemek sebzését számoló osztály.
 */
public class ItemDamager {

    /**
     * Az itemek sebzését számoló metódus.
     * @param item az item
     * @return az item sebzése
     */
    public static int getDamage(EquippableItem item) {
        if (item != null) {
            return switch (item.getType()) {
                case SPEAR -> 19;
                case AXE, PICKAXE -> 8;
                case TORCH -> 6;
                default -> 2;
            };
        }

        return 4;
    }

}
