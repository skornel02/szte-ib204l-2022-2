package prog1.kotprog.dontstarve.solution.level.fields;

import prog1.kotprog.dontstarve.solution.level.BaseField;

/**
 * Az üres mezőt reprezentáló osztály.
 */
public class EmptyField extends PlayableField implements BaseField {

    /**
     * A mező víz-e.
     */
    private final boolean water;

    /**
     * Az üres mező konstruktora.
     * @param water asd.
     */
    public EmptyField(boolean water) {
        this.water = water;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean isWalkable() {
        return !water;
    }

    /**
     * A mező víz-e.
     * @return a mező víz-e
     */
    @Override
    public boolean canHaveFire() {
        if (water) {
            return false;
        }

        return super.canHaveFire();
    }
}
