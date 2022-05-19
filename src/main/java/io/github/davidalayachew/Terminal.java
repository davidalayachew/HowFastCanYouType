package io.github.davidalayachew;

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
   
      return null;
   
   }
   
   @Override
   public <T> Set<T> pickMultiple(final String message, final Set<T> options)
   {
   
      return null;
   
   }
   
}
