import java.util.*;

/**
 * Rebecca Gold
 *
 * From Project Euler:
 * Problem 35: Circular Primes
 *
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 * How many circular primes are there below one million?
 *
 * This problem was my challenge problem. I started working on it because the name sort of stuck on the list when I was
 * first looking through problems and when I read the problem description it seemed interesting. I started working on it
 * brute force and got that solution done out in around 20-30 minutes. It worked pretty efficiently for circular primes
 * up to 10,000 but struggled with times over a minute on 100,000 and over five on 1,000,000. I decided there had to be
 * a smarter solution. I started looking around for optimization solutions on the internet. I found
 * http://www.javaprogrammingforums.com/algorithms-recursion/16939-circular-prime-checking-algorithm-optimization.html
 * which gave me two suggestions that I then went to implement. The first was stopping my programming from bothering
 * to check any number that would have 2, 4, 6, 8, 5 or 0 as one of the digits. Because we were looking for circular
 * numbers, any number that contained those digits would automatically not be prime. That cut my time down on the
 * 100,000 reasonable but I was still getting crazy runtimes for checking 1,000,000. I then focused on adding an
 * optimization to only check numbers that began with the a digit lower than all the other ones. This cut the circular
 * calls down dramatically and made the problem work out nicely.
 *
 * Time Taken: 2 hours.
 */
public class CircularPrimes {

    public static void main(String[] args){
        int high = 1000000; //For testing purposes, I reset the high value to make sure I was getting reasonable answers.

        long start = System.currentTimeMillis();
        int primes = circularPrimes(high);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("There are " + primes + " circular primes below " + high + ". It took " + time +
                "millis to calculate this.");
    }

    public static boolean isPrime(int num){
        for(int i=2; i<num; i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param nums A group of numbers that you want to verify all are prime
     * @return If the numbers are all prime or not.
     */
    public static boolean arePrime(HashSet<Integer> nums){
        for(int num:nums){
            if(!isPrime(num)){
                return false;
            }
        }
        return true;
    }

    /**
     * A circular counterpart is a rotation of digits. IE, the circular counterparts of 197 are {197, 971, 719}. I use
     * a HashSet here because I want to count UNIQUE circular counterparts. IE, the circular counterparts of 11 is {11}.
     *
     * @param num A number that you want to get the circular counterparts of.
     *  @return A set with all unique circular counterpart.
     */
    public static HashSet<Integer> circularCounterparts(int num){
        int digits = String.valueOf(num).length();
        int[] circulars = new int[digits];
        circulars[0] = num;
        for (int i=1; i<digits; i++){
            String number = String.valueOf(circulars[i-1]);
            char firstDigit = number.charAt(0);
            String withoutFirstDigit = number.substring(1);
            number = withoutFirstDigit + firstDigit;
            circulars[i] = new Integer(number);
        }

        HashSet<Integer> cleanedCirculars = new HashSet<Integer>();
        for(int circ:circulars){
            cleanedCirculars.add(circ);
        }

        return cleanedCirculars;
    }

    /**
     * This function first checks if a number could not be a potential circular prime (meaning none of the digits are
     * 2, 4, 6, 8, 5, or 0). Then it verifies that the circular counterparts of that number are all prime.
     *
     * @param num A number that you want to verify if it is a circular prime.
     * @return If this number is a circular prime.
     */
    public static boolean isCircularPrime(int num){
        char[] numbers = String.valueOf(num).toCharArray();
        if(Arrays.asList(numbers).contains('2')
          ||Arrays.asList(numbers).contains('4')
          ||Arrays.asList(numbers).contains('6')
          ||Arrays.asList(numbers).contains('8')
          ||Arrays.asList(numbers).contains('5')
          ||Arrays.asList(numbers).contains('0')){
            return false;
        }
        else{
            if(arePrime(circularCounterparts(num))){
            //    System.out.println(circularCounterparts(num)); Testing purposes
                return true;
            }
            return false;
        }
    }

    /**
     * This function checks all the numbers where the first digit is the smallest digit (because numbers where this is
     * not the case will be counted as circular counterparts) and counts how many circular primes there are below a
     * a given high value.
     *
     * @param high The number you want to check the circular primes below.
     * @return The number of circular primes below a number.
     */
    public static int circularPrimes(int high){
        int counter = 0;
        for(int i=2; i<high; i++){           //Primes start at 2
            if(firstDigitIsSmallest(i) && isCircularPrime(i)){ //Because of boolean logic, this will only check if the
                                                               //number is a circular prime if the first condition passes
                counter+=circularCounterparts(i).size();
            }
        }
        return counter;
    }

    /**
     * This function checks to see if the first digit of a number is the smallest digit. This is useful in limiting the
     * number of circular primes checked.
     *
     * @param num The number you are checking.
     * @return if the first digit is the smallest digit.
     */
    public static boolean firstDigitIsSmallest(int num){
        if(num<10){
            return true;
        }
        char[] numAsChars = new Integer(num).toString().toCharArray();
        for(int i=1; i<numAsChars.length; i++){
            if(numAsChars[i]<numAsChars[0]){
                return false;
            }
        }
        return true;
    }
}
