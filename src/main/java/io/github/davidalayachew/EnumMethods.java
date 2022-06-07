package io.github.davidalayachew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/** Interface to provide some helpful methods for the enums of this project. Only enums should implement this interface. */
public interface EnumMethods
{

   /**
    * 
    * Takes all values of this enum and returns them as a {@code Set<T>}.
    * 
    * @param   clazz                   The class that I will be making a set from.
    * @param   <T>                     The enum type that we will be creating the Set
    *                                  results from.
    * @return                          The set of the enum values.
    * 
    */
   public static <T extends Enum<T>> Set<T> asSet(final Class<T> clazz)
   {
   
      return EnumSet.allOf(clazz);
   
   }

   /**
    * 
    * Takes all values of this enum and returns them as a {@code List<T>}.
    * 
    * @param   clazz                   The class that I will be making a list from.
    * @param   <T>                     The enum type that we will be creating the List
    *                                  results from.
    * @return                          The List of the enum values.
    * 
    */
   public static <T extends Enum<T>> List<T> asList(final Class<T> clazz)
   {
   
      return Arrays.asList(clazz.getEnumConstants());
   
   }

   /**
    * 
    * Takes all values of this enum and returns them as a {@code List<String>}.
    * 
    * @param   desiredNumberOfWords    parameter deciding how many words will be returned.
    *                                  If the desired number is {@code >= the number of words } in
    *                                  this enum, then all of the words are returned. The
    *                                  same occurs if the desired number is {@code <= 0 }.
    * @param   clazz                   The class that I will be making a list from.
    * @param   <T>                     The enum type that we will be creating the List
    *                                  results from.
    * @return                          {@code List<String>} of the desired number of words.
    * 
    */
   public static <T extends Enum<T>> List<String> asStringList(final int desiredNumberOfWords, final Class<T> clazz)
   {
   
      final List<T> list = asList(clazz);
      Collections.shuffle(list);
   
      return
         list.stream()
            .map(T::name)
            .limit(desiredNumberOfWords <= 0 ? list.size() : desiredNumberOfWords)
            .toList()
            ;
   
   }

}
