import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by anbganapathy on 9/2/2016.
 */
public class StringReverse {

public void reverseOfTheString(String val)
{
   ArrayList<Integer> location=new ArrayList<Integer>();
    char[] revesre=new char[val.length()];
    for (int i=0; i<val.length();i++)
    {
        revesre[i]=val.charAt(i);
        if(val.charAt(i)=='a' || val.charAt(i)=='e' || val.charAt(i)=='i' || val.charAt(i)=='o' || val.charAt(i)=='u')
            location.add(i);
    }

    for (int i=0;i<location.size()/2;i++) {

        char temp=revesre[location.get(i)];
        revesre[location.get(i)]=revesre[location.get(location.size()-1)-i];
        revesre[location.get(location.size()-1)-i]=temp;
    }

    for (int i=0;i<revesre.length;i++)
        System.out.print(revesre[i]);

}


    public static void main(String[] args) {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        StringReverse stringReverse=new StringReverse();
        try {
            String val=bufferedReader.readLine();
            stringReverse.reverseOfTheString(val);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
