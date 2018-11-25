
/**
 * Dominostein mit zwei Werten (links & rechts)
 * 
 * @author Ole Reichhelm
 * @version 1.0
 */
public class Domino{
    private int left; 
    private int right; 
    
    public Domino(int left, int right)
    {
        this.left = left;
        this.right = right;
    }
    
    public int getLeft(){
        return left;
    }
    
    public void setLeft(int value){
        left = value; 
    }
    
    public int getRight(){
        return right;
    }
    
    public void setRight(int value){
        right = value; 
    }
    
    public String dominoAsString(){
        return "[" + left + "|" + right + "]";
    }
}
