import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RandomizedBST implements TaxEvasionInterface
{
	private class TreeNode
	{
		LargeDepositor item;
		TreeNode left; // pointer to left subtree
		TreeNode right; // pointer to right subtree
		int N; // number of nodes in the subtree rooted at this TreeNode

		public TreeNode(LargeDepositor item)
		{
			this.item = item;
			this.N = 1;
		}
	}

	private TreeNode root;

    private void iterateforName(TreeNode node, String last_name, List<LargeDepositor> myList)
    {
        if (node == null)
            return;
        if (node.item.getLastName().equals(last_name))
        {
            myList.insertAtBack(node.item);
        }
        iterateforName(node.left, last_name, myList);
        iterateforName(node.right, last_name, myList);
    }

	private LargeDepositor searchR(TreeNode h, int key)
	{
		if (h == null)
			return null;
		if (key == h.item.key())
			return h.item;
		if (key < h.item.key())
			return searchR(h.left, key);
		else
			return searchR(h.right, key);
	}


	private void recursiveInOrder(TreeNode node)
	{
		if (node == null)
			return;

		recursiveInOrder(node.left);
		System.out.println("\n" + node.item);
		recursiveInOrder(node.right);
	}

	private int count(TreeNode h)
	{
		if (h == null)
			return 0;

		return h.N;
	}

	public void insert(LargeDepositor item)
	{
		root = insertR(item, root);
	}

	private TreeNode insertAsRoot(LargeDepositor item, TreeNode h)
	{
		if (h == null)
		{
			h = new TreeNode(item);
			return h;
		}

		if (item.key() == h.item.key())
		{
			System.out.println("Depositor already exists.");
			return h;
		} 
		else if (item.key() < h.item.key())
		{
			// insert into the left subtree and perform a right rotation
			h.left = insertAsRoot(item, h.left);
			h = rotateRight(h);
		}

		else
		{
			// insert into the right subtree and perform a left rotation
			h.right = insertAsRoot(item, h.right);
			h = rotateLeft(h);
		}

		h.N++;
		return h;
	}

	private TreeNode insertR(LargeDepositor item, TreeNode h)
	{
		// if we reach a leaf's null child, create a new node
		if (h == null)
		{
			h = new TreeNode(item);
			return h;
		}

		if (Math.random() * (h.N + 1) < 1.0)
		{
			return insertAsRoot(item, h);
		}

		/// search the correct position to put the new node until reaching bottom
		if (item.key() == h.item.key())
		{
			System.out.println("Depositor already exists.");
			return h;
		} 
		else if (item.key() < h.item.key())
		{
			h.left = insertR(item, h.left);
		} 
		else
		{
			h.right = insertR(item, h.right);
		}

		h.N++;
		return h;
	}

//see rotations in pdf DSslides13, slide 13-25 to understand logic of calculating N
	private TreeNode rotateRight(TreeNode h)
	{
		// Right rotation operation
		TreeNode x = h.left;
		x.N = h.N;
		h.left = x.right;
		h.N = count(h.right) + count(x.right) + 1;
		x.right = h;

		return x;
	}

	private TreeNode rotateLeft(TreeNode h)
	{
		// Left rotation operation
		TreeNode x = h.right;
		x.N = h.N;
		h.right = x.left;
		h.N = count(h.left) + count(x.left) + 1;
		x.left = h;

		return x;
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
			System.out.println("Reading depositors data from file...");
			line = reader.readLine();
			while (line != null)
			{
				tmpObj = new LargeDepositor();
				while (line != "") // put each word in each own node
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
			System.out.println("Error reading file...");
		}
	}

	public void updateSavings(int AFM, double savings)
	{
		LargeDepositor depositor = searchByAFM(AFM);
		if (depositor != null)
			depositor.setSavings(savings);
	}

	public LargeDepositor searchByAFM(int AFM)
	{
		LargeDepositor searchResult = searchR(root, AFM);
		if(searchResult==null)
			System.out.println("Depositor not found");
		return searchResult;
	}

	public List<LargeDepositor> searchByLastName(String last_name)
	{
		List<LargeDepositor> myList = new List<LargeDepositor>();
		iterateforName(root, last_name, myList);
		if (myList.size() > 0)
		{
			return myList;
		}
		else
		{
			return null;
		}
	}

	// implementation of idea of page 589 in main book
	public void remove(int AFM)
	{
		LargeDepositor searchResult = searchByAFM(AFM);
		if (searchResult != null)
			root = removeR(root, AFM);		
	}

	private TreeNode removeR(TreeNode h, int v)
	{
		if (h == null)
			return null;
		int w = h.item.key();

		// find value 'v' to delete
		if (v < w)
		{
			h.left = removeR(h.left, v);
			h.N--;// only to reduce size in removeR, not when calling join
		}

		else if (w < v)
		{
			h.right = removeR(h.right, v);
			h.N--;// only to reduce size in removeR
		}

		/*
		 * if found, join it's childrent and 'delete' it by replacing it with the result
		 * of the join
		 */
		else
			h = joinLR(h.left, h.right);

		return h;
	}

	private TreeNode joinLR(TreeNode a, TreeNode b)
	{
		if (a == null)
			return b;
		if (b == null)
			return a;

		int N = a.N + b.N;
		// make right child of a, the b nodeTree
		if (Math.random() * N < 1.0 * a.N)
		{
			a.right = joinLR(a.right, b);
			// fix in each recursion the new treeNode size
			a.N = count(a.right)+count(a.left)+1;
			return a;
		}
		// make left child of b, the a nodeTree
		else
		{
			b.left = joinLR(a, b.left);
			b.N = count(b.right)+count(b.left)+1;
			return b;
		}
	}

	public double getMeanSavings()
	{
		if(root==null) return 0;
		return addSavingsR(root) / root.N;
	}

	private double addSavingsR(TreeNode h)
	{
		if (h == null)
			return 0;
		double sum = addSavingsR(h.left);
		sum += h.item.getSavings();
		sum += addSavingsR(h.right);
		return sum;
	}
		
	public void printTopLargeDepositors(int k)
	{
		PQ topLargeDepositorsPQ = new PQ(k, PQ.Type.MIN);
		createMinHeapR(root, topLargeDepositorsPQ, k);
	
		for (int i = 0; i < k; i++)
		{
			LargeDepositor pqElement= topLargeDepositorsPQ.getHead();	
			System.out.println("\n" + i + ": " + pqElement);
			System.out.println("------------------------------");			
		}
	}
	
	private void createMinHeapR(TreeNode node,  PQ pq, int k)
	{
		if (node == null)
			return;
	
		pq.insert(node.item);
		//removes min element
		if(pq.size()>k)
		{
			pq.getHead();
		}
	
		createMinHeapR(node.left, pq, k);
		createMinHeapR(node.right, pq, k);
	}
	
	public void printByAFM()
	{
		recursiveInOrder(root);
	}
	
	public static void main(String args[]) throws Exception
	{
		RandomizedBST symbolTable = new RandomizedBST();
		Scanner on = new Scanner(System.in);
		String option;
		while (true)
		{
			printMenu();
			System.out.print("Option: ");
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
            }
            else if (option.equals("5"))
            {
                System.out.println("Last name: ");
                String name = on.nextLine();
                List<LargeDepositor> myList = symbolTable.searchByLastName(name);
				if (myList!=null)
				{
					System.out.println("Large Depositor with specified last name were succesfully found!\n");
					if (myList.size()<=5)
					{
						System.out.println(myList);
					}
				}
                else
				{
					System.out.println("Last name was not found");
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
                System.out.print("top k Large Depositors: ");
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
            else
			{
                System.out.println("The option was not recognised from the system, try once more");
            }    
        }

		on.close();
	}

    public static void printMenu()
    {
        System.out.println("\nWhat would you like to do?");
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
