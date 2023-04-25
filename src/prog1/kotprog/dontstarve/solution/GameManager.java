package prog1.kotprog.dontstarve.solution;

import prog1.kotprog.dontstarve.solution.ai.AttackState;
import prog1.kotprog.dontstarve.solution.ai.DisabledAiState;
import prog1.kotprog.dontstarve.solution.character.BaseCharacter;
import prog1.kotprog.dontstarve.solution.character.Character;
import prog1.kotprog.dontstarve.solution.character.actions.Action;
import prog1.kotprog.dontstarve.solution.character.actions.ActionDied;
import prog1.kotprog.dontstarve.solution.character.actions.ActionNone;
import prog1.kotprog.dontstarve.solution.level.BaseField;
import prog1.kotprog.dontstarve.solution.level.Level;
import prog1.kotprog.dontstarve.solution.level.PositionCreator;
import prog1.kotprog.dontstarve.solution.utility.GameStatus;
import prog1.kotprog.dontstarve.solution.utility.Position;
import prog1.kotprog.dontstarve.solution.utility.RandomStarter;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

/**
 * A játék működéséért felelős osztály.<br>
 * Az osztály a singleton tervezési mintát valósítja meg.
 */
public final class GameManager {
    /**
     * Az osztályból létrehozott egyetlen példány (nem lehet final).
     */
    private static GameManager instance = new GameManager();

    /**
     * Random objektum, amit a játék során használni lehet.
     */
    private final Random random = new Random();

    /**
     * A játékban lévő mezők térképe.
     * x,y koordinátákkal.
     */
    private Level level;

    /**
     * The status of the game
     */
    private GameStatus gameStatus;

    /**
     * Counts the ingame time.
     */
    private int gameTickCounter;

    /**
     * Tutorial mode enabled
     */
    private boolean tutorialMode;

    private final List<BaseCharacter> characters;

    /**
     * Az osztály privát konstruktora.
     */
    private GameManager() {
        this.gameStatus = GameStatus.LOAD_MAP;
        this.gameTickCounter = 0;
        this.tutorialMode = false;
        this.characters = new LinkedList<>();
    }

    /**
     * Az osztályból létrehozott példány elérésére szolgáló metódus.
     * @return az osztályból létrehozott példány
     */
    public static GameManager getInstance() {
        return instance;
    }

    /**
     * A létrehozott random objektum elérésére szolgáló metódus.
     * @return a létrehozott random objektum
     */
    public Random getRandom() {
        return random;
    }

