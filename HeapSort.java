   public class HeapSort
   {
      public static final int SIZE = 9;
      public static void swap(double[] array, int a, int b)
      {
         double c= array[a];
         array[a]=array[b];
         array[b]=c;
      }
      
      public static void heapDown(double[] array, int k, int size)
      {
         if(k*2<=size&&k*2+1<=size)
         {
            if(array[k*2]>array[k*2+1]&&array[k*2]>array[k])
            {
               swap(array,k,k*2);
               heapDown(array,k*2,size);
            }
            else if(array[k*2+1]>array[k])
            {
               swap(array,k,k*2+1);
               heapDown(array,k*2+1,size);
            }
         }
         else if(k*2<=size)
         {
            if(array[k*2]>array[k])
            {
               swap(array,k,k*2);
               heapDown(array,k*2,size);
            }
         }
         else if(k*2+1<=size)
         {
            if(array[k*2+1]>array[k])
            {
               swap(array,k,k*2+1);
               heapDown(array,k*2+1,size);
            }
         }
      }
      
      public static void sort(double[] array)
      {
         int size=array.length-1;
         while(size>1)
         {
            swap(array,1,size);
            size--;
            heapDown(array,1,size);
         }
      }
      
      public static void display(double[] array)
      {
         for(int k = 1; k < array.length; k++)
            System.out.print(array[k] + "    ");
         System.out.println("\n"); 
      }
      
      public static void createRandom(double[] array)
      {  
         for(int k = 1; k <= SIZE; k++)
         {
            int n;
            do
               n = (int)(Math.random() * 9000);
            while(n % 10 == 0);
            array[k] =  n / 100.0;
         }
      }
      
      public static void makeHeap(double[] array, int size)
      {
         for(int k=size/2;k>=1;k--)
            heapDown(array,k,size);
      }
      
      public static void main(String[] args)
      {
      // Part 2:  Create a heap from random numbers, sort it.
         double[] array = new double[SIZE + 1];
         createRandom(array);
         display(array);
         makeHeap(array, SIZE);
         display(array); 
         sort(array);
         display(array);
              
      // //Part 1: Given a heap, sort it. Do this part first.   
         // double array[] = {-1,99,80,85,17,30,84,2,16,1};
         // display(array);
         // sort(array);
         // display(array);
      }
   }
