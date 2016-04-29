/*
 *  Java Program to Implement Binary Search Tree
 */
 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
 /* Class BSTNode */
 class BSTNode
 {
     BSTNode left, right;
     int data;
 
     /* Constructor */
     public BSTNode()
     {
         left = null;
         right = null;
         data = 0;
     }
     /* Constructor */
     public BSTNode(int n)
     {
         left = null;
         right = null;
         data = n;
     }
     /* Function to set left node */
     public void setLeft(BSTNode n)
     {
         left = n;
     }
     /* Function to set right node */ 
     public void setRight(BSTNode n)
     {
         right = n;
     }
     /* Function to get left node */
     public BSTNode getLeft()
     {
         return left;
     }
     /* Function to get right node */
     public BSTNode getRight()
     {
         return right;
     }
     /* Function to set data to node */
     public void setData(int d)
     {
         data = d;
     }
     /* Function to get data from node */
     public int getData()
     {
         return data;
     }     
 }
 
 /* Class BST */
 class BST
 {
     private BSTNode root;
 
     /* Constructor */
     public BST()
     {
         root = null;
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
     /* Functions to insert data */
     public void insert(int data)
     {
         root = insert(root, data);
     }
     /* Function to insert data recursively */
     private BSTNode insert(BSTNode node, int data)
     {
         if (node == null)
             node = new BSTNode(data);
         else
         {
             if (data <= node.getData())
                 node.left = insert(node.left, data);
             else
                 node.right = insert(node.right, data);
         }
         return node;
     }
     /* Functions to delete data */
         
    //delete trail 2 starts
    void deleteTreeNode(int data){
          if (isEmpty())
             System.out.println("Tree Empty");
         else if (search(data) == false)
             System.out.println("Sorry "+ data +" is not present");
         else
         {
             root = deleteTreeNode(root ,data);
             System.out.println(data+ " deleted from the tree");
         }
    }

    private BSTNode deleteTreeNode(BSTNode root, int data) {
        
        
        BSTNode cur = root;
        if(cur == null){
            return cur;
        }
        if(cur.data > data){            
            cur.left = deleteTreeNode(cur.left, data);
        }else if(cur.data < data){
            cur.right = deleteTreeNode(cur.right, data);
        }else{
            
                      
            if(cur.left == null && cur.right == null){
                cur = null;
            }else if(cur.right == null){
                
                cur = cur.left;
            }else if(cur.left == null){
                cur = cur.right;
            }else{
                
                
                BSTNode temp  = findMinFromRight(cur.right);
               
                cur.data = temp.data;
                
                cur.right = deleteTreeNode(cur.right, temp.data);
            }
        }

        return cur;
    }

    private BSTNode findMinFromRight(BSTNode node) {
        while(node.left != null){
            node = node.left;
        }
        return node;
    }
    
    
     /* Functions to search for an element */
     public boolean search(int val)
     {
         return search(root, val);
     }
     /* Function to search for an element recursively */
     private boolean search(BSTNode r, int val)
     {
         boolean found = false;
         while ((r != null) && !found)
         {
             int rval = r.getData();
             if (val < rval)
                 r = r.getLeft();
             else if (val > rval)
                 r = r.getRight();
             else
             {
                 found = true;
                 break;
             }
             found = search(r, val);
         }
         
         
         return found;
     }
     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(BSTNode r)
     {
         if (r != null)
         {
             inorder(r.getLeft());
             System.out.print(r.getData() +" ");
             inorder(r.getRight());
         }
     }
     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(BSTNode r)
     {
         if (r != null)
         {
             System.out.print(r.getData() +" ");
             preorder(r.getLeft());             
             preorder(r.getRight());
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(BSTNode r)
     {
         if (r != null)
         {
             postorder(r.getLeft());             
             postorder(r.getRight());
             System.out.print(r.getData() +" ");
         }
     }
     
    public void displayTree()
	{
       BTreePrinter objPrinter = new BTreePrinter();
       
	   System.out.println("****......................................................****\n");
       objPrinter.printNode(root);
	   System.out.println("****......................................................****\n");
	}     
 }
 
 /* Class BinarySearchTree */
 public class BinarySearchTree
 {
     public static void main(String[] args)
    {                 
        Scanner scan = new Scanner(System.in);
        /* Creating object of BST */
        BST bst = new BST(); 
        System.out.println("Binary Search Tree Test\n");          
        char ch;
        /*  Perform tree operations  */
        do    
        {
            System.out.println("\nBinary Search Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. EXIT");

 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                
                
                
                     int num, i;    
                    /* Accept number of elements */
                    System.out.println("Enter number of integer elements you are inserting");
                    num = scan.nextInt();    
                    /* Make array of n elements */
                    int arr[] = new int[ num ];
                    /* Accept elements */
                    System.out.println("\nEnter "+ num +" integer elements");
                    for (i = 0; i < num; i++)
                        bst.insert( scan.nextInt() );
                 
                break;                          
            case 2 : 
                System.out.println("Enter integer element to delete");
                bst.deleteTreeNode( scan.nextInt() );                     
                break;                         
            case 3 : 
                System.out.println("Enter integer element to search");
                boolean Srchresult = bst.search( scan.nextInt() );
                String rsltTxt = "Key Not Found";
                if(Srchresult)
                {
                    rsltTxt = "Key Found";
                }
                
                System.out.println("Search result : "+rsltTxt);
                break;                                              
            case 4 :  
                System.out.println("Program Exiting");
                System.exit(0);
                break;            
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            /*  Display tree  */
            System.out.println("\nDisplaying the tree");
		    bst.displayTree();
             
            System.out.print("\nPost order : ");
            bst.postorder();
            System.out.print("\nPre order : ");
            bst.preorder();
            System.out.print("\nIn order : ");
            bst.inorder();
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
    }
 }
 

class BTreePrinter {
    
    
	public BTreePrinter()
	{
        
    }
    

    public  void printNode(BSTNode root) {
        int maxLevel = maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private  void printNodeInternal(List<BSTNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

       printWhitespaces(firstSpaces);

        List<BSTNode> newNodes = new ArrayList<BSTNode>();
        for (BSTNode node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhitespaces(1);

                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private  void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int maxLevel(BSTNode node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private boolean isAllElementsNull(List<BSTNode> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}


