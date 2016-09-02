import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by anbganapathy on 9/2/2016.
 */
public class PrimeNumber {

    private Boolean isPrime=true;
    private Integer nextNum=0;
    private  Boolean isPolynomial=true;


    public void isPrimeNumber(String val) {
        Integer primeVar = Integer.parseInt(val);
        if (primeVar % 2 == 0)
            primeVar = primeVar + 1;

        for (int i=primeVar; isPrime; i=i+2)
        {
            for (int j=3;j<=i/2;j++)
            {
                if (j==(i/2))
                {
                    isPrime= checkPalindrome(i);;
                    nextNum=i;
                }
                if(i%j==0) break;
                else continue;
            }

        }

        System.out.println(nextNum);
    }


    public boolean checkPalindrome(int val)
    {
        int reverse=0;
        int remainder;
        int palindrome=val;
        while (palindrome!=0)
        {
            remainder=palindrome%10;
            reverse=reverse*10 + remainder;
            palindrome=palindrome/10;
        }

        if (reverse==val)
            isPolynomial = false;

        return isPolynomial;
    }


    public static void main(String[] args) {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        PrimeNumber primeNumber=new PrimeNumber();

        try {
            String a=bufferedReader.readLine();
            primeNumber.isPrimeNumber(a);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
