import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DominoPool {
    private static final int MAX_NUMBER_ON_DOMINO = 5;
    public List<Domino> provideShuffledDominoHeap() {
        List<Domino> heapOfDominoes = new LinkedList<>();
        for (int left = 0; left < MAX_NUMBER_ON_DOMINO; left++) {
            for (int right = 0; right < MAX_NUMBER_ON_DOMINO; right++) {
                heapOfDominoes.add(new Domino(left, right));
            }
        }
        Collections.shuffle(heapOfDominoes);
        return heapOfDominoes;
    }
}