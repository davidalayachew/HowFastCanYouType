package io.github.davidalayachew.data;

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
      Assert.assertEquals(length, Word.asStringList(-100).size());
      Assert.assertEquals(length, Word.asStringList(-3).size());
      Assert.assertEquals(length, Word.asStringList(-2).size());
      Assert.assertEquals(length, Word.asStringList(-1).size());
      
      //Zeroes -- zero will result in the full list
      Assert.assertEquals(length, Word.asStringList(-0).size());
      Assert.assertEquals(length, Word.asStringList(0).size());
      
      //Positives below MAX -- will result in the specified amount of elements
      Assert.assertEquals(1, Word.asStringList(1).size());
      Assert.assertEquals(2, Word.asStringList(2).size());
      Assert.assertEquals(3, Word.asStringList(3).size());
      Assert.assertEquals(length - 4, Word.asStringList(length - 4).size());
      Assert.assertEquals(length - 3, Word.asStringList(length - 3).size());
      Assert.assertEquals(length - 2, Word.asStringList(length - 2).size());
      Assert.assertEquals(length - 1, Word.asStringList(length - 1).size());
      
      //MAX and above -- will result in the full list
      Assert.assertEquals(length, Word.asStringList(length).size());
      Assert.assertEquals(length, Word.asStringList(length + 1).size());
      Assert.assertEquals(length, Word.asStringList(length + 2).size());
      Assert.assertEquals(length, Word.asStringList(length + 3).size());
      Assert.assertEquals(length, Word.asStringList(length + 4).size());
      Assert.assertEquals(length, Word.asStringList(Integer.MAX_VALUE - 4).size());
      Assert.assertEquals(length, Word.asStringList(Integer.MAX_VALUE - 3).size());
      Assert.assertEquals(length, Word.asStringList(Integer.MAX_VALUE - 2).size());
      Assert.assertEquals(length, Word.asStringList(Integer.MAX_VALUE - 1).size());
      Assert.assertEquals(length, Word.asStringList(Integer.MAX_VALUE).size());
   
   }

}
