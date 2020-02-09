package util;
/**
 *
 * @author uellington
 */
public class Random {
    
    public static int randomize(int min, int max){
        boolean tryAgain = true;
        int random = 0;
        while (tryAgain) {
            random = new java.util.Random().nextInt(max);
            if(random >= min){
                tryAgain = false;
            }
        }
        return random;
    }
}
