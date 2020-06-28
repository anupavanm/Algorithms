using System;
using System.Collections.Generic;

class MaxSumPossible{

  public static void Main(string[] args){

    //Given an array of integers of size ‘n’.
    //Our aim is to calculate the maximum sum possible for ‘k’ consecutive elements in the array.
    TestCase1();
    TestCase2();
  }

  //Brute Force Approach
    //Iterate through the items, and inner loop from current item to next k-1 items, if the current sum > previous sum, store current sum as maximum value. so by end of the method you would get maximum.
    //Time Complexity - O(n^2)
    //Space Complexity - O(1) - No extra datastrcutures are used


//Sliding window approach
  //Precalculate the sum of first K items
  //Loop through input[k] to input.Length, everytime remove the input[i - k] and add input[k] to the sum
  //If current sum > max sum, set max sum = current sum.
  //Time Complexity - O(n)
  //Space - O(1)

  public static int MaxSumWindowApproach(int[] input, int noOfElements){

    int maxSum = 0;
    int currentSum = 0;

    for(int i = 0; i < noOfElements; i++){
      currentSum += input[i];
    }

    for(int j = noOfElements; j < input.Length; j++ ){
      currentSum = currentSum - input[j - noOfElements] + input[j];
      if(currentSum > maxSum){
        maxSum = currentSum;
      }
    }

    return maxSum;

  }


  public static  void TestCase1(){

    int[] input  = new int[]{100, 200, 300, 400};
    int k = 2;
    int expectedOutPut = 700;

    int result =  MaxSumWindowApproach(input, k);

    Console.WriteLine(result);

  }

  public static void TestCase2(){

    int[] input  = new int[]{1, 5, 7, 8, 20, 29, 1,34,44,103};
    int k = 3;
    int expectedOutPut = 181;

    int result =  MaxSumWindowApproach(input, k);

    Console.WriteLine(result);
  }

}