    /**
     * Egy karakter becsatlakozása a játékba.<br>
     * A becsatlakozásnak számos feltétele van:
     * <ul>
     *     <li>A pálya már be lett töltve</li>
     *     <li>A játék még nem kezdődött el</li>
     *     <li>Csak egyetlen emberi játékos lehet, a többi karaktert a gép irányítja</li>
     *     <li>A névnek egyedinek kell lennie</li>
     * </ul>
     * @param name a csatlakozni kívánt karakter neve
     * @param player igaz, ha emberi játékosról van szó; hamis egyébként
     * @return a karakter pozíciója a pályán, vagy (Integer.MAX_VALUE, Integer.MAX_VALUE) ha nem sikerült hozzáadni
     */
    public Position joinCharacter(String name, boolean player) {
        if (gameStatus != GameStatus.JOINING
                || (player && this.characters.stream().anyMatch(BaseCharacter::isPlayer))
                || this.characters.stream().anyMatch(character -> Objects.equals(character.getName(), name))) {
            return new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        Position position = new PositionCreator(
                this.level,
                characters.stream()
                    .map(BaseCharacter::getCurrentPosition)
                    .toArray(Position[]::new)
                )
                .createPosition();

        BaseCharacter character = new Character(name, player, position);
        RandomStarter starter = new RandomStarter(this.random, character.getInventory());
        starter.createStarter();

        if (!player) {
            character.setAiState(tutorialMode ? new DisabledAiState() : new AttackState());
            //character.setAiState(new DisabledAiState());
        }

        this.characters.add(character);
        return position.clone();
    }

    /**
     * Egy adott nevű karakter lekérésére szolgáló metódus.<br>
     * @param name A lekérdezni kívánt karakter neve
     * @return Az adott nevű karakter objektum, vagy null, ha már a karakter meghalt vagy nem is létezett
     */
    public BaseCharacter getCharacter(String name) {
        return this.characters.stream()
                .filter(BaseCharacter::isAlive)
                .filter(character -> Objects.equals(character.getName(), name))
                .findAny().orElse(null);
    }

    /**
     * Ezen metódus segítségével lekérdezhető, hogy hány karakter van még életben.
     * @return Az életben lévő karakterek száma
     */
    public int remainingCharacters() {
        return (int) this.characters.stream()
                .filter(BaseCharacter::isAlive)
                .count();
    }

    /**
     * Ezen metódus segítségével történhet meg a pálya betöltése.<br>
     * A pálya betöltésének azelőtt kell megtörténnie, hogy akár 1 karakter is csatlakozott volna a játékhoz.<br>
     * A pálya egyetlen alkalommal tölthető be, később nem módosítható.
     * @param level a fájlból betöltött pálya
     */
    public void loadLevel(Level level) {
        if (this.gameStatus != GameStatus.LOAD_MAP) {
            return;
        }

        level.loadMap();
        this.level = level;
        this.gameStatus = GameStatus.JOINING;
    }

    /**
     * A pálya egy adott pozícióján lévő mező lekérdezésére szolgáló metódus.
     * @param x a vízszintes (x) irányú koordináta
     * @param y a függőleges (y) irányú koordináta
     * @return az adott koordinátán lévő mező
     */
    public BaseField getField(int x, int y) {
        if (this.gameStatus == GameStatus.LOAD_MAP) {
            return null;
        }

        return this.level.getField(x, y);
    }

    /**
     * A játék megkezdésére szolgáló metódus.<br>
     * A játék csak akkor kezdhető el, ha legalább 2 karakter már a pályán van,
     * és közülük pontosan az egyik az emberi játékos által irányított karakter.
     * @return igaz, ha sikerült elkezdeni a játékot; hamis egyébként
     */
    public boolean startGame() {
        if (gameStatus != GameStatus.JOINING || this.characters.size() < 2 || this.characters.stream().noneMatch(BaseCharacter::isPlayer)) {
            return false;
        }

        this.characters.forEach(character -> character.setLastAction(new ActionNone()));

        gameStatus = GameStatus.IN_GAME;
        return true;
    }

    /**
     * Ez a metódus jelzi, hogy 1 időegység eltelt.<br>
     * A metódus először lekezeli a felhasználói inputot, majd a gépi ellenfelek cselekvését végzi el,
     * végül eltelik egy időegység.<br>
     * Csak akkor csinál bármit is, ha a játék már elkezdődött, de még nem fejeződött be.
     * @param action az emberi játékos által végrehajtani kívánt akció
     */
    public void tick(Action action) {
        if (gameStatus != GameStatus.IN_GAME) {
            return;
        }

        BaseCharacter player = this.characters.stream()
                .filter(BaseCharacter::isPlayer)
                .findAny().orElseThrow(() -> new IllegalArgumentException("No player character found after start"));

        BaseCharacter[] enemies = this.characters.stream()
                .filter(Predicate.not(BaseCharacter::isPlayer))
                .filter(BaseCharacter::isAlive)
                .toArray(BaseCharacter[]::new);

        action.handleAction(player, level, this, this.characters);
        player.setLastAction(action);

        for (BaseCharacter enemy : enemies) {
            Action enemyAction = enemy.getNextAiAction(level, this, this.characters);
            enemy.setLastAction(enemyAction);
            enemyAction.handleAction(enemy, level, this, this.characters);
        }

        if (!player.isAlive() || this.remainingCharacters() == 1) {
            gameStatus = GameStatus.ENDED;
        }

        this.characters.stream()
                .filter(BaseCharacter::isAlive)
                .forEach(character -> {
                    if (character.tickHunger()) {
                        new ActionDied().handleAction(character, level, this, null);
                    }
                });

        this.level.tick();

        this.characters.stream().map(BaseCharacter::getInventory)
                .filter(inv -> inv.equippedItem() != null)
                .forEach(inv -> {
                    inv.equippedItem().tick();
                    inv.checkHand();
                });

        gameTickCounter++;
    }

    /**
     * Ezen metódus segítségével lekérdezhető az aktuális időpillanat.<br>
     * A játék kezdetekor ez az érték 0 (tehát a legelső időpillanatban az idő 0),
     * majd minden eltelt időpillanat után 1-gyel növelődik.
     * @return az aktuális időpillanat
     */
    public int time() {
        return gameTickCounter;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük a játék győztesét.<br>
     * Amennyiben a játéknak még nincs vége (vagy esetleg nincs győztes), akkor null-t ad vissza.
     * @return a győztes karakter vagy null
     */
    public BaseCharacter getWinner() {
        if (this.gameStatus != GameStatus.ENDED) {
            return null;
        }

        return this.characters.stream()
                .filter(BaseCharacter::isAlive)
                .findFirst().orElse(null);
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék elkezdődött-e már.
     * @return igaz, ha a játék már elkezdődött; hamis egyébként
     */
    public boolean isGameStarted() {
        return gameStatus == GameStatus.IN_GAME;
    }

    /**
     * Ezen metódus segítségével lekérdezhetjük, hogy a játék befejeződött-e már.
     * @return igaz, ha a játék már befejeződött; hamis egyébként
     */
    public boolean isGameEnded() {
        return gameStatus == GameStatus.ENDED;
    }

    /**
     * Ezen metódus segítségével beállítható, hogy a játékot tutorial módban szeretnénk-e elindítani.<br>
     * Alapértelmezetten (ha nem mondunk semmit) nem tutorial módban indul el a játék.<br>
     * Tutorial módban a gépi karakterek nem végeznek cselekvést, csak egy helyben állnak.<br>
     * A tutorial mód beállítása még a karakterek csatlakozása előtt történik.
     * @param tutorial igaz, amennyiben tutorial módot szeretnénk; hamis egyébként
     */
    public void setTutorial(boolean tutorial) {
        tutorialMode = tutorial;
    }
}
