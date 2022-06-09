package io.github.davidalayachew;

import io.github.davidalayachew.data.Word;
import io.github.davidalayachew.data.GameOption;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/** Enum to represent the different ways for the player to interact with the application. */
public abstract sealed class InteractMode permits Terminal, GUI
{

   /** Pattern for checking if a String is an Integer. */
   private static final Pattern INTEGER_PATTERN = Pattern.compile("^\\d{1,6}$");
   
   /** Default min int value. */
   private static final int MIN = 0;
   
   /** Default max int value. */
   private static final int MAX = 999_999;

   /** Set of words that the user will be typing this round. */
   private final List<String> words;

   /** Set of game options that will be active this round. */
   private final Set<GameOption> options;

   /** Constructor. */
   public InteractMode()
   {
   
      words =
         EnumMethods.asStringList
         (
            this.pickOne
            (
               "Please enter how many words you would like to type",
               Set.copyOf(IntStream.rangeClosed(1, Word.values().length).boxed().toList())
            ),
            Word.class
         );
         
      options = Set.of(this.pickOne("Please select one of the following Game Options.", Set.of(GameOption.values())));
      
      System.out.println(words);
      System.out.println(options);
   
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
   
      while (true)
      {
      
         final String response = this.prompt(message);
         
         if (!isValidNumber(response))
         {
         
            this.print(response + " is not a valid number.");
            continue;
         
         }
         
         else
         {
         
            return Integer.parseInt(response);
         
         }
      
      }
   
   }

   public <T> int promptForIndex(final String message, final Collection<T> collection)
   {
   
      final int min = 1;
      final int max = collection.size();
   
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

   public boolean isValidNumber(final String potential)
   {
   
      if (response == null)
      {
      
         throw new IllegalArgumentException("User decided to quit!");
         
      }
      
      return INTEGER_PATTERN.matcher(response).matches();
       
   }

}