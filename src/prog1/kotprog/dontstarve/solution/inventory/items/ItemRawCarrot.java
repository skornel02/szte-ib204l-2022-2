package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * A nyers répa item leírására szolgáló osztály.
 */
public class ItemRawCarrot extends AbstractItem {
    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param amount az item mennyisége
     */
    public ItemRawCarrot(int amount) {
        super(ItemType.RAW_CARROT, amount, 10);
    }

    @Override
    public AbstractItem clone() {
        return new ItemRawCarrot(getAmount());
    }
}
