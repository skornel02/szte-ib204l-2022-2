package prog1.kotprog.dontstarve.solution.ai;

import prog1.kotprog.dontstarve.solution.GameManager;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionNone;
import prog1.kotprog.dontstarve.solution.level.Level;

import java.util.List;

/**
 * AI state.
 */
public interface AiState {

    /**
     * Get the action of the state.
     * @return action.
     */
    default Action getAction() {
        return new ActionNone();
    }

    /**
     * Get the next state.
     * @param character character.
     * @param level level.
     * @param manager manager.
     * @param characters characters.
     * @return next state.
     */
    default AiState nextState(BaseCharacter character, Level level, GameManager manager, List<BaseCharacter> characters) {
        return this;
    }

    /**
     * Is thinking phase.
     * Can do multiple thinking phase in a single action.
     * @return true if thinking phase.
     */
    default boolean isThinkingPhase() {
        return false;
    }

}
