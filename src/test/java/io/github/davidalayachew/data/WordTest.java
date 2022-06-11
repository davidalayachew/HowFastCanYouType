package io.github.davidalayachew.data;

import io.github.davidalayachew.EnumMethods;

import org.junit.Assert;
import org.junit.Test;

public class WordTest
{

   /** A test that always fails. **/
   @Test
   public void testAsStringList()
   {
   
      final int length = Word.values().length;
      
      //Negatives -- any negative number will result in the full list
      Assert.assertEquals(length, EnumMethods.asStringList(-100, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(-3, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(-2, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(-1, Word.class).size());
      
      //Zeroes -- zero will result in the full list
      Assert.assertEquals(length, EnumMethods.asStringList(-0, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(0, Word.class).size());
      
      //Positives below MAX -- will result in the specified amount of elements
      Assert.assertEquals(1, EnumMethods.asStringList(1, Word.class).size());
      Assert.assertEquals(2, EnumMethods.asStringList(2, Word.class).size());
      Assert.assertEquals(3, EnumMethods.asStringList(3, Word.class).size());
      Assert.assertEquals(length - 4, EnumMethods.asStringList(length - 4, Word.class).size());
      Assert.assertEquals(length - 3, EnumMethods.asStringList(length - 3, Word.class).size());
      Assert.assertEquals(length - 2, EnumMethods.asStringList(length - 2, Word.class).size());
      Assert.assertEquals(length - 1, EnumMethods.asStringList(length - 1, Word.class).size());
      
      //MAX and above -- will result in the full list
      Assert.assertEquals(length, EnumMethods.asStringList(length, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(length + 1, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(length + 2, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(length + 3, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(length + 4, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(Integer.MAX_VALUE - 4, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(Integer.MAX_VALUE - 3, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(Integer.MAX_VALUE - 2, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(Integer.MAX_VALUE - 1, Word.class).size());
      Assert.assertEquals(length, EnumMethods.asStringList(Integer.MAX_VALUE, Word.class).size());
   
   }

}
