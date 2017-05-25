//outputs to dictionaryOutput, requires spanglish.txt

import java.io.*;
   import java.util.*;
   public class Dictionary_main
   {
      public static void main(String[] args) throws Exception
      {
         try
         {
            System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
         }
            catch(Exception e)
            {
            }
         Map<String, Set<String>> eng2spn = new TreeMap<String, Set<String>>();
         Scanner infile = new Scanner(new File("spanglish.txt"));
         while(infile.hasNext())
         {
            add(eng2spn, infile.next(), infile.next());
         }
         infile.close();
         System.out.println("ENGLISH TO SPANISH");
         display(eng2spn);
      
         Map<String, Set<String>> spn2eng = reverse(eng2spn);
         System.out.println("SPANISH TO ENGLISH");
         display(spn2eng);
      }
      
      public static void display(Map<String, Set<String>> m)
      {//prints out the values of the sets in m
         Iterator i = m.keySet().iterator();
         while(i.hasNext())
         {
            Object temp = i.next();
            System.out.println("\t"+temp+" "+m.get(temp));
         }
      }
      
      public static void add(Map<String, Set<String>> dictionary, String word, String translation)
      {//word is in english, translations is spanglish
         if(!dictionary.containsKey(word)) //if the word does not already have a translation make a new set for its translations
            dictionary.put(word,new TreeSet<String>());
       
         dictionary.get(word).add(translation); //find the key and add its translation
      }
      public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
      {
         TreeMap<String, Set<String>> tr = new TreeMap<String, Set<String>>();
         
         for(String st : dictionary.keySet()) //goes through the dictionary finding all the english words
         {
            for(String str : dictionary.get(st)) //for each english word, get all the spanglish translations
            {
               add(tr,str,st); //add the english translation to the spanglish key  
            }
         }
         return tr;
      }
   }
      /********************
 INPUT:
    holiday
  fiesta
  holiday
  vacaciones
  party
  fiesta
  celebration
  fiesta
     <etc.>
  *********************************** 
 OUTPUT:
  ENGLISH TO SPANISH
   banana [banana]
   celebration [fiesta]
   computer [computadora, ordenador]
   double [doblar, doble, duplicar]
   father [padre]
   feast [fiesta]
   good [bueno]
   hand [mano]
   hello [hola]
   holiday [fiesta, vacaciones]
   party [fiesta]
   plaza [plaza]
   priest [padre]
   program [programa, programar]
   sleep [dormir]
   son [hijo]
   sun [sol]
   vacation [vacaciones]

  SPANISH TO ENGLISH
   banana [banana]
   bueno [good]
   computadora [computer]
   doblar [double]
   doble [double]
   dormir [sleep]
   duplicar [double]
   fiesta [celebration, feast, holiday, party]
   hijo [son]
   hola [hello]
   mano [hand]
   ordenador [computer]
   padre [father, priest]
   plaza [plaza]
   programa [program]
   programar [program]
   sol [sun]
   vacaciones [holiday, vacation]

**********************/