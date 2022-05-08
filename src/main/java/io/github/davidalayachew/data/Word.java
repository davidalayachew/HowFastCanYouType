package io.github.davidalayachew.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** Enum to represent the list of words that can be used for the typing test. */
public enum Word
{

   APPLE,
   BANANA,
   CHERRY,
   DONUT,
   EGG,
   FALAFEL,
   GNOCCHI,
   HAM,
   ICE,
   JUICE,
   KALE,
   LETTUCE,
   MOZZARELLA,
   NACHOS,
   ORANGE,
   PAPAYA,
   QUESADILLA,
   RICE,
   SOUP,
   TORTILLA,
   UDON,
   VINEGAR,
   WATER,
   XYLITOL,
   YAM,
   ZUCCHINI,
   ;
   
   /**
    * 
    * Takes all values of this enum and returns them as a {@code List<String>}.
    * 
    * @param   desiredNumberOfWords    parameter deciding how many words will be returned.
    *                                  If the desired number is >= the number of words in
    *                                  this enum, then all of the words are returned. The
    *                                  same occurs if the desired number is <= 0.
    * @return                          {@code List<String>} of the desired number of words.
    * 
    */
   public static List<String> asStringList(int desiredNumberOfWords)
   {
   
      final List<Word> list = Arrays.asList(values());
      Collections.shuffle(list);
   
      return
         list.stream()
            .map(Word::name)
            .limit(desiredNumberOfWords <= 0 ? values().length : desiredNumberOfWords)
            .toList()
            ;
   
   }

}
