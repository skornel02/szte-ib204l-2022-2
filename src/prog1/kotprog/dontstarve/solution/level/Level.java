package prog1.kotprog.dontstarve.solution.level;

import prog1.kotprog.dontstarve.solution.level.fields.EmptyField;
import prog1.kotprog.dontstarve.solution.level.fields.HittableField;
import prog1.kotprog.dontstarve.solution.utility.Position;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A pályát leíró kép betöltéséért felelő osztály.
 */
public class Level {
    /**
     * A kép, ami a pályát tartalmazza.
     */
    private BufferedImage image;

    private BaseField[][] fields;

    /**
     * Konstruktor, amely a megadott fájlból beolvassa a pályát.
     * @param fileName a fájl, amely a pályát tartalmazza
     */
    public Level(String fileName) {
        try {
            image = ImageIO.read(new File(fileName));
        } catch (IOException e) {
            System.err.println("A pálya beolvasása nem sikerült!");
        }
    }

    /**
     * A pálya magasságát lekérdező metódus.
     * @return a pálya magassága
     */
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * A pálya szélességét lekérdező metódus.
     * @return a pály szélessége
     */
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * A pályát reprezentáló kép egy adott pixelének színét lekérdező metódus.<br>
     * @param x a képpont oszlopszáma
     * @param y a képpont sorszáma
     * @return a kép adott pozíciójának színe
     */
    public int getColor(int x, int y) {
        return image.getRGB(x, y);
    }

    /**
     * A pálya egy adott pozíciójának mezőjét lekérdező metódus.
     */
    public void loadMap() {
        fields = new BaseField[getWidth()][getHeight()];

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                switch (getColor(x, y)) {
                    case MapColors.EMPTY -> fields[x][y] = new EmptyField(false);
                    case MapColors.WATER -> fields[x][y] = new EmptyField(true);
                    case MapColors.TREE -> fields[x][y] = new HittableField(HittableFieldType.LOG);
                    case MapColors.STONE -> fields[x][y] = new HittableField(HittableFieldType.STONE);
                    case MapColors.TWIG -> fields[x][y] = new HittableField(HittableFieldType.TWIG);
                    case MapColors.BERRY -> fields[x][y] = new HittableField(HittableFieldType.BERRY);
                    case MapColors.CARROT -> fields[x][y] = new HittableField(HittableFieldType.CARROT);
                    default -> throw new IllegalArgumentException("Ismeretlen mező típus: " + getColor(x, y));
                }
            }
        }
    }

    /**
     * A pálya egy adott pozíciójának mezőjét lekérdező metódus.
     * @param x a mező x koordinátája
     * @param y a mező y koordinátája
     * @return a mező
     */
    public BaseField getField(float x, float y) {
        int realX = Math.round(x);
        int realY = Math.round(y);

        if (realX < 0 || realX >= getWidth() || realY < 0 || realY >= getHeight()) {
            return null;
        }

        return this.fields[realX][realY];
    }

    /**
     * A pálya egy adott pozíciójának mezőjét lekérdező metódus.
     * @param position a mező pozíciója
     * @return a mező
     */
    public BaseField getField(Position position) {
        return getField(position.getX(), position.getY());
    }

    /**
     * Tickelés.
     */
    public void tick() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                fields[x][y].tick();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getWidth()).append("|").append(getHeight()).append("\n");

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                String item = switch (getColor(x, y)) {
                    case MapColors.EMPTY -> "-";
                    case MapColors.WATER -> "~";
                    case MapColors.TREE -> "T";
                    case MapColors.STONE -> "o";
                    case MapColors.TWIG -> "t";
                    case MapColors.BERRY -> "b";
                    case MapColors.CARROT -> "c";
                    default -> "x";
                };
                sb.append(item);
            }
        }
        sb.append("\n");

        return sb.toString();
    }
}
