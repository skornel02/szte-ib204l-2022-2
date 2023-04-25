package prog1.kotprog.dontstarve.solution.level.fields;

import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.HittableFieldType;

/**
 * Hit me harder.
 */
public class HittableField extends PlayableField implements BaseField {

    /**
     * The type of the hittable fiend.
     */
    private final HittableFieldType type;
    /**
     * The durablity of the field.
     */
    private int durabilityLeft;

    /**
     * Hey, bro, hit me.
     * @param type what kind of hit
     */
    public HittableField(HittableFieldType type) {
        super();
        this.type = type;
        durabilityLeft = type.getDurability();
    }

    /**
     * Performs hit action.
     * @param inventory Inventory of the player
     */
    public void hit(BaseInventory inventory) {
        if (durabilityLeft == 0 || inventory == null) {
            return;
        }
        if (type.getRequiredTool() != null
                && (inventory.equippedItem() == null
                    || inventory.equippedItem().getType() != type.getRequiredTool())) {
            return;
        }

        durabilityLeft--;
        if (type.getRequiredTool() != null) {
            inventory.equippedItem().use();
            inventory.checkHand();
        }

        if (durabilityLeft == 0) {
            AbstractItem item = type.getDropItem();
            if (!type.isDirectlyIntoInventory() || !inventory.addItem(item)) {
                addItem(item);
            }
        }
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean hasTree() {
        return type == HittableFieldType.LOG && durabilityLeft != 0;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean hasStone() {
        return type == HittableFieldType.STONE && durabilityLeft != 0;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean hasTwig() {
        return type == HittableFieldType.TWIG && durabilityLeft != 0;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean hasBerry() {
        return type == HittableFieldType.BERRY && durabilityLeft != 0;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean hasCarrot() {
        return type == HittableFieldType.CARROT && durabilityLeft != 0;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean canHaveFire() {
        return durabilityLeft == 0 && !hasFire();
    }
}
