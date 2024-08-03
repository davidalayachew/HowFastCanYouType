
package HowFastCanYouTypePackage;

import javax.swing.*;
import java.awt.GridLayout;
import java.util.*;

public class GUI
{

   private static final String textToType = "The FitnessGram Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal. A single lap should be completed each time you hear this sound. Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start.";

   private final List<KeyPress> keyPresses = new ArrayList<>();

   private GUI()
   {
   
      final JFrame frame = new JFrame();
      
      frame.setTitle("How Fast Can You Type");
      frame.setLocationByPlatform(true);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      final JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new GridLayout(0, 1));
      
      final JTextArea prompt = new JTextArea();
      prompt.setText(textToType);
      prompt.setLineWrap(true);
      prompt.setBackground(java.awt.Color.CYAN);
      prompt.setEnabled(false);
      // prompt.setForeground(java.awt.Color.BLACK);
      prompt.setPreferredSize(new java.awt.Dimension(450, 150));
      
      final JTextArea typingArea = new JTextArea();
      typingArea.setPreferredSize(new java.awt.Dimension(450, 150));
      
      typingArea.getDocument().addDocumentListener(
         new javax.swing.event.DocumentListener()
         {
         
            @Override public void changedUpdate(javax.swing.event.DocumentEvent e) {throw new IllegalStateException("how did this occur? DocumentEvent = " + e);}
            @Override public void insertUpdate(javax.swing.event.DocumentEvent e) {this.keyPresses.add()}
            @Override public void removeUpdate(javax.swing.event.DocumentEvent e) {System.out.println(e);}
            
         })
         ;
      
      mainPanel.add(prompt);
      mainPanel.add(typingArea);
      
      frame.add(mainPanel);
      
      frame.pack();
      frame.setVisible(true);
   
   }
   
   public static void start()
   {
   
      final GUI gui = new GUI();
   
   }

}
