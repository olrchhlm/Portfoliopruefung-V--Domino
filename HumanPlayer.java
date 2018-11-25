import java.util.ArrayList;
import java.util.Optional;

/**
 * Ermöglicht die Teilnahme von menschlichen Spielern mittels Eingabekonsole. 
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public class HumanPlayer extends Player{
    private UserDialog userDialog = new UserDialog();

    public HumanPlayer(String name, ArrayList<Domino> dominosHoldingOnHand){
        super(name, dominosHoldingOnHand);
    }

    @Override
    public Optional<Domino> makeMove(Domino dominoOnTable){
        System.out.println("Ihre Steine: " + printdominosHoldingOnHand());
        ArrayList<Domino> possibleChoices = createPossibleChoices(dominoOnTable);
        Optional<Domino> playersMove = Optional.empty();
        if(possibleChoices.isEmpty()){
            System.out.println("Keine Auswahlmöglichkeit");
        } else {
            int choiceMadeByHuman = userDialog.getUserInput("Auswahlmöglichkeiten: ", possibleChoicesToStringArray(possibleChoices));
            if(choiceMadeByHuman < possibleChoices.size()){
                playersMove = Optional.of(putDominoOnTable(dominoOnTable, possibleChoices.get(choiceMadeByHuman)));
            }
        }
        return playersMove; 
    } 

    @Override
    public int selectSide(){
        return userDialog.getUserInput("Auswahlmöglichkeiten: ", "links anlegen", "rechts anlegen");
    }

    private String[] possibleChoicesToStringArray(ArrayList<Domino> possibleChoices){
        String[] possibleChoicesAsStringArray = new String[possibleChoices.size() + 1]; 
        for (int i = 0; i < possibleChoices.size(); i++){
            possibleChoicesAsStringArray[i] = possibleChoices.get(i).dominoAsString();
        }
        possibleChoicesAsStringArray[possibleChoicesAsStringArray.length - 1] = "ziehen"; 
        return possibleChoicesAsStringArray; 
    } 
}
