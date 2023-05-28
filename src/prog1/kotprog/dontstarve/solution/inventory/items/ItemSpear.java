package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A lándzsa item leírására szolgáló osztály.
 */
public class ItemSpear extends EquippableItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemSpear() {
        super(ItemType.SPEAR, 10);
    }

    @Override
    public AbstractItem clone() {
        return new ItemSpear();
    }
}
