/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storeinfosystem;
import wrapper.*;
import java.util.ArrayList;

/**
 *
 * @author humbata1
 */
public class StoreOperations {
    
    
    public static ArrayList<Integer> StrCompare(String str) 
    {
       char[] charArray;
       charArray = str.toCharArray();
       database.openBusDatabase();
       int[] itemIDs = ItemInfo.getItems();
       String[] itemNames = new String [itemIDs.length];
      
       
       int i,trueCount;
       ArrayList<Integer> listData= new ArrayList<>();
       listData.clear();
       char[] dataArray;
       boolean comp;
       for (int j=0;j<itemIDs.length;j++)
       {
         trueCount=0;
         i=0;
                  dataArray = ItemInfo.getName(itemIDs[j]).toCharArray();
         comp =CharCompare(charArray[i],dataArray[i]);

        // System.out.println(j + " :  " + ItemInfo.getName(itemIDs[j]));
         while(comp==true && i!=charArray.length && i!=dataArray.length)
         {
            comp= CharCompare(charArray[i],dataArray[i]);
            if (comp == false) break;
          // System.out.println(charArray[i] + " : compared : " + dataArray[i]);
            trueCount++;
            i++;

         }
         if (trueCount == charArray.length )
             listData.add(itemIDs[j]);
       }
       return listData;
    }
    
    public static boolean CharCompare(char chr,char chr2)
    {
       if (Character.toUpperCase(chr)== Character.toUpperCase(chr2))
           return true;
       else if ((chr=='ı' && chr2=='i') || (chr=='I' && chr2=='ı') || (chr=='ı' && chr2=='I'))
           return true;
       else
         return false;
    }
}
