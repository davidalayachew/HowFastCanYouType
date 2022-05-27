package io.github.davidalayachew;

import io.github.davidalayachew.data.Word;
import io.github.davidalayachew.data.GameOption;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/** Enum to represent the different ways for the player to interact with the application. */
public abstract sealed class InteractMode permits Terminal, GUI
{

   /** Pattern for checking if a String is an Integer. */
   private static final Pattern INTEGER_PATTERN = Pattern.compile("^\\d{1,6}$");

   /** Set of words that the user will be typing this round. */
   private final List<String> words;
   
   /** Set of game options that will be active this round. */
   private final Set<GameOption> options;

   /** Constructor. */
   public InteractMode()
   {
   
      words = Word.asStringList(this.promptForInt("Please enter how many words you would like to type"));
      options = null;
      
      System.out.println(words);
   
   }

   /** Method to handle playing the game loop. */
   public void play()
   {
   
   }

   /**
    * 
    * Method that prints the given message.
    * 
    * @param   message     Message to be printed to the user.
    * 
    */
   public abstract void print(final String message);

   /**
    * 
    * Method that asks the user the given message, then returns the String response.
    * 
    * @param   message     Message to be printed to the user.
    * @return              The String response from the user.
    * 
    */
   public abstract String prompt(final String message);

   /**
    * 
    * Method that asks the user for an integer (using the given message), then returns the integer response.
    * 
    * @param   message     Message to be printed to the user.
    * @return              The integer response from the user.
    * 
    */
   public int promptForInt(final String message)
   {
   
      boolean valid = false;
      String response = "";
   
      while (!valid)
      {
      
         response = this.prompt(message);
         
         if (response == null)
         {
         
            continue;
         
         }
      
         valid = INTEGER_PATTERN.matcher(response).matches();
         
         if (!valid)
         {
         
            this.print(response + " is not a valid number.");
         
         }
      
      }
      
      return Integer.parseInt(response);
   
   }

   /**
    * 
    * Method that asks the user (using the given message) to pick ONE option from the given options.
    * 
    * @param   message     Message to be printed to the user.
    * @param   options     Options for the user to choose from.
    * @param   <T>         The type parameter.
    * @return              The single option that the user chose.
    * 
    */
   public abstract <T> T pickOne(final String message, final Set<T> options);

   /**
    * 
    * Method that asks the user (using the given message) to pick MULTIPLE options from the given options.
    * 
    * @param   message     Message to be printed to the user.
    * @param   options     Options for the user to choose from.
    * @param   <T>         The type parameter.
    * @return              The multiple options that the user chose.
    * 
    */
   public abstract <T> Set<T> pickMultiple(final String message, final Set<T> options);

}