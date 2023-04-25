package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A fejsze item leírására szolgáló osztály.
 */
public class ItemAxe extends EquippableItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     */
    public ItemAxe() {
        super(ItemType.AXE, 40);
    }

    @Override
    public AbstractItem clone() {
        return new ItemAxe();
    }
}
