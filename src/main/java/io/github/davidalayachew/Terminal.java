package io.github.davidalayachew;

import java.util.Set;

/** Class containing the Terminal implementation of InteractMode. */
public final class Terminal extends InteractMode
{

   @Override
   public void print(final String message)
   {
   
      return;
   
   }
   
   @Override
   public String prompt(final String message)
   {
   
      return null;
   
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
