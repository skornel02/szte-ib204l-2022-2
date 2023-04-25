package prog1.kotprog.dontstarve.solution.ai;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.actions.*;
import prog1.kotprog.dontstarve.solution.inventory.items.ItemType;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.utility.Direction;

import java.util.List;

public class AttackState implements AiState {

    /**
     * Az aktuális akció.
     */
    private Action action = new ActionNone();

    /**
     * Az aktuális állapot.
     */
    private AiStateEnums.AttackStates state = AiStateEnums.AttackStates.SEEK;

    /**
     * Visszaadja az aktuális állapotot.
     *
     * @return Az aktuális állapot.
     */
    @Override
    public Action getAction() {
        return action;
    }

    /**
     * Get the next state.
     * @param character character.
     * @param level level.
     * @param manager manager.
     * @param characters characters.
     * @return next state.
     */
    @Override
    public AiState nextState(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        for (int i = 0; i < 10; i++) {
            var item =character.getInventory().getItem(i);
            if (item == null)
                continue;

            if (item.getType() == ItemType.TWIG && item.getAmount() >= 3) {
                action = new ActionCraft(ItemType.AXE);
                return this;
            }
            if (item.getType() == ItemType.AXE) {
                action = new ActionEquip(i);
                return this;
            }
        }

        BaseCharacter player = characters.stream().filter(BaseCharacter::isPlayer).findAny()
                .orElseThrow(() -> new IllegalStateException("Player not found"));

        BaseCharacter closest = characters.stream()
                .filter(BaseCharacter::isAlive)
                .filter(c -> c != character).min((c1, c2) -> Float.compare(c1.getCurrentPosition().getDistance(character.getCurrentPosition()),
                        c2.getCurrentPosition().getDistance(character.getCurrentPosition())))
                .orElseThrow(() -> new IllegalStateException("Closest not found"));

        float distance = player.getCurrentPosition().getDistance(character.getCurrentPosition());
        Direction directionToMove = character.getCurrentPosition().getDirectionTo(player.getCurrentPosition());

        if (distance == 0) {
            if (closest.isPlayer()) {
                state = AiStateEnums.AttackStates.ATTACK;
                action = new ActionAttack();
            } else {
                action = new ActionStep(Direction.LEFT);
                state = AiStateEnums.AttackStates.SEEK;
            }
        } else if (distance <= 2) {
            if (closest.isPlayer()) {
                state = AiStateEnums.AttackStates.ATTACK;
                action = new ActionStepAndAttack(directionToMove);
            } else {
                action = new ActionStep(Direction.LEFT);
                state = AiStateEnums.AttackStates.SEEK;
            }
        } else {
            state = AiStateEnums.AttackStates.SEEK;
            action = new ActionStep(directionToMove);
        }

        return this;
    }

    /**
     * Is thinking phase.
     * Can do multiple thinking phase in a single action.
     * @return thinking phase.
     */
    @Override
    public String toString() {
        return "AttackState{" +
                "state=" + state +
                '}';
    }
}
