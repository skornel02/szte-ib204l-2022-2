package prog1.kotprog.dontstarve.solution.character.actions;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.inventory.ItemDamager;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * A támadás akció leírására szolgáló osztály: a legközelebbi karakter megtámadása.
 */
public class ActionAttack extends Action {
    /**
     * Az akció létrehozására szolgáló konstruktor.
     */
    public ActionAttack() {
        super(ActionType.ATTACK);
    }

    @Override
    public void handleAction(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        BaseCharacter closest = null;
        float distance = Float.MAX_VALUE;

        BaseCharacter[] enemies = characters.stream()
                .filter(BaseCharacter::isAlive)
                .filter(c -> c != character)
                .toArray(BaseCharacter[]::new);

        for (BaseCharacter enemy : enemies) {
            float d = character.getCurrentPosition().getDistance(enemy.getCurrentPosition());
            if (d < distance) {
                closest = enemy;
                distance = d;
            }
        }

        if (distance <= 2) {
            int dmg = ItemDamager.getDamage(character.getInventory().equippedItem());

            if (character.getInventory().equippedItem() != null) {
                character.getInventory().equippedItem().use();
            }
            character.getInventory().checkHand();

            if (closest.takeDamage(dmg)) {
                new ActionDied().handleAction(closest, level, manager, null);
            }
        }

    }
}
