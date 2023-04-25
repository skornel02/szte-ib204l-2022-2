package prog1.kotprog.dontstarve.solution.utility;

/**
 * Egy 2 dimenziós (x, y) koordinátát leíró osztály.
 */
public class Position implements Cloneable {
    /**
     * vízszintes (x) irányú koordináta.
     */
    private float x;

    /**
     * függőleges (y) irányú koordináta.
     */
    private float y;

    /**
     * Két paraméteres konstruktor, amely segítségével egy új pozíciót lehet létrehozni.
     * @param x vízszintes (x) irányú koordináta
     * @param y függőleges (y) irányú koordináta
     */
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Az aktuális koordinátához legközelebbi, csak egész értékű komponensekből álló koordináta kiszámítása.<br>
     * A kerekítés a matematika szabályainak megfelelően történik.
     * @return a kiszámolt pozíció
     */
    public Position getNearestWholePosition() {
        return new Position(Math.round(x), Math.round(y));
    }

    /**
     * x koordináta gettere.
     * @return x koordináta
     */
    public float getX() {
        return x;
    }

    /**
     * y koordináta gettere.
     * @return y koordináta
     */
    public float getY() {
        return y;
    }

    /**
     * x koordináta settere.
     * @param x új x érték
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * y koordináta settere.
     * @param y új y érték
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * Asd.
     * @return sss.
     */
    @Override
    public String toString() {
        return "Position(" +
                "" + x +
                ", " + y +
                ')';
    }

    public float getDistance(Position position) {
        return (float) Math.sqrt(Math.pow(position.getX() - this.getX(), 2) + Math.pow(position.getY() - this.getY(), 2));
    }

    /**
     * A paraméterként megadott pozícióhoz képest visszaadja a pozícióba lépés irányát.
     * @param position a cél pozíció
     * @return a lépés iránya
     */
    public Direction getDirectionTo(Position position) {
        float xDiff = position.getX() - this.getX();
        float yDiff = position.getY() - this.getY();

        if (Math.abs(xDiff) > Math.abs(yDiff)) {
            return xDiff > 0 ? Direction.RIGHT : Direction.LEFT;
        } else {
            return yDiff > 0 ? Direction.DOWN : Direction.UP;
        }
    }

    /**
     * A paraméterként megadott irányba és sebességgel lép a pozícióba.
     * @param direction a lépés iránya
     * @param speed a lépés sebessége
     * @return a lépés utáni pozíció
     */
    public Position getNextPosition(Direction direction, float speed) {
        return switch (direction) {
            case UP -> new Position(this.getX(), this.getY() - speed);
            case DOWN -> new Position(this.getX(), this.getY() + speed);
            case LEFT -> new Position(this.getX() - speed, this.getY());
            case RIGHT -> new Position(this.getX() + speed, this.getY());
        };
    }

    @Override
    public Position clone() {
        return new Position(x, y);
    }
}
