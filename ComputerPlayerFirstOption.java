import java.util.ArrayList;
import java.util.Optional;

/**
 * Einfältiger Computer-Spieler, der immer die erste Möglichkeit wählt.
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public class ComputerPlayerFirstOption extends ComputerPlayer {
    ComputerPlayerFirstOption(String name, ArrayList<Domino> dominosHoldingOnHand){
        super(name, dominosHoldingOnHand);
    }
    
    @Override
    public int selectSide(){
        printChosenSide(getName(), 0);
        return 0;
    }
    
    @Override
    public Optional<Domino> makeMove(Domino dominoOnTable){
        ArrayList<Domino> possibleChoices = createPossibleChoices(dominoOnTable);
        Optional<Domino> playersMove = Optional.empty();
        if(possibleChoices.isEmpty()){
            System.out.println("Keine Anlegemöglichkeit");
        } else {
            printChosenDomino(getName(), possibleChoices.get(0));
            playersMove = Optional.of(putDominoOnTable(dominoOnTable, possibleChoices.get(0))); 
        }
        return playersMove; 
    }
}
