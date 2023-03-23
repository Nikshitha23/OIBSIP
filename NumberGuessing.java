package numberguessing;

import java.util.Scanner;
 
public class NumberGuessing {
    public static void NumberGuessingGame()
    {
        Scanner sc = new Scanner(System.in);
        int number = 1 + (int)(100* Math.random());
        int n = 5;
        int i, guess;
        System.out.println("Guess number between 1 to 100." + "You get 5 chances to guess the correct number");
        for (i = 0; i < n; i++) 
        {
 
            System.out.println("Guess the number:");
            guess = sc.nextInt();
            if (number == guess) {
                System.out.println( "Congratulations!"+ " You guessed the number.You Win!!!");
                   break;
                           
                         
            }
            else if (number > guess&& i != n - 1)
            {
                System.out.println( "The number is " + "greater than " + guess);
            }
                   
                   else if (number < guess  && i != n - 1)
                    {
                System.out.println("The number is"+ " less than " + guess);
                    
            }
        }
 
        if (i == n) {
            System.out.println("You have exhausted" + " number of trials.You Lost!!!");
                
            System.out.println("The number was " + number);
        }
    }
  public static void main(String arg[])
    {
        NumberGuessingGame();
    }
}