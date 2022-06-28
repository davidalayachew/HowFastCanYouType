package io.github.davidalayachew;

import io.github.davidalayachew.data.Word;
import io.github.davidalayachew.data.GameOption;
import java.util.Collection;
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
   public static final int MIN = 0;

   /** Default max int value. */
   public static final int MAX = 999_999;

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
            this.promptForIntWithinBounds
            (
               "Please enter how many words you would like to type",
               1,
               10
            ),
            Word.class
         );
   
      options = Set.of(this.pickOne("Please select one of the following Game Options.", Set.of(GameOption.values())));
   
      System.out.println(words);
      System.out.println(options);
   
   }
   
   /** To facilitate development and prevent glossing over incomplete work, I create this method with just throws an exception. */
   public void unfinished()
   {
   
      throw new UnsupportedOperationException();
   
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
   public abstract void printSingle(final String message);

   /**
    *
    * Method that returns the given messages as a user-friendly String.
    *
    * @param   messages    Messages to be formatted into user-friendly Strings.
    * @return              The user-friendly String.
    *
    */
   public String stringMultiple(final String... messages)
   {
   
      return this.stringMultiple(List.of(messages));
   
   }
   
   /**
    * 
    * Method that returns the given collection in a user-friendly String.
    * 
    * @param   collection  The collection to be printed.
    * @param   <T>         The type parameter of the collection to be printed.
    * @return              The user-friendly String.
    * 
    */
   public abstract <T> String stringMultiple(final Collection<T> collection);

   /**
    * 
    * Method that returns the given list as a user-friendly string, with each entry given a unique number.
    * 
    * @param   list        The list to be printed.
    * @param   <T>         The type parameter of the list to be printed.
    * @return              The list as a user friendly string.
    * 
    */
   public abstract <T> String stringMultipleNumbered(final List<T> list);

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
         
            this.printSingle(response + " is not a valid number.");
            continue;
         
         }
         
         else
         {
         
            return Integer.parseInt(response);
         
         }
      
      }
   
   }

   /**
    *
    * Method that asks the user for an integer (using the given message), then returns the integer response.
    *
    * @param   message     Message to be printed to the user.
    * @param   min         The minimum value the result is permitted to be.
    * @param   max         The maximum value the result is permitted to be.
    * @return              The integer response from the user.
    *
    */
   public int promptForIntWithinBounds(final String message, final int min, final int max)
   {
   
      while (true)
      {
      
         final String response = this.prompt(message + " [" + min + ", " + max + "]");
      
         if (!isValidNumber(response))
         {
         
            this.printSingle(response + " is not a valid number.");
            continue;
         
         }
      
         final int result = Integer.parseInt(response);
      
         if (result < min || result > max)
         {
         
            this.printSingle(result + " is not permitted. Your number must be between " + min + " and " + max + " (inclusive)");
         
         }
         
         else
         {
         
            return result;
         
         }
      
      }
   
   }

   /**
    *
    * Method that asks the user for an index of the given list of options (using the given message),
    * then returns the integer response.
    *
    * @param   message     Message to be printed to the user.
    * @param   list        The list of possible options.
    * @param   <T>         The type parameter of the list.
    * @return              An index of an item found within the list.
    *
    */
   public <T> int promptForIndex(final String message, final List<T> list)
   {
   
      return this.promptForIntWithinBounds(message, 0, list.size() - 1);
   
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

   /**
    *
    * Returns true if the given String can be safely converted into a valid number.
    *
    * @param   potential   The String that may also be a valid input for converting into a number.
    * @return              Return true if it can be safely converted. Otherwise, return false.
    *
    */
   public boolean isValidNumber(final String potential)
   {
   
      if (potential == null)
      {
      
         throw new IllegalArgumentException("No response was given");
      
      }
   
      return INTEGER_PATTERN.matcher(potential).matches();
   
   }

}