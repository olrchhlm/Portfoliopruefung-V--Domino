import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Optional;

/**
 * DominoGame bereitet das Spiel vor und kümmert sich um den Ablauf. 
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */

public class DominoGame{
    private final UserDialog userDialog = new UserDialog();
    private final int playerLimit = 4; 
    private final DominoPool dominoPool = new DominoPool(); 
    private List<Domino> dominoHeapOnTable;
    private Domino dominoOnTable; 
    private final ArrayList<Player> players = new ArrayList<>();

    public DominoGame(){
        dominoHeapOnTable = dominoPool.provideShuffledDominoHeap();
    }

    public Domino getDominoOnTable(){
        return dominoOnTable;
    }

    public void addHumanPlayer(String name){
        if(!reachedPlayerLimit()){
            HumanPlayer humanPlayer = new HumanPlayer(name , getFirstFiveFromHeap());
            players.add(humanPlayer);
        }
    }

    public void addComputerPlayerFirstOption(String name){
        if(!reachedPlayerLimit()){
            ComputerPlayerFirstOption computerPlayerFirstOption = new ComputerPlayerFirstOption(name , getFirstFiveFromHeap());
            players.add(computerPlayerFirstOption);
        }
    }

    public void addComputerPlayerRandomOption(String name){
        if(!reachedPlayerLimit()){
            ComputerPlayerRandomOption computerPlayerRandomOption = new ComputerPlayerRandomOption(name , getFirstFiveFromHeap());
            players.add(computerPlayerRandomOption);
        }
    }

    private Domino pickRandomDominoFromHeap() {
        Domino domino = dominoHeapOnTable.get(0);
        dominoHeapOnTable.remove(0);
        return domino;
    }

    private boolean reachedPlayerLimit(){
        boolean reachedPlayerLimit = false; 
        if(players.size() >= playerLimit){
            reachedPlayerLimit = true; 
            System.out.println("Die maximale Spieleranzahl für 25 Dominosteine wurde erreicht.");
        }
        return reachedPlayerLimit; 
    }

    public void startGame(){
        addDominoOnTableFromHeap();
        
        playRounds();
        
        System.out.println("Spielende");
        printPlayersWithDominosOnHand();
        giveScoreToPlayers();

        System.out.println(createIntermediateResult());

        askForAnotherRound();
    }
    
    private void playRounds(){
        boolean gameOver = false;
        for(int i = 0; !gameOver; ++i){
            Player player = players.get(i % players.size());
            System.out.println("Anlegemöglichkeiten: " + dominoOnTable.dominoAsString());
            Optional<Domino> domino = player.makeMove(dominoOnTable);
            if (domino.isPresent()) {
                dominoOnTable = domino.get(); 
                if (player.getDominosHoldingOnHand().isEmpty()) {
                    gameOver = true;
                }
            } else {
                if(dominoHeapOnTable.isEmpty() && !hasPlayerWithPossibleChoice()){
                    gameOver = true;
                } else if(!dominoHeapOnTable.isEmpty()){ 
                    player.addToDominosHoldingOnHand(pickRandomDominoFromHeap());
                }
            }
        }
    }

    public boolean hasPlayerWithPossibleChoice(){
        boolean playerWithPossibleChoice = false;
        for(Player player : players){
            if(!player.createPossibleChoices(dominoOnTable).isEmpty()){
                playerWithPossibleChoice = true;
            }
        }
        return playerWithPossibleChoice; 
    }

    private void playAnotherRound(){
        dominoHeapOnTable = dominoPool.provideShuffledDominoHeap();
        for(Player player : players){
            player.setDominosHoldingOnHand(getFirstFiveFromHeap());
        }
        startGame();
    }

    private void askForAnotherRound(){
        if(userDialog.getUserInput("Weitere Runde? ", "Nein", "Ja") == 0){
            System.out.println("Tschüß");
        } else {
            playAnotherRound();
        }
    }

    private void addDominoOnTableFromHeap(){
        dominoOnTable = dominoHeapOnTable.get(1);
        dominoHeapOnTable.remove(1);
    }

    private ArrayList<Domino> getFirstFiveFromHeap(){
        ArrayList<Domino> returnList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            returnList.add(dominoHeapOnTable.get(i));
            dominoHeapOnTable.remove(i);
        }

        return returnList;
    }

    private void printPlayersWithDominosOnHand(){
        for(Player player : players){
            System.out.println(player.getName() + ": " + player.printdominosHoldingOnHand());
        }
    }

    private String createIntermediateResult(){
        String playerNames = ""; 
        String playerScores = ""; 

        for(int i = 0; i < players.size() - 1; i++){
            playerNames += players.get(i).getName() + "-";
            playerScores = playerScores + players.get(i).getCurrentScore() + ":";
        }
        playerNames += players.get(players.size() - 1).getName(); 
        playerScores = playerScores + players.get(players.size() - 1).getCurrentScore();
        return playerNames + " " + playerScores; 
    }

    public void giveScoreToPlayers(){
        for(Player player : players){
            int totalValue = 0; 
            for(Domino domino : player.getDominosHoldingOnHand()){
                totalValue += domino.getLeft();
                totalValue += domino.getRight();
            }
            player.addScore(totalValue);
        }
    }
}
