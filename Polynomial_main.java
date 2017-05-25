    import java.util.*;
   public class Polynomial_main
   {
      public static void main(String[] args)
      {
         Polynomial poly = new Polynomial();
         poly.addTerm(1, -4);
         poly.addTerm(3, 2);
         poly.addTerm(0, 2);
         System.out.println(poly.toString());
         System.out.println(poly.evaluate(2.0));
       
         Polynomial poly2 = new Polynomial();
         poly2.addTerm(1, -4);
         poly2.addTerm(3, 2);
         poly2.addTerm(0, 3);
         poly2.addTerm(2, 1); 
         System.out.println(poly2.toString());
         System.out.println(poly.add(poly2));
         System.out.println(poly.multiply(poly2));
         

      }
   }


   class Polynomial implements Polyinter
   {
      private Map<Integer,Integer> m;
      public Polynomial()
      {
         m=new TreeMap<Integer,Integer>();
      }  
    
      public String toString()
      {
         String s="";
         Iterator<Integer> it = m.keySet().iterator();
         if(it.hasNext())
         {
            while(it.hasNext())
            {
               int temp=it.next();
               String tem;
               if(m.get(temp)!=0)
               {
                  if(temp==0) //if the exponent is 0, then x^0 will always give 1 and is not printed
                  {
                     tem=m.get(temp)+" + ";
                  }
                  else
                     if(m.get(temp)==1) //if the coefficient is 1, it will not be printed
                        tem="x"+"^"+temp+" + ";
                     else
                        tem=m.get(temp)+"x"+"^"+temp+" + ";
                  s=tem+s;
               }
            }
            return s.substring(0,s.length()-2); //removes the last +
         }
         else
            return "";
      }
      
      public double evaluate(double x)
      {//goes through the map and evaluates with the specified argument
         double total = 0.0;
         Iterator<Integer> it = m.keySet().iterator();
         while(it.hasNext())
         {
            int temp = it.next();
            total+=(Math.pow(x,temp)*m.get(temp));
         } 
         return total;
      }
      
      public void addTerm(int power, int co)
      {
         m.put(power,co); //simply adds the coefficient and power to the map, with the power as the key
      }
      
      public Polynomial add(Polynomial p)
      {
         Polynomial pnew = new Polynomial(); //the polynomial to be returned
         Iterator<Integer> it2 = m.keySet().iterator();
         while(it2.hasNext()) //puts all values from this map to the new polynomial
         {
            int temp2=it2.next();
            pnew.addTerm(temp2,m.get(temp2));
         }
         Iterator<Integer> it=p.m.keySet().iterator();
         while(it.hasNext()) //goes through the second map and adds its terms appropiately
         {
            int temp = it.next();
            if(pnew.m.containsKey(temp)) //if there is already a value with that power, then just add the coefficients together
            {
               pnew.addTerm(temp,m.get(temp)+p.m.get(temp));
            }
            else
               pnew.addTerm(temp,p.m.get(temp));
         }
         return pnew;
      }
      
      public Polynomial multiply(Polynomial p)
      {
         Polynomial pnew = new Polynomial(); //the polynomial to be returned
         Map<Integer,Integer> m2 = p.m; //the map from the other polynomial to be multiplied by
         Iterator<Integer> it = m.keySet().iterator();
         while(it.hasNext())
         {
            int temp = it.next();
            Iterator<Integer> it2 = m2.keySet().iterator();
            while(it2.hasNext()) //multiplies the value from the first polynomial by every value in the second polynomial
            {
               int temp2 = it2.next();
               if(pnew.m.containsKey(temp+temp2))//if that exponent is already in the new polynomial, then adds them together
                  pnew.addTerm(temp+temp2,(m.get(temp)*m2.get(temp2))+pnew.m.get(temp+temp2));
               else
                  pnew.addTerm(temp+temp2,m.get(temp)*m2.get(temp2));
            }
         }
         return pnew;
      }
   }
   
   interface Polyinter //interface used for organization
   {
      public abstract String toString();
      public abstract double evaluate(double x);
      public abstract void addTerm(int power, int co);
      public abstract Polynomial add(Polynomial p);
      public abstract Polynomial multiply(Polynomial p);
   }
   
 /*  
 expected output
  2x^3 + -4x^1 + 2
  10.0
  2x^3 + x^2 + -4x^1 + 3
  4x^3 + x^2 + -8x^1 + 5
  4x^6 + 2x^5 + -16x^4 + 6x^3 + 18x^2 + -20x^1 + 6
  */