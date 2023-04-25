package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * Felvehető / kézbe vehető item leírására szolgáló osztály.
 */
public abstract class EquippableItem extends AbstractItem {

    protected int durablitity;

    private final int maxDurability;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type          az item típusa
     * @param maxDurability maxDur
     */
    public EquippableItem(ItemType type, int maxDurability) {
        super(type, 1, 1);
        this.maxDurability = maxDurability;
        this.durablitity = maxDurability;
    }

    /**
     * Megadja, hogy milyen állapotban van a tárgy.
     * @return a tárgy használatlansága, %-ban (100%: tökéletes állapot)
     */
    public float percentage() {
        return (float) durablitity * 100 / maxDurability;
    }

    /**
     * Megadja, hogy a tárgy használható-e.
     */
    public void use() {
        if (this instanceof ItemTorch) {
            return;
        }

        durablitity--;
    }

    /**
     * Megadja, hogy a tárgy használható-e.
     */
    public void tick() {
        if (this instanceof ItemTorch) {
            durablitity--;
        }
    }

    /**
     * Megadja, hogy a tárgy használható-e.
     * @return yey
     */
    public boolean isBroken() {
        return durablitity == 0;
    }

    /**
     * Megadja, hogy a tárgy használható-e.
     * @return yey
     */
    @Override
    public String toString() {
        return "EquippableItem " + this.getType() + "{" +
                "durablitity=" + durablitity +
                ", percentage=" + percentage() +
                '}';
    }
}
