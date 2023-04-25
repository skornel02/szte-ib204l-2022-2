package prog1.kotprog.dontstarve.solution.level.fields;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemFire;
import prog1.kotprog.dontstarve.solution.level.BaseField;

import java.util.ArrayList;
import java.util.List;

/**
 * Kezeli a tárgyakat a mezőkön és a tábortüzet.
 */
public abstract class PlayableField implements BaseField {
    /**
     * The containeeer
     */
    private final List<AbstractItem> items;

    private int fireTicks;

    /**
     * The constructor
     */
    public PlayableField() {
        this.items = new ArrayList<>();
        this.fireTicks = 0;
    }

    /**
     * Get items.
     * @return the items
     */
    @Override
    public AbstractItem[] items() {
        return items.toArray(new AbstractItem[0]);
    }

    /**
     * Adds item to ground
     * @param item to add MAY BE NULL!
     */
    @Override
    public void addItem(AbstractItem item) {
        if (item != null) {
            if (item instanceof ItemFire) {
                fireTicks = 61;
            } else {
                items.add(item);
            }
        }
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public AbstractItem pickupItem() {
        if (items.isEmpty()) {
            return null;
        }
        AbstractItem item = items.get(0);
        items.remove(0);
        return item;
    }

    /**
     * A mező víz-e.
     */
    @Override
    public void tick() {
        if (fireTicks > 0) {
            fireTicks--;
        }
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean canHaveFire() {
        return fireTicks == 0;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean hasFire() {
        return fireTicks > 0;
    }
}
