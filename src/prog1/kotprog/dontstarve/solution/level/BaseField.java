package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.inventory.items.AbstractItem;

/**
 * A pálya egy általános mezőjét leíró interface.
 */
public interface BaseField {
    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mező járható-e.
     * @return igaz, amennyiben a mező járható; hamis egyébként
     */
    default boolean isWalkable() {
        return true;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e fa.
     * @return igaz, amennyiben van fa; hamis egyébként
     */
    default boolean hasTree() {
        return false;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e kő.
     * @return igaz, amennyiben van kő; hamis egyébként
     */
    default boolean hasStone() {
        return false;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e gally.
     * @return igaz, amennyiben van gally; hamis egyébként
     */
    default boolean hasTwig() {
        return false;
    }

    /**
     * Ezen metódus segítségével lekérdezheő, hogy a mezőn van-e bogyó.
     * @return igaz, amennyiben van bogyó; hamis egyébként
     */
    default boolean hasBerry() {
        return false;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e répa.
     * @return igaz, amennyiben van répa; hamis egyébként
     */
    default boolean hasCarrot() {
        return false;
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e tárgy.
     */
    void tick();

    /**
     * Can place fire on it
     * @return yes/no
     */
    boolean canHaveFire();

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e tűz rakva.
     * @return igaz, amennyiben van tűz; hamis egyébként
     */
    boolean hasFire();

    /**
     * Ezen metódus segítségével lekérdezhető, hogy a mezőn van-e uresseg.
     * @return yes
     */
    default boolean empty() {
        return isWalkable()
                && !hasFire()
                && !hasCarrot()
                && !hasBerry()
                && !hasTwig()
                && !hasTree()
                && !hasStone();
    }

    /**
     * Ezen metódus segítségével a mezőn lévő tárgyak lekérdezhetők.<br>
     * A tömbben az a tárgy jön hamarabb, amelyik korábban került az adott mezőre.<br>
     * A karakter ha felvesz egy tárgyat, akkor a legkorábban a mezőre kerülő tárgyat fogja felvenni.<br>
     * Ha nem sikerül felvenni, akkor a (nem) felvett tárgy a tömb végére kerül.
     * @return a mezőn lévő tárgyak
     */
    AbstractItem[] items();

    /**
     * Ezen metódus segítségével a mezőre lehet tárgyat helyezni.
     * @param item a helyezendő tárgy
     */
    void addItem(AbstractItem item);

    /**
     * Ezen metódus segítségével a mezőről lehet tárgyat felvenni.
     * @return a felvett tárgy
     */
    AbstractItem pickupItem();

}
