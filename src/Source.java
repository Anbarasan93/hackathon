import sun.org.mozilla.javascript.internal.ast.Yield;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by anbganapathy on 8/25/2016.
 */
public class Source {

    public static void main(String[] args) {

        try {

            Scanner in = new Scanner(System.in);
            int a;
            a = in.nextInt();
            String b;
            in.nextLine();
            b = in.nextLine();
            int c;
            c = in.nextInt();
            String a1[] = new String[c];
            in.nextLine();
            for (int i = 0; i < c; i++) {
                a1[i] = in.nextLine();
            }
            BinaryTree bt = new BinaryTree();
            bt.treeCreation(a, b);
            bt.setOpValues(a1);
            bt.functionCaller(a1, a, b, true);
        }
        catch (Exception e)
        {
            System.out.println("Invalid Input");
        }

    }

}


class BinaryTree
{
    HashMap<Integer, ArrayList<Node>>   hash= new HashMap<Integer, ArrayList<Node>>();
    Map<AtomicInteger,Integer> addValues;
    int stateIdentifier=0;
    private  String [] opValues;
    private boolean isFirstReq=true;
    private boolean isAgainReq=true;

    public String[] getOpValues() {
        return opValues;
    }

    public void setOpValues(String[] opValues) {
        this.opValues = opValues;
    }

    public ArrayList<Node> treeCreation(int size, String str)
    {

        ArrayList<Node> ts=new ArrayList<>();

        int max= (int) (Math.pow(2,(size+1)));

        String[] values=str.split(" ");

        for(int i=0;  i < values.length  ; i++)
        {
            Node node=new Node();
            node.setValue(values[i]);

            ts.add(node);
        }

        int k=0;
        for (int i=0; i<size;i++)
        {
            for ( int j=1;j<=Math.pow(2,i);j++)
            {

                    ts.get(k).setLeft(ts.get((2 * k) + 1));
                    ts.get(k).setRight(ts.get((2 * k) + 2));
                    ts.get(k).setParent(ts.get(k));
                    k++;
            }

        }


        hash.put(stateIdentifier,ts);
        stateIdentifier++;

        return ts;

    }




    public void inOrder(Node node)
    {
        if(node!=null)
        {

            inOrder(node.getLeft());
            if(!node.getValue().equalsIgnoreCase("-1"))
            {

                 Integer.parseInt(node.getValue());
                addValues.put(new AtomicInteger(0),Integer.parseInt(node.getValue()));

            }

            inOrder(node.getRight());
        }

    }



    public  void addNode(String[] values, int a, String b)
    {
        ArrayList<Node> ts1;
        if (isAgainReq) {

            ts1 = treeCreation(a, b);
        }
        else
        {

            isAgainReq=true;
            String[] postOps = previousOperation(values);
            functionCaller(postOps, a, b,false);
            ts1 = hash.get(stateIdentifier - 1);
        }

        String x=values[1];
        String y=values[2];
        String z=values[3];
        Node node=new Node();
        node.setValue(z);

        ts1.add(node);

       for(int i=0; i<ts1.size();i++)
       {

          if( ts1.get(i).getValue().equals(x))
          {
              node.setParent(ts1.get(i));
                  if(y.equalsIgnoreCase("L")) {
                  ts1.get(i).setLeft(node);

              }
              else
                  {
                   ts1.get(i).setRight(node);
          }}
       }

           }

    public void functionCaller(String[] a1, int a, String b,boolean isFirstReq)
    {

        int z=0;
            for (int i = 0; i < a1.length; i++) {

                if(a1[i]!=null)
                {
                String[] values = a1[i].split(" ");
                    int j = 0;
                    if(values[j].startsWith("ADD"))
                          z++;
                    if(z>1)
                        isAgainReq=false;

                    try

                    {
                        if (i == 0) {
                            if (values[j].equalsIgnoreCase("ADD"))
                                addNode(values, a, b);
                            if (values[j].equalsIgnoreCase("SUM"))
                                sumNode(values);
                            if (values[j].equalsIgnoreCase("UPDATE"))
                                updateNode(values, a, b, isFirstReq);
                            if (values[j].equalsIgnoreCase("DELETE"))
                                deleteNode(values, a, b, isFirstReq);

                        } else {
                            isFirstReq = false;

                            if (values[j].equalsIgnoreCase("ADD"))
                                addNode(values, a, b);
                            if (values[j].equalsIgnoreCase("SUM"))
                                sumNode(values);
                            if (values[j].equalsIgnoreCase("UPDATE"))
                                updateNode(values, a, b, isFirstReq);
                            if (values[j].equalsIgnoreCase("DELETE"))
                                deleteNode(values, a, b, isFirstReq);

                        }

                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid Input");
                    }

            }

        }
    }



