import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RandomizedBST implements TaxEvasionInterface 
{
    private class TreeNode {
        LargeDepositor item;
        TreeNode left; // pointer to left subtree
        TreeNode right; // pointer to right subtree
        int N; //number of nodes in the subtree rooted at this TreeNode
    }
    
    private TreeNode root;

    private LargeDepositor searchR(TreeNode h, int key) 
    {
        if (h == null) return null;
        if (key == h.item.key()) return h.item;
        if (key < h.item.key()) return searchR(h.left, key);
        else return searchR(h.right, key); 
    }

    private void iterateforName(TreeNode node, String last_name, List<LargeDepositor> myList)
    {
        if (node == null)
            return;
        if (node.item.getLastName() == last_name)
        {
            myList.insertAtBack(node.item);
        }
        iterateforName(node.left, last_name, myList);
        iterateforName(node.right, last_name, myList);
    }

    private void recursiveInOrder(TreeNode node) {
        if (node == null)
            return;

        recursiveInOrder(node.left);
        System.out.println(node.item + "\n");
        recursiveInOrder(node.right);
    }

    public void insert(LargeDepositor item)
    {

    }

    public void load(String filename)
    {
        String line, data = "";
        BufferedReader reader;
        LargeDepositor tmpObj;
        int index;
        StringDoubleEndedQueue<String> ObjCharacteristics = new StringDoubleEndedQueueImpl<String>();
        try
        {
            reader = new BufferedReader(new FileReader(filename));
            System.out.println("Reading cities' data from file...");
            line = reader.readLine();
            while (line != null)
            {
                tmpObj = new LargeDepositor();
                while (line != "")  // put each word in each own node
                {
                    index = line.indexOf(" ");
                    if (index >= 0)
                    {
                        data = line.substring(0, index);
                    }
                    else
                    {
                        data = line;
                        index = line.length();
                    }
                    ObjCharacteristics.addLast(data);
                    line = line.substring(index).trim();
                }
                tmpObj.setAFM(Integer.parseInt(ObjCharacteristics.removeFirst()));
                tmpObj.setFirstName(ObjCharacteristics.removeFirst());
                tmpObj.setLastName(ObjCharacteristics.removeFirst());
                tmpObj.setSavings(Double.parseDouble(ObjCharacteristics.removeFirst()));
                tmpObj.setTaxedIncome(Double.parseDouble(ObjCharacteristics.removeFirst()));
                insert(tmpObj); 
                line = reader.readLine();
            }
            System.out.println("All data were read succesfully");
            reader.close();
        }
        catch (IOException e) 
        {
            System.out.println	("Error reading file...\nThe program will now exit");
            System.exit(0);
        }
    }

    public void updateSavings(int AFM, double savings)
    {

    }

    public LargeDepositor searchByAFM(int AFM)
    {
        return searchR(root, AFM);
    }

    //TODO make simple list
    public List<LargeDepositor> searchByLastName(String last_name)
    {
        List<LargeDepositor> myList = new List<LargeDepositor>();
        iterateforName(root, last_name, myList);
        if (myList.size()>0)
        {
            return myList;
        }
        else
        {
            return null;
        }
    }

    public void remove(int AFM)
    {

    }

    public double getMeanSavings()
    {
        return 0;
    }

    public void printTopLargeDepositors(int k)
    {

    }

    public void printByAFM()
    {
        recursiveInOrder(root);
    }

    public static void main(String args[])
    {
        RandomizedBST symbolTable = new RandomizedBST();
        // For testing purposes

        symbolTable.load("Data.txt");
        symbolTable.printByAFM();
        Scanner on = new Scanner(System.in);
        String option;
        while (true){
            printMenu();

            option = on.nextLine();
            if (option.equals("1"))
            {
                LargeDepositor tmp = new LargeDepositor();
                System.out.println("AFM: ");
                tmp.setAFM(Integer.parseInt(on.nextLine()));
                System.out.println("First name: ");
                tmp.setFirstName(on.nextLine());
                System.out.println("Last name: ");
                tmp.setLastName(on.nextLine());
                System.out.println("Savings: ");
                tmp.setSavings(Double.parseDouble(on.nextLine()));
                System.out.println("Taxed income: ");
                tmp.setTaxedIncome(Double.parseDouble(on.nextLine()));

                symbolTable.insert(tmp);
            }
            else if (option.equals("2"))
            {
                System.out.println("File name: ");
                symbolTable.load(on.nextLine());
            }
            else if (option.equals("3"))
            {
                System.out.println("AFM: ");
                int afm = Integer.parseInt(on.nextLine());
                System.out.println("Updated savings: ");
                double savings = Double.parseDouble(on.nextLine());
                symbolTable.updateSavings(afm, savings);
            }
            else if (option.equals("4"))
            {
                System.out.println("AFM: ");
                int afm = Integer.parseInt(on.nextLine());
                LargeDepositor result = symbolTable.searchByAFM(afm);
                if (result!= null)
                {
                    System.out.println("Large Depositor's data: \n" + result);
                }
                else
                {
                    System.out.println("AFM was not found");
                }
            }
            else if (option.equals("5"))
            {
                System.out.println("Last name: ");
                String name = on.nextLine();
                List<LargeDepositor> myList = symbolTable.searchByLastName(name);
                if (myList.size()<=5)
                {
                    System.out.println(myList);
                }
            }
            else if (option.equals("6"))
            {
                System.out.println("AFM: ");
                int afm = Integer.parseInt(on.nextLine());
                symbolTable.remove(afm);
            }
            else if (option.equals("7"))
            {
                System.out.println("Mean savings: " + symbolTable.getMeanSavings());
            }
            else if (option.equals("8"))
            {
                System.out.println("top k Large Depositors: ");
                int k = Integer.parseInt(on.nextLine());
                symbolTable.printTopLargeDepositors(k);
            }
            else if (option.equals("9"))
            {
                symbolTable.printByAFM();
            }
            else if (option.equals("0"))
            {
                System.out.println("Exiting program...");
                break;
            }
            else{
                System.out.println("The option was not recognised from the system, try once more");
            }    
        }

        on.close();
    }

    public static void printMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1. Insert a new Large Depositor");
        System.out.println("2. Load Large Depositor from file");
        System.out.println("3. Update savings of a Large Depositor");
        System.out.println("4. Find Large Depositor by AFM");
        System.out.println("5. Find Large Depositor by Last name");
        System.out.println("6. Remove Large Depositor by AFM");
        System.out.println("7. Get savings' average");
        System.out.println("8. Get top k Large Depositors");
        System.out.println("9. Print Large Depositors in ascending order");
        System.out.println("0. Exit");
    }
}
