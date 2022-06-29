package io.github.davidalayachew;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/** Class containing the Terminal implementation of InteractMode. */
public final class Terminal extends InteractMode
{

   //I feel like this entire process should be overhauled.
   //Instead of doing the action of printing, we should have methods that handle printing, and others that handle string formation.

   @Override
   public void printSingle(final String message)
   {
   
      System.out.println(message);
   
   }
   
   @Override
   public <T> String stringMultiple(final Collection<T> messages)
   {
   
      String response = "";
   
      for (T each : messages)
      {
      
         response += each.toString() + "\n";
      
      }
      
      return response;
   
   }
   
   @Override
   public <T> String stringMultipleNumbered(final List<T> list)
   {
   
      String response = "";
   
      for (int i = 0; i < list.size(); i++)
      {
      
         response += i + "\t - " + list.get(i) + "\n";
      
      }
      
      return response;
   
   }
   
   @Override
   public String prompt(final String message)
   {
   
      this.printSingle(message);
   
      return new Scanner(System.in).nextLine();
   
   }
   
   @Override
   public <T> T pickOne(final String message, final Set<T> options)
   {
   
      final List<T> list = List.copyOf(options);
   
      this.printSingle("Please select from one of the following options.");
   
      this.printSingle(this.stringMultipleNumbered(list));
      
      final int response = this.promptForIndex(message, list);
   
      return list.get(response);
   
   }
   
   @Override
   public <T> Set<T> pickMultiple(final String message, final Set<T> options)
   {
   
      return null;
   
   }
   
}
