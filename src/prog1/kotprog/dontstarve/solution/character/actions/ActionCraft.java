package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemAxe;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemFire;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemPickaxe;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemSpear;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemTorch;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A kraftolás akció leírására szolgáló osztály: adott típusú item kraftolása.
 */
public class ActionCraft extends Action {
    /**
     * A lekraftolandó item típusa.
     */
    private final ItemType itemType;

    /**
     * Az akció létrehozására szolgáló konstruktor.
     *
     * @param itemType a lekraftolandó item típusa
     */
    public ActionCraft(ItemType itemType) {
        super(ActionType.CRAFT);
        this.itemType = itemType;
    }

    /**
     * Az itemType gettere.
     * @return a lekraftolandó item típusa
     */
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseField field = level.getField(character.getCurrentPosition());

        if (itemType == ItemType.FIRE && !field.canHaveFire()) {
            return;
        }

        int twigs = character.getInventory().countItemType(ItemType.TWIG);
        int logs = character.getInventory().countItemType(ItemType.LOG);
        int stones = character.getInventory().countItemType(ItemType.STONE);

        AbstractItem tool = null;
        switch (itemType) {
            case AXE -> {
                if (twigs < 3) {
                    break;
                }

                character.getInventory().removeItem(ItemType.TWIG, 3);
                tool = new ItemAxe();
            }
            case PICKAXE -> {
                if (twigs < 2 || logs < 2) {
                    break;
                }

                character.getInventory().removeItem(ItemType.TWIG, 2);
                character.getInventory().removeItem(ItemType.LOG, 2);
                tool = new ItemPickaxe();
            }
            case SPEAR -> {
                if (stones < 2 || logs < 2) {
                    break;
                }

                character.getInventory().removeItem(ItemType.STONE, 2);
                character.getInventory().removeItem(ItemType.LOG, 2);
                tool = new ItemSpear();
            }
            case TORCH -> {
                if (twigs < 3 || logs < 1) {
                    break;
                }

                character.getInventory().removeItem(ItemType.TWIG, 3);
                character.getInventory().removeItem(ItemType.LOG, 1);
                tool = new ItemTorch();
            }
            case FIRE -> {
                if (twigs < 2 || logs < 2 || stones < 4) {
                    break;
                }

                character.getInventory().removeItem(ItemType.TWIG, 2);
                character.getInventory().removeItem(ItemType.LOG, 2);
                character.getInventory().removeItem(ItemType.STONE, 4);
                tool = new ItemFire();
            }
        }
        if (tool != null && (itemType == ItemType.FIRE || !character.getInventory().addItem(tool))) {
            field.addItem(tool);
        }
    }
}
