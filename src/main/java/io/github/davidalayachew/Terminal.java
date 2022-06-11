package io.github.davidalayachew;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/** Class containing the Terminal implementation of InteractMode. */
public final class Terminal extends InteractMode
{

   @Override
   public void printSingle(final String message)
   {
   
      System.out.println(message);
   
   }
   
   @Override
   public <T> void printMultiple(final Collection<T> messages)
   {
   
      for (T each : messages)
      {
      
         this.printSingle(each.toString());
      
      }
   
   }
   
   @Override
   public <T> void printMultipleNumbered(final List<T> list)
   {
   
      for (int i = 0; i < list.size(); i++)
      {
      
         this.printSingle(i + "\t -" + list.get(i));
      
      }
   
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
   
      this.printMultiple(list);
      
      final int response = this.promptForIndex(message, list);
   
      return list.get(response);
   
   }
   
   @Override
   public <T> Set<T> pickMultiple(final String message, final Set<T> options)
   {
   
      return null;
   
   }
   
}
