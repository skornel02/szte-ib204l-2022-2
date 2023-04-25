package prog1.kotprog.dontstarve.solution.inventory.items;

/**
 * Egy általános itemet leíró osztály.
 */
public abstract class AbstractItem implements Cloneable {
    /**
     * Az item típusa.
     * @see ItemType
     */
    private final ItemType type;

    /**
     * Az item mennyisége.
     */
    private int amount;

    /**
     * The max stack amount
     */
    private final int maxAmount;

    /**
     * Konstruktor, amellyel a tárgy létrehozható.
     *
     * @param type      az item típusa
     * @param amount    az item mennyisége
     * @param maxAmount Az item max mennyisége
     */
    public AbstractItem(ItemType type, int amount, int maxAmount) {
        this.type = type;
        this.amount = amount;
        this.maxAmount = maxAmount;
    }

    /**
     * A type gettere.
     * @return a tárgy típusa
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Max amount getter.
     * @return max amount.
     */
    public int getMaxAmount() {
        return maxAmount;
    }

    /**
     * Az amount gettere.
     * @return a tárgy mennyisége
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Asdasdsa asd.
     * @param amount assdasd.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns whether we can add item,
     * !? This might mean some item will be lost.
     * @param item the item to add
     * @return can add
     */
    private boolean canAdd(AbstractItem item) {
        if (item == null || this.getType() != item.getType() || item.getAmount() == 0) {
            return false;
        }
        return this.getAmount() < this.maxAmount;
    }

    /**
     * Counts how many items will be left.
     * @param item item to add
     * @return items left out
     */
    private int itemsLeftOutAfterAdd(AbstractItem item) {
        if (!this.canAdd(item)) return item.getAmount();
        return Math.max(0, item.getAmount() - (this.maxAmount - this.getAmount()));
    }

    /**
     * Add item to this item, and modify original's amount
     * Sets amount to zero if it fits
     * @param item item to try to add
     * @return successfully added all
     */
    public boolean addItem(AbstractItem item) {
        if (!this.canAdd(item)) {
            return false;
        }
        int addAmount = item.getAmount();
        int willBeLeftOut = itemsLeftOutAfterAdd(item);
        this.amount += addAmount - willBeLeftOut;
        item.amount = willBeLeftOut;
        return willBeLeftOut == 0;
    }

    /**
     * Prints.
     * @return yes.
     */
    @Override
    public String toString() {
        return "AbstractItem{" +
                "type=" + type +
                ", amount=" + amount +
                ", maxAmount=" + maxAmount +
                '}';
    }

    /**
     * Clones.
     * @return yes.
     */
    @Override
    public abstract AbstractItem clone();
}
