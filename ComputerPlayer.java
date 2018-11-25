import java.util.ArrayList;
import java.util.Optional;

/**
 * Vererbt an die Klassen ComputerPlayerRandomOption und ComputerPlayerFirstOption
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public abstract class ComputerPlayer extends Player{
    ComputerPlayer(String name, ArrayList<Domino> dominosHoldingOnHand){
        super(name, dominosHoldingOnHand);
    }
    
    protected void printChosenDomino(String name, Domino domino){
        System.out.println(name + ": " + domino.dominoAsString());
    }
    
    protected void printChosenSide(String name, int side){
        if(side == 0){
            System.out.println(name + ": links anlegen");
        } else {
            System.out.println(name + ": rechts anlegen");
        }
    }
}
