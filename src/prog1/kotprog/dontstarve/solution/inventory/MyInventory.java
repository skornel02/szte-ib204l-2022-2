package prog1.kotprog.dontstarve.solution.inventory;

import prog1.kotprog.dontstarve.solution.annotations.Testable;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.EquippableItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * My inventory implementation.
 */
@Testable
public class MyInventory implements BaseInventory {

    private final AbstractItem[] slots;
    private EquippableItem hand;

    /**
     * Defailt inventory const.
     */
    public MyInventory() {
        this.slots = new AbstractItem[InventoryConstant.maxSize()];
        this.hand = null;
    }

    @Override
    public boolean addItem(AbstractItem item) {
        if (item == null) {
            return false;
        }

        // Try to add.
        for (AbstractItem slot : slots) {
            if (slot != null && slot.addItem(item)) {
                return true;
            }
        }
        // Place in empty
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                if (item.getAmount() > item.getMaxAmount()) {
                    AbstractItem itemToPlace = item.clone();
                    itemToPlace.setAmount(itemToPlace.getMaxAmount());
                    slots[i] = itemToPlace;

                    item.setAmount(item.getAmount() - item.getMaxAmount());
                    return addItem(item);
                } else {
                    slots[i] = item;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public AbstractItem dropItem(int index) {
        if (index < 0 || index > 9) {
            return null;
        }

        AbstractItem toDrop = slots[index];
        slots[index] = null;
        return toDrop;
    }

    private void removeAmount(int index, int amount) {
        if (index < 0 || index > 9 || slots[index] == null) {
            return;
        }

        if (slots[index].getAmount() <= amount) {
            slots[index] = null;
        } else {
            slots[index].setAmount(slots[index].getAmount() - amount);
        }
    }

    @Override
    public boolean removeItem(ItemType type, int amount) {
        int itemCount = Arrays.stream(slots)
                .filter(slot -> slot != null && slot.getType() == type)
                .mapToInt(AbstractItem::getAmount)
                .sum();
        if (itemCount < amount) {
            return false;
        }
        for (int i = 0; i < slots.length && amount > 0; i++) {
            if (slots[i] == null || slots[i].getType() != type)
                continue;

            int amountToRemove = Math.min(amount, slots[i].getAmount());
            amount -= amountToRemove;
            removeAmount(i, amountToRemove);
        }
        return true;
    }

    @Override
    public boolean swapItems(int index1, int index2) {
        if (index1 < 0 || index1 > 9 || index2 < 0 || index2 > 9 || slots[index1] == null || slots[index2] == null) {
            return false;
        }

        AbstractItem helper = slots[index1];
        slots[index1] = slots[index2];
        slots[index2] = helper;

        return true;
    }

    @Override
    public boolean moveItem(int index, int newIndex) {
        if (index < 0 || index > 9 || newIndex < 0 || newIndex > 9 || slots[index] == null || slots[newIndex] != null) {
            return false;
        }

        slots[newIndex] = slots[index];
        slots[index] = null;

        return true;
    }

    @Override
    public boolean combineItems(int index1, int index2) {
        if (index1 < 0 || index1 > 9 || index2 < 0 || index2 > 9 || slots[index1] == null || slots[index2] == null) {
            return false;
        }
        int originalAmount = slots[index2].getAmount();

        if (slots[index1].addItem(slots[index2])) {
            slots[index2] = null;
        }

        return slots[index2] == null || slots[index2].getAmount() != originalAmount;
    }

    @Override
    public boolean equipItem(int index) {
        if (index < 0 || index > 9 || slots[index] == null || !(slots[index] instanceof EquippableItem)) {
            return false;
        }
        AbstractItem helper = hand;
        hand = (EquippableItem) slots[index];
        slots[index] = helper;

        return true;
    }

    @Override
    public EquippableItem unequipItem() {
        EquippableItem itemInHand = hand;
        hand = null;
        if (itemInHand != null && itemInHand.percentage() < 0.001) {
            return null;
        }

        if (emptySlots() == 0 || itemInHand == null) {
            return itemInHand;
        } else {
            addItem(itemInHand);
            return null;
        }
    }

    @Override
    public ItemType cookItem(int index) {
        if (index < 0 || index > 9 || slots[index] == null || !ItemCooker.canCook(slots[index])) {
            return null;
        }

        return removeAndGetType(index);
    }

    @Override
    public ItemType eatItem(int index) {
        if (index < 0 || index > 9 || slots[index] == null || !ItemEater.canEat(slots[index])) {
            return null;
        }

        return removeAndGetType(index);
    }

    private ItemType removeAndGetType(int index) {
        AbstractItem item = slots[index];
        removeAmount(index, 1);

        return item.getType();
    }

    @Override
    public int emptySlots() {
        return (int) Arrays.stream(slots).filter(Objects::isNull).count();
    }

    @Override
    public EquippableItem equippedItem() {
        return hand;
    }

    @Override
    public AbstractItem getItem(int index) {
        if (index < 0 || index > 9) {
            return null;
        }

        return slots[index];
    }

    @Override
    public int countItemType(ItemType type) {
        int total = 0;
        for (int i = 0; i < slots.length; i++) {
            if (getItem(i) != null && getItem(i).getType() == type) {
                total += getItem(i).getAmount();
            }
        }
        return total;
    }

    @Override
    public void checkHand() {
        if (hand != null && hand.isBroken()) {
            hand = null;
        }
    }

    /**
     * Prints.
     * @return formatted.
     */
    @Override
    public String toString() {
        return "MyInventory{\n" +
                "slots=" + Arrays.stream(slots).map(slot -> " - " + (slot != null ? slot.toString() : "<>")).collect(Collectors.joining("\n")) +
                ",\nhand=" + hand +
                "\n}";
    }
}
