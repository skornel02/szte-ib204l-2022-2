package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemLog;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemStone;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;

public enum HittableFieldType {
    /**
     * Wooodd.
     */
    LOG(ItemType.AXE, 4, 2, false),
    /**
     * Stooone.
     */
    STONE(ItemType.PICKAXE, 5, 3, false),
    /**
     *  Twiiig.
     */
    TWIG(null, 2, 1, true),
    /**
     * Raspberry pi.
     */
    BERRY(null, 1, 1, true),
    /**
     * Repaaa.
     */
    CARROT(null, 1, 1, true);

    /**
     * Required tool to get item.
     */
    private final ItemType requiredTool;
    /**
     * Times you need to hit it.
     */
    private final int durability;
    /**
     * The amount it drops.
     */
    private final int dropAmount;
    /**
     * The item it drops.
     */
    private final boolean directlyIntoInventory;

    /**
     * Hidden constructor.
     *
     * @param requiredTool          a
     * @param durability            b
     * @param dropAmount            d
     * @param directlyIntoInventory c
     */
    HittableFieldType(ItemType requiredTool, int durability, int dropAmount, boolean directlyIntoInventory) {
        this.requiredTool = requiredTool;
        this.durability = durability;
        this.dropAmount = dropAmount;
        this.directlyIntoInventory = directlyIntoInventory;
    }

    /**
     * Get the dropped item.
     * @return dropped item with amount.
     */
    public AbstractItem getDropItem() {
        return switch (this) {
            case LOG -> new ItemLog(dropAmount);
            case TWIG -> new ItemTwig(dropAmount);
            case STONE -> new ItemStone(dropAmount);
            case BERRY -> new ItemRawBerry(dropAmount);
            case CARROT -> new ItemRawCarrot(dropAmount);
        };
    }

    /**
     * abc.
     * @return required tool.
     */
    public ItemType getRequiredTool() {
        return requiredTool;
    }

    /**
     * efg.
     * @return default durability.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Should go into inventory.
     * @return yes/no.
     */
    public boolean isDirectlyIntoInventory() {
        return directlyIntoInventory;
    }
}
