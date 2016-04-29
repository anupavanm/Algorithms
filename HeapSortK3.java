/*
 * Java Program to Implement Heap Sort
 */
 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
/* Class HeapSort */
public class HeapSortK3
{    
    private static int N;
    
    
    
    /* Sort Function */
    public static void sort(int arr[])
    {    
        BTreePrinter objDisplay = new BTreePrinter();   
        
        System.out.println("INITIAL TREE \n");
        objDisplay.displayTree(arr);
        
        heapify(arr);        
        for (int i = N; i > 0; i--)
        {
            System.out.println("****--------------------------------*****");
            
            System.out.println("SWAPPING 0th Node with "+i+"th \n");
            swap(arr,0, i);
            
            
           //for display purpose before maxheap          
           int p = 0;
           int disarr2[] = new int[N - p];
           
           for (p = 0; p < N ; p++)
           {
             disarr2[p] = arr[p];
             
             System.out.print(arr[p]+" ");  
           }
           System.out.println("\n");
           
           objDisplay.displayTree(disarr2);
            
            
            
            
            N = N-1;
            System.out.println("MAXHEAPIFY(A,0) to find next MAX\n");
            
           maxheap(arr, 0);
           
           
           //for display purpose after maxheap          
           int k = 0;
           int disarr[] = new int[N + 1 - k];
           int count = 0; 
           
           for (k = 0; k < N + 1; k++)
           {
             disarr[k] = arr[k];
             
             System.out.print(arr[k]+" ");  
           }
           System.out.println("\n");
           
           objDisplay.displayTree(disarr);
           
           System.out.println("****--------------------------------*****");
        }
    }     
    /* Function to build a heap */   
    public static void heapify(int arr[])
    {
        BTreePrinter objDisplay = new BTreePrinter();
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
        {
            System.out.println("BUILDING INITIAL HEAP : MAXHEAPIFY(A,"+i+") \n");
            
            maxheap(arr, i); 
            
            objDisplay.displayTree(arr);
             
           for (int k = 0; k <= N; k++)
           {
            System.out.print(arr[k]+" ");  
           
           }
           
           System.out.println("\n");   
        }      
    }
    /* Function to swap largest element in heap */        
    public static void maxheap(int arr[], int i)
    { 
        
        int left = 2*i + 1 ;
        int right = 2*i + 2;
        int max = i;
        if (left <= N && arr[left] < arr[i])
            max = left;
        if (right <= N && arr[right] < arr[max])        
            max = right;
 
        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    }    
    /* Main method */
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner( System.in );        
        System.out.println("Heap Sort\n");
        int n, i;    
        /* Accept number of elements */
        System.out.println("Enter number of integer elements");
        n = scan.nextInt();    
        /* Make array of n elements */
        int arr[] = new int[ n ];
        /* Accept elements */
        System.out.println("\nEnter "+ n +" integer elements");
        for (i = 0; i < n; i++)
            arr[i] = scan.nextInt();
        /* Call method sort */
        sort(arr);
        /* Print sorted Array */
        System.out.println("\nElements after sorting ");        
        for (i = n-1 ; i >= 0; i--)
            System.out.print(arr[i]+" ");            
        System.out.println();            
    }    
}

class Node {
    Node left, right;
    int data;
    
    public Node(int val) {
        this.data = val;
    }
}

class BTreePrinter {
    
    public static Node root;
    
	public BTreePrinter()
	{ root = null; }
    
    
     public static Node createTreeNode(int input[], int index){
        if(index<=input.length){
            int value = input[index-1];
          
                Node t = new Node(value);
                t.left = createTreeNode(input, index*2);
                t.right = createTreeNode(input, index*2+1);
                return t;
            
        }
        return null;
    }
    
    public static void displayTree(int arr[]){
        
        root = BTreePrinter.createTreeNode(arr,1);
        BTreePrinter.printNode(root);
        
    }

    public static void printNode(Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static boolean isAllElementsNull(List<Node> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
