package io.github.davidalayachew.data;

import io.github.davidalayachew.EnumMethods;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class WordTest
{

   private static final List<String> ALL = java.util.Arrays.stream(Word.values()).map(Word::name).toList();

   @Test
   public void testAsStringList()
   {
   
      final int length = Word.values().length;
   
      //Negatives -- any negative number will result in the full list
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(-100, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(-3, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(-2, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(-1, Word.class)));
      
      //Zeroes -- zero will result in the full list
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(-0, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(0, Word.class)));
      
      //Positives below MAX -- will result in the specified amount of elements
      Assert.assertEquals(1, EnumMethods.asStringList(1, Word.class).size());
      Assert.assertEquals(2, EnumMethods.asStringList(2, Word.class).size());
      Assert.assertEquals(3, EnumMethods.asStringList(3, Word.class).size());
      Assert.assertEquals(length - 4, EnumMethods.asStringList(length - 4, Word.class).size());
      Assert.assertEquals(length - 3, EnumMethods.asStringList(length - 3, Word.class).size());
      Assert.assertEquals(length - 2, EnumMethods.asStringList(length - 2, Word.class).size());
      Assert.assertEquals(length - 1, EnumMethods.asStringList(length - 1, Word.class).size());
      
      //MAX and above -- will result in the full list
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(length, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(length + 1, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(length + 2, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(length + 3, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(length + 4, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(Integer.MAX_VALUE - 4, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(Integer.MAX_VALUE - 3, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(Integer.MAX_VALUE - 2, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(Integer.MAX_VALUE - 1, Word.class)));
      Assert.assertEquals(ALL, sort(EnumMethods.asStringList(Integer.MAX_VALUE, Word.class)));
   
   }
   
   private static List<String> sort(List<String> list)
   {
   
      var abc = new java.util.ArrayList<>(list);
   
      java.util.Collections.sort(abc);
      
      return List.copyOf(abc);
   
   }

}
