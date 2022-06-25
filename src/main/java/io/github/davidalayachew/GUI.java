package io.github.davidalayachew;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/** Class for the GUI implementation of InteractMode. */
public final class GUI extends InteractMode
{

   @Override
   public void printSingle(final String message)
   {
   
      JOptionPane.showMessageDialog(null, message);
   
   }

   @Override
   public <T> void printMultiple(final Collection<T> messages)
   {
   
      unfinished();
   
   }
   
   @Override
   public <T> String stringMultipleNumbered(final List<T> list)
   {
   
      throw new UnsupportedOperationException();
   
   }
   
   @Override
   public String prompt(final String message)
   {
   
      return JOptionPane.showInputDialog(message);
   
   }

   @Override
   public <T> T pickOne(final String message, final Set<T> options)
   {
   
      final int selection = 
            JOptionPane.showOptionDialog(
               null,
               message,
               "Select one",
               JOptionPane.DEFAULT_OPTION,
               JOptionPane.QUESTION_MESSAGE,
               null,
               options.toArray(),
               null
            );
   
      if (selection == -1)
      {
      
         throw new IllegalArgumentException("No option selected. User decided to quit!");
      
      }
   
      return new ArrayList<>(options).get(selection);
   
   }

   @Override
   public <T> Set<T> pickMultiple(final String message, final Set<T> options)
   {
   
      return null;
   
   }

}
