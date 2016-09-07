/**
 * Created by anbganapathy on 9/7/2016.
 */
// Java 7 program to read an integer from STDIN and output it to STDOUT
import java.util.*;

// Class name should be "BeautyArray",
// otherwise solution won't be accepted
public class BeautyArray {

    public void getOutput(int[] arrayValues,String coOrds)
    {
        String coOrdVal[]=coOrds.split(" ");
        int start=Integer.parseInt(coOrdVal[0]);
        int end=Integer.parseInt(coOrdVal[1]);
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        for(int i=start-1; i<end;i++)
            arrayList.add(arrayValues[i]);
        Map<Integer,Integer> map =new HashMap<Integer,Integer>();
        for(Integer temp : arrayList)
        {
            Integer count=map.get(temp);
            map.put(temp, (count==null) ? 1 : count+1);
        }
        Integer sum=0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet())
            sum = sum + entry.getKey() * entry.getValue() * entry.getValue();

        System.out.println(sum);

    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        BeautyArray source = new BeautyArray();

        // Declare the variable
        String a;

        // Read the variable from STDIN
        a =in.nextLine();
        String values[]=a.split(" ");

        int arraySize=Integer.parseInt(values[0]);
        int queriesize=Integer.parseInt(values[1]);
        String b = in.nextLine();
        String arrayValue[]=b.split(" ");
        int arrayValues[]=new int[arraySize];
        for(int i=0; i<arraySize;i++)
            arrayValues[i]=Integer.parseInt(arrayValue[i]);
        String queries[]=new String[queriesize];
        for(int i=0; i<queriesize; i++)
            queries[i]=in.nextLine();
        for(int i=0; i <queriesize;i++)
            source.getOutput(arrayValues,queries[i]);
    }
}
