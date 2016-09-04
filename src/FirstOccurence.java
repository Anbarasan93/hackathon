import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by anbganapathy on 9/3/2016.
 */
public class FirstOccurence {

    public Boolean isFirstOccurence=true;
    public char result='0';

    public void getFisrtOccurence(String val)
    {
      for (int i=0;i<val.length()&&isFirstOccurence;i++)
      {
          for (int j=0; j<val.length();j++)
          {
              if(j==val.length()-1)
              {
                  if(val.charAt(i)==val.charAt(j))
                      break;
                  else {
                      isFirstOccurence = false;
                      result = val.charAt(i);
                  }
              }
              if(val.charAt(i)==val.charAt(j)) {
                  if (i == j) continue;
                  else break;
              }
              else {
                  continue;
              }

          }
      }

      if(result=='0') System.out.println("No Repeating Character");
        else System.out.println(result);
    }

    public static void main(String[] args)  {
        FirstOccurence firstOccurence=new FirstOccurence();
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        try {
            String val=bufferedReader.readLine();
            firstOccurence.getFisrtOccurence(val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
