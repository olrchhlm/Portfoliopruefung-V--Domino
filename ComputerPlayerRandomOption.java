import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * Hazardierender Computer-Spieler, der immer eine zufällige Möglichkeit wählt.
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public class ComputerPlayerRandomOption extends ComputerPlayer{
    Random randomNumber = new Random(); 

    ComputerPlayerRandomOption(String name, ArrayList<Domino> dominosHoldingOnHand){
        super(name, dominosHoldingOnHand);
    }

    @Override
    public Optional<Domino> makeMove(Domino dominoOnTable){
        ArrayList<Domino> possibleChoices = createPossibleChoices(dominoOnTable);
        Optional<Domino> playersMove = Optional.empty();
        if(possibleChoices.isEmpty()){
            System.out.println("Keine Anlegemöglichkeit");
        } else {
            int choiceMade = generateRandomNumber(possibleChoices.size() + 1);
            if(choiceMade < possibleChoices.size()){
                printChosenDomino(getName(), possibleChoices.get(choiceMade));
                playersMove = Optional.of(putDominoOnTable(dominoOnTable, possibleChoices.get(choiceMade))); 
            } else {
                System.out.println("Ziehe Stein");
            }
        }
        return playersMove; 
    }
    
    @Override
    public int selectSide(){
        int selectedSide = generateRandomNumber(2);
        printChosenSide(getName(), selectedSide);
        return selectedSide;
    }

    private int generateRandomNumber(int limit){
        return randomNumber.nextInt(limit);
    }
}
