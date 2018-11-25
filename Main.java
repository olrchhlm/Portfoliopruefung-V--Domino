import java.util.Random;

/**
 * Erzeugt ein DominoGame mit einem menschlichen Spieler sowie einem ComputerPlayerFirstOption.
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public class Main{
    public void startDomino(){
        DominoGame dominoGame = new DominoGame();
        dominoGame.addHumanPlayer("Sie");
        dominoGame.addComputerPlayerFirstOption("Ich");
        
        dominoGame.startGame();
    }
}
