import java.util.ArrayList;
import java.util.Optional;

/**
 * Die Klasse Player ist Super-Klasse aller anderen Spieler.
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public abstract class Player
{
    private ArrayList<Domino> dominosHoldingOnHand = new ArrayList<>();
    private final String name;
    private int currentScore = 0; 

    public Player(String name, ArrayList<Domino> dominosHoldingOnHand){
        this.name = name; 
        this.dominosHoldingOnHand = dominosHoldingOnHand; 
    }

    public void setDominosHoldingOnHand(ArrayList<Domino> dominosHoldingOnHand){
        this.dominosHoldingOnHand = dominosHoldingOnHand; 
    }

    public void addToDominosHoldingOnHand(Domino domino) {
        dominosHoldingOnHand.add(domino);
    }

    public ArrayList<Domino> getDominosHoldingOnHand(){
        return dominosHoldingOnHand; 
    }

    public String getName(){
        return name;
    }

    public void addScore(int addedValue){
        currentScore += addedValue; 
    }

    public int getCurrentScore(){
        return currentScore; 
    }
    
    public Domino putDominoOnTable(Domino dominoOnTable, Domino dominoFromPlayer) {
        dominosHoldingOnHand.remove(dominoFromPlayer);        
        return mergeDominos(dominoOnTable, dominoFromPlayer); 
    }

    public ArrayList<Domino> createPossibleChoices(Domino inputDomino){ 
        ArrayList<Domino> possibleChoices = new ArrayList<>();
        for(Domino domino: dominosHoldingOnHand){
            if(domino.getLeft() == inputDomino.getRight() || domino.getRight() == inputDomino.getLeft()){
                possibleChoices.add(domino);
            }
        }
        return possibleChoices;
    }

    public String printdominosHoldingOnHand(){
        String dominoList = "[";
        for(int i = 0; i < (dominosHoldingOnHand.size() - 1); i++){
            dominoList += dominosHoldingOnHand.get(i).dominoAsString() + ", ";
        }
        if(!dominosHoldingOnHand.isEmpty()){
            dominoList += dominosHoldingOnHand.get(dominosHoldingOnHand.size() - 1).dominoAsString();
        }
        return dominoList + "]"; 
    }

    private Domino mergeDominos(Domino dominoOnTable, Domino dominoFromPlayer){
        Domino mergedDomino = new Domino(0, 0); 
        if (dominoOnTable.getRight() == dominoFromPlayer.getLeft() && dominoFromPlayer.getRight() == dominoOnTable.getLeft()) {
            int selectedSide = selectSide();
            if(selectedSide == 0){
                mergedDomino = mergeLeft(dominoOnTable, dominoFromPlayer);
            } else{
                mergedDomino = mergeRight(dominoOnTable, dominoFromPlayer);
            }
        } else if (dominoFromPlayer.getRight() == dominoOnTable.getLeft()){
            mergedDomino = mergeLeft(dominoOnTable, dominoFromPlayer); 
        } else if (dominoOnTable.getRight() == dominoFromPlayer.getLeft()){
            mergedDomino = mergeRight(dominoOnTable, dominoFromPlayer);
        }
        return mergedDomino;
    }

    private Domino mergeRight(Domino dominoOnTable, Domino dominoFromPlayer){
        Domino mergedDomino = new Domino(dominoOnTable.getLeft(), dominoFromPlayer.getRight()); 
        return mergedDomino; 
    } 

    private Domino mergeLeft(Domino dominoOnTable, Domino dominoFromPlayer){
        Domino mergedDomino = new Domino(dominoFromPlayer.getLeft(), dominoOnTable.getRight()); 
        return mergedDomino;
    }

    protected abstract int selectSide();

    protected abstract Optional<Domino> makeMove(Domino dominoOnTable);
}
