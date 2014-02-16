/**
 * Rebecca Gold
 *
 * From Project Euler:
 * Problem 8: Largest Product in A Series
 *
 *  Find the greatest product of five consecutive digits in the 1000-digit
 *  number.
 *  <See BIG_NUMBER constant below>
 *
 *  After going though (coding and thinking through) most of the problems with lower numbers than this one, I decided to
 *  give this one a try in code. My first instinct was to try brute force so I implemented the algorithm below. The time
 *  was quite good on this one, but I still snooped around on the internet for a bit and couldn't find any real
 *  optimizations so I have left my solution as-is.
 *
 * Time taken: 30 minutes
 */
public class LargestProductInASeries {

    //I chose the represent this number as a String because it would not fit as an int or a long and it would be easy
    //to manipulate in chunks of 5 as a String.
    public static final String BIG_NUMBER = "7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869478851843858615607891129494954595017379583319528532088055111254069874715852386305071569329096329522744304355766896648950445244523161731856403098711121722383113622298934233803081353362766142828064444866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901699720888093776657273330010533678812202354218097512545405947522435258490771167055601360483958644670632441572215539753697817977846174064955149290862569321978468622482839722413756570560574902614079729686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465674813919123162824586178664583591245665294765456828489128831426076900422421902267105562632111110937054421750694165896040807198403850962455444362981230987879927244284909188845801561660979191338754992005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252483600823257530420752963450";

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        int product = largestProductOf5ConsecutiveNumbers(BIG_NUMBER);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("The largest product of 5 consecutive numbers in the given 1000-digit number is " + product +
                ". It took " + time + "millis to calculate this.");

    }


    /**
     * This is a pretty simple variation on a max number function, checking each group of 5 numbers in the input String.
     *
     * @param bigNumber The large number you are working on.
     * @return The largest product of 5 consecutive number.
     */
    private static int largestProductOf5ConsecutiveNumbers(String bigNumber) {
        int largestProduct = 1;
        for(int i = 0; i<bigNumber.length()-4; i++){
            int currProduct = 1;
            for(int j=0; j<5; j++){
                currProduct*=Character.getNumericValue(bigNumber.charAt(i+j));
            }
            if(currProduct>largestProduct){
                largestProduct=currProduct;
            }
        }
        return largestProduct;
    }
}
