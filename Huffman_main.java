   import java.util.Scanner;
   import java.io.*;
   import java.util.*;
   public class Huffman_main
   {
      public static void main(String[] args) throws IOException
      {
         Scanner prompt=new Scanner(System.in);
         System.out.print("Message? "); //prompts for message
         String message = prompt.next();
         Map<Character,Integer> freq = getFrequency(message); //gets the frequencys of each letter
         PriorityQueue<TreeNode> queue = intoQueue(freq); //creates a priorityqueue using the frequencys
         TreeNode code = code(queue); //creates the code tree
         printScheme(code, message); //prints out the scheme and message
      }
      
      public static Map<Character,Integer> getFrequency(String message)
      {//creates a map with each letter pointing to its frequency
         Map<Character,Integer> m = new HashMap<Character,Integer>();
         for(int x=0;x<message.length();x++)
         {
            Character temp = message.charAt(x);
            if(m.containsKey(temp))
            {
               m.put(temp,m.get(temp)+1); //it that letter is already found, simply increment the frequency of it
            }
            else
            {
               m.put(temp,1); //else make a new entry
            }
         }
         return m;
      }
      
      public static PriorityQueue<TreeNode> intoQueue(Map<Character,Integer> m)
      {//makes a priority queue of treenodes with letters in each one, containing the char and frequency, and sorted by frequency, with lowest frequency at front
         PriorityQueue<TreeNode> queue = new PriorityQueue<TreeNode>();  //treenode class now implements comparable, making this possible
         Iterator<Character> it = m.keySet().iterator();
         while(it.hasNext())
         {
            char temp = it.next();
            queue.add(new TreeNode(new Letter(temp,m.get(temp))));
         }
         return queue;
      }
      
      public static TreeNode code(PriorityQueue<TreeNode> p)
      {//goes through each node in the priority queue, calling a recursive helper on it if not null
         if(p!=null)
            return encode(p);
         else
            return null;
      }
    
      private static TreeNode encode(PriorityQueue<TreeNode> p)
      {//removes the two lowest nodes, makes them children of a third node and puts the new node into the priority queue. keeps going until there is only one node, which is the final code tree.
         if(p.size()==1)
            return p.remove();
         else
         {
            TreeNode t1 = p.remove();
            TreeNode t2 = p.remove();
            Letter let = new Letter('*',((Letter)t1.getValue()).getFreq()+((Letter)t2.getValue()).getFreq());
            TreeNode t3 = new TreeNode(let,t1,t2);
            p.add(t3);
            return code(p);
         }
      }
      
      public static void printScheme(TreeNode t, String message)
      {//first makes a set of all letters (no duplicates) and also prints out the message at same time
      //then, takes that set to make the scheme
         try
         {
            System.setOut(new PrintStream(new FileOutputStream("message.xxx.txt")));
         }
            catch(Exception e)
            {
            }
         Set<Character> letters = new HashSet<Character>();
         for(int x=0;x<message.length();x++)
         {
            letters.add(message.charAt(x));
            System.out.print(printScheme(t,message.charAt(x)));
         }
         try
         {
            System.setOut(new PrintStream(new FileOutputStream("scheme.xxx.txt")));
         }
            catch(Exception e)
            {
            }
         Iterator<Character> it = letters.iterator();
         while(it.hasNext())
         {
            char temp = it.next();
            System.out.println(temp+printScheme(t,temp));
         }
         
      }
      
      private static String printScheme(TreeNode t, char c)
      {//recursively uses treenodes find method to go left and right until finding the char, creating a string at same time
         Character ch = ((Letter)t.getValue()).getChar();
         if(ch.equals(c))
         {
            return "";
         }
         else if(t.getLeft()!=null&&t.getLeft().find(c))
         {
            return "0"+printScheme(t.getLeft(),c);
         }
         else if(t.getRight()!=null&&t.getRight().find(c))
         {
            return "1"+printScheme(t.getRight(),c);
         }
         return "";
      }
   }
   
   class Letter implements Comparable<Letter>
   {//resource class containing both a char and its frequency
      Character let;
      int freq;
      public Letter(Character ch, int fre)
      {
         let=ch;
         freq=fre;
      } 
      
      public int compareTo(Letter l)
      { //the letter with smaller frequency has greater priority
         if(freq>l.freq)
            return 1;
         else if(freq<l.freq)
            return -1;
         else
            return 0;
      }
         
      public boolean equals(Letter l)
      {
         return compareTo(l)==0;
      }
         
      public String toString()
      {
         return let+":"+freq;
      }  
      
      public int getFreq()
      {
         return freq;
      }
      
      public char getChar()
      {
         return let;
      }
   }
   
   class TreeNode implements Comparable<TreeNode>
   {//implements comparable now
      private Object value; 
      private TreeNode left, right;
   
      public TreeNode(Object initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
      public Object getValue()
      { 
         return value; 
      }
   
      public TreeNode getLeft() 
      { 
         return left; 
      }
   
      public TreeNode getRight() 
      { 
         return right; 
      }
   
      public void setValue(Object theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(TreeNode theNewLeft) 
      { 
         left = theNewLeft;
      }
   
      public void setRight(TreeNode theNewRight)
      { 
         right = theNewRight;
      }
      
      public int compareTo(TreeNode t)
      {
         if(((Comparable)value).compareTo(((Comparable)t.value))>0)
            return 1;
         else if(((Comparable)value).compareTo(((Comparable)t.value))<0)
            return -1;
         else
            return 0;
      }
      
      public boolean find(Character ob)
      {//since this class is only used in this lab, the method here is specificaly designed with Letters in mind
      //recursively determines if this node or any of its children contain the specified character
         if(ob.equals(((Letter)getValue()).getChar()))
            return true;
         else if(getLeft()!=null&&getLeft().find(ob))
            return true;
         else if(getRight()!=null&&getRight().find(ob))
            return true;
         return false;
      }  
   }


