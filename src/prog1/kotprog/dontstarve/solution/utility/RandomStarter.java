package prog1.kotprog.dontstarve.solution.utility;

import prog1.kotprog.dontstarve.solution.inventory.BaseInventory;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemLog;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawBerry;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemRawCarrot;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemStone;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTwig;

import java.util.Random;

/**
 * This will create random starter.
 * From a random and an inventory.
 */
public class RandomStarter {

    private final Random random;
    private final BaseInventory inventory;

    /**
     * This will create a random starter.
     * @param random random.
     * @param inventory inventory.
     */
    public RandomStarter(Random random, BaseInventory inventory) {
        this.random = random;
        this.inventory = inventory;
    }

    /**
     * This will create a starter.
     */
    public void createStarter() {
        for (int i = 0; i < 4; i++) {
            switch (this.random.nextInt(5)) {
                case 1:
                    this.inventory.addItem(new ItemLog(1));
                    break;
                case 3:
                    this.inventory.addItem(new ItemStone(1));
                    break;
                case 2:
                    this.inventory.addItem(new ItemTwig(1));
                    break;
                case 4:
                    this.inventory.addItem(new ItemRawBerry(1));
                    break;
                case 0:
                    this.inventory.addItem(new ItemRawCarrot(1));
                    break;
                default:
                    break;
            }
        }
    }
}