    public void sumNode(String[] values)
    {
      String  state=values[1];
        String value=values[2];
        if(state.equalsIgnoreCase("-1"))
            state= Integer.toString(stateIdentifier-1);

        ArrayList<Node> tempNode = hash.get(Integer.parseInt(state));
        addValues=new HashMap<AtomicInteger,Integer>();
        int finalValue=0;

        for(int i=0 ; i <tempNode.size(); i ++)
        {
            if(tempNode.get(i).getValue().equalsIgnoreCase(value)) {
                      if (i == 0) {
                        inOrder(tempNode.get(i ).getLeft());
                        inOrder(tempNode.get(i).getRight());
                        finalValue=Integer.parseInt(tempNode.get(i).getValue());

                }
                else {
                    if (tempNode.get(i - 1).getLeft().getValue().equalsIgnoreCase(value))
                    {
                        inOrder(tempNode.get(i).getParent());
                    }

                    else{

                        inOrder(tempNode.get(i).getParent());
                    }
                }
            }

        }

        Iterator it=addValues.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair=(Map.Entry) it.next();
            finalValue=finalValue+(Integer) pair.getValue();

        }

        System.out.println(finalValue);
                addValues.clear();

        }



    public String[] previousOperation(String[] a) {


        String[] preOps = getOpValues();
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(preOps));
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).startsWith("SUM")) {
                arrayList.remove(i);
            }

        }
        preOps = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
            preOps[i] = arrayList.get(i);
        StringBuilder builder = new StringBuilder();
        String operation;
        for (String value : a) {
            builder.append(value + " ");
        }
        operation = builder.toString();

        int k = 0;
        for (int i = 0; i < preOps.length; i++) {
            if (preOps[i].trim().equalsIgnoreCase(operation.trim())) {
                k = i;
            }
        }

            String[] postOps = new String[k];

            for (int i = 0, j = 0; i < k; i++) {
                if (preOps[i].startsWith("SUM"))
                    continue;
                else {
                    postOps[j] = preOps[i];

                    j++;
                }
            }


            String retValue[] = new String[1];

            ArrayList<String> arrayList1 = new ArrayList<String>();

            for (String s : postOps) {
                if (s != null && s.length() > 0) {
                    arrayList1.add(s);
                }
            }
            postOps = new String[arrayList1.size()];
            for (int i = 0; i < arrayList1.size(); i++)
                postOps[i] = arrayList1.get(i);

            int length = postOps.length;
            retValue[0] = postOps[length - 1];

            return retValue;


        }



    public void updateNode(String[] a, int b, String c,boolean isFirstReq)
    {
        isAgainReq=true;
        String x=a[1];
        String y=a[2];
        ArrayList<Node>  ts2;

        if(isFirstReq) {
              ts2 = treeCreation(b, c);
        }
        else {
            String[] postOps = previousOperation(a);
            functionCaller(postOps, b, c,false);
            ts2 = hash.get(stateIdentifier - 1);
        }
        for(int i=0; i<ts2.size();i++)
        {
            if(ts2.get(i).getValue().equalsIgnoreCase(x))
                ts2.get(i).setValue(y);
        }



    }

    public void deleteNode(String[] a, int b, String c,boolean isFirstReq)
    {

        isAgainReq=true;
        ArrayList<Node> ts3;
        if(isFirstReq) {
             ts3 = treeCreation(b, c);
        }
        else {
            String[] postOps = previousOperation(a);
            functionCaller(postOps, b, c,false);
            ts3 = hash.get(stateIdentifier - 1);
        }
        String x=a[1];
        for(int i=0; i<ts3.size();i++)
        {
            if(ts3.get(i).getValue().equalsIgnoreCase(x))
                ts3.get(i).setValue("-1");
        }



    }


}


class Node extends ArrayList<Node> {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", left=" + left +
                '}';
    }

    private String value;
    private Node left;
    private Node right;
    private Node parent;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}




