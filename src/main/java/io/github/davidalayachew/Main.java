package io.github.davidalayachew;

import javax.swing.JOptionPane;

/** Main class. */
public class Main
{

	/**
	 *
	 * Main method.
	 *
	 * @param	args	commandline arguments, should they be needed.
	 *
	 */
   public static void main(String[] args)
   {
   
      enum CMDOptions { GUI, CMD, ; }
      
      for (String each : args)
      {
      
         System.out.println(each);
      
      }
   
      switch(JOptionPane.showConfirmDialog(null, "Press YES for GUI Version, NO for Terminal Version."))
      {
         
         case JOptionPane.YES_OPTION   -> new GUI().play();
         case JOptionPane.NO_OPTION    -> new Terminal().play();
         default                       -> JOptionPane.showMessageDialog(null, "Ending the program.");
         
      }
   
   }

}
