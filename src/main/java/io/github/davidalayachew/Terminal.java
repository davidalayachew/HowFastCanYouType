package io.github.davidalayachew;

import java.util.List;;
import java.util.Scanner;
import java.util.Set;

/** Class containing the Terminal implementation of InteractMode. */
public final class Terminal extends InteractMode
{

   @Override
   public void print(final String message)
   {
   
      System.out.println(message);
   
   }
   
   @Override
   public String prompt(final String message)
   {
   
      print(message);
   
      return new Scanner(System.in).nextLine();
   
   }
   
   @Override
   public <T> T pickOne(final String message, final Set<T> options)
   {
   
      this.print("Please select from one of the following options.");
      
      final List<T> list = List.copyOf(options);
   
      for (int i = 1; i <= options.size(); i++)
      {
      
         this.print(i + ")\t" + list.get(i));
      
      }
      
      final int response = this.promptForIntWithBounds(message, list);
   
      return null;
   
   }
   
   @Override
   public <T> Set<T> pickMultiple(final String message, final Set<T> options)
   {
   
      return null;
   
   }
   
}
