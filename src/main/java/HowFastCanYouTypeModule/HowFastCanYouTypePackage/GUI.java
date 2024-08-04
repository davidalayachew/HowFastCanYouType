
package HowFastCanYouTypePackage;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.GridLayout;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class GUI
{

   private static final String textToType = "The FitnessGram Pacer Test is a multistage aerobic capacity test that progressively gets more difficult as it continues. The 20 meter pacer test will begin in 30 seconds. Line up at the start. The running speed starts slowly, but gets faster each minute after you hear this signal. A single lap should be completed each time you hear this sound. Remember to run in a straight line, and run as long as possible. The second time you fail to complete a lap before the sound, your test is over. The test will begin on the word start. On your mark, get ready, start.";

   private final List<TextAction> textActions = new ArrayList<>();

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
      prompt.setWrapStyleWord(true);
      prompt.setLineWrap(true);
      prompt.setOpaque(false);
      prompt.setEditable(false);
      prompt.setFocusable(false);
      prompt.setBackground(UIManager.getColor("Label.background"));
      prompt.setFont(UIManager.getFont("Label.font"));
      prompt.setBorder(UIManager.getBorder("Label.border"));
   
   
      final JTextArea typingArea = new JTextArea();
      typingArea.setLineWrap(true);
      typingArea.setWrapStyleWord(true);
      typingArea.setPreferredSize(new java.awt.Dimension(450, 150));
   
      final GUI self = this;
   
      typingArea
         .getDocument()
         .addDocumentListener
         (
            new DocumentListener()
            {
            
               @Override public void changedUpdate(final DocumentEvent e) {throw new IllegalStateException("how did this occur? DocumentEvent = " + e);}
               @Override public void insertUpdate(final DocumentEvent e) {this.handleDocumentEvent(e);}
               @Override public void removeUpdate(final DocumentEvent e) {this.handleDocumentEvent(e);}
               private void handleDocumentEvent(final DocumentEvent e)
               {
               
                  System.out.println("");
                  System.out.println(e.getDocument());
                  System.out.println(e.getLength());
                  System.out.println(e.getOffset());
                  System.out.println(e.getType());
               
                  final TextAction textAction;
               
                  if (DocumentEvent.EventType.INSERT.equals(e.getType()) && 1 == e.getLength())
                  {
                  
                     try
                     {
                     
                        final String expected = GUI.textToType;
                        final String actual = e.getDocument().getText(0, e.getDocument().getLength());
                     
                        final char textInserted = actual.charAt(e.getOffset());
                     
                        if (expected.startsWith(actual))
                        {
                        
                           textAction = new CorrectInsertTextAction(textInserted, System.currentTimeMillis());
                        
                        }
                        
                        else
                        {
                        
                           textAction = new IncorrectInsertTextAction(textInserted, System.currentTimeMillis());
                        
                        }
                     
                     }
                     
                     catch (final BadLocationException exception)
                     {
                     
                        throw new IllegalStateException("How did this happen?", exception);
                     
                     }
                  
                  }
                  
                  else if (DocumentEvent.EventType.REMOVE.equals(e.getType()))
                  {
                  
                     textAction = new RemoveTextAction(e.getLength(), System.currentTimeMillis());
                  
                  }
                  
                  else
                  {
                  
                     throw new IllegalStateException("Make a better error message, so as to debug this better.");
                  
                  }
               
                  self.textActions.add(textAction);
               
               }
            
            }
         )
         ;
   
      mainPanel.add(prompt);
      mainPanel.add(typingArea);
   
      final JButton button = new JButton();
      button.addActionListener(_ -> analyze(List.copyOf(this.textActions), typingArea.getText()));
   
      mainPanel.add(button);
   
      frame.add(mainPanel);
   
      frame.pack();
      frame.setVisible(true);
   
   }

   public static void start()
   {
   
      final GUI gui = new GUI();
   
   }

   private static void analyze(final List<TextAction> textActions, final String textThusFar)
   {
   
      if (textActions.isEmpty())
      {
      
         System.out.println("No statistics have been gathered yet.");
      
         return;
      
      }
   
      WORDS_PER_MINUTE:
      {
      
         final String prefix = "Words Per Minute = ";
      
         final String output;
         
         final long start = textActions.getFirst().epochTimestampInMilliseconds();
         final long end = textActions.getLast().epochTimestampInMilliseconds();
         
         final List<List<Integer>> words =
            textThusFar
               .chars()
               .boxed()
               .dropWhile(Predicate.not(Character::isLetter))
               .gather(windowBy(Character::isLetter))
               .toList()
               ;
         
         if (words.isEmpty())
         {
         
            output = "No words, so cannot calculate";
         
         }
         
         else if (textActions.size() == 1)
         {
         
            output = "Only did one key press -- need more data";
         
         }
         
         else
         {
         
            final double result = ((double) (words.size() * 60 * 1000)) / ((double) (end - start));
            
            output = result + " words per minute";
         
         }
         
         System.out.println(prefix + output);
         
      }
   
      LONGEST_SUCCESS_STREAK:
      {
      
         final OptionalInt value =
            textActions
               .stream()
               .gather(windowBy(CorrectInsertTextAction.class::isInstance))
               .filter(list -> list.getLast() instanceof CorrectInsertTextAction)
               .mapToInt(List::size)
               .max()
               ;
      
         final String output =
            value.isPresent()
               ? String.valueOf(value.getAsInt())
               : "No Successes thus far"
               ;
      
         System.out.println("Longest Success Streak = " + output);
      
      }
   
      MEAN_TIME_TO_FAILURE:
      {
      
         final String prefix = "Mean Time to Failure = ";
      
         final String output;
      
         if (textActions.size() == 1)
         {
         
            output = "Only 1 Text Action gathered -- Not enough info";
         
         }
         
         else
         {
         
            final List<List<TextAction>> successWindows =
               textActions
                  .stream()
                  .gather(windowBy(CorrectInsertTextAction.class::isInstance))
                  .dropWhile(eachList -> !(eachList.getLast() instanceof CorrectInsertTextAction))
                  .toList()
                  ;
         
            if (successWindows.size() <= 1)
            {
            
               output = "Can't calculate until there is at least one failure AFTER a success";
            
            }
            
            else
            {
            
               record Pair(long first, long second)
               {
               
                  static Optional<Pair> of(List<List<TextAction>> slidingWindow)
                  {
                  
                     //Assuming that slidingWindow has a size of 2
                  
                     final List<TextAction> first = slidingWindow.get(0);
                     final List<TextAction> second = slidingWindow.get(1);
                  
                     if (first.size() == 1 && !(first.getFirst() instanceof CorrectInsertTextAction))
                     {
                     
                        return Optional.empty();
                     
                     }
                  
                     final TextAction firstValue =
                        first.get(0) instanceof CorrectInsertTextAction cita
                           ? cita
                           : first.get(1)
                           ;
                  
                     final TextAction secondValue = second.getFirst();
                  
                     return Optional.of(new Pair(firstValue.epochTimestampInMilliseconds(), secondValue.epochTimestampInMilliseconds()));
                  
                  }
               
               }
            
               final double result =
                  successWindows
                     .stream()
                     .gather(Gatherers.windowSliding(2))
                     .map(Pair::of)
                     .filter(Optional::isPresent)
                     .map(Optional::get)
                     .mapToLong(eachPair -> eachPair.second() - eachPair.first())
                     .average()
                     .orElseThrow()
                     ;
            
               output = String.valueOf(result) + "ms";
            
            }
         
         }
      
         System.out.println(prefix + output);
      
      }
   
      SUCCESS_RATIO:
      {
      
      }
   
   }

   private static <TR> Gatherer<TR, ?, List<TR>> windowBy(Predicate<TR> includeInCurrentWindow) {
      class State {
         ArrayList<TR> window;
      
         boolean integrate(TR element, Gatherer.Downstream<? super List<TR>> downstream) {
            if (window != null && !includeInCurrentWindow.test(element)) {
               var result = Collections.unmodifiableList(window);
               window = null;
               if (!downstream.push(result))
                  return false;
            }
         
            if (window == null)
               window = new ArrayList<>();
         
            return window.add(element);
         }
      
         void finish(Gatherer.Downstream<? super List<TR>> downstream) {
            if (window != null) {
               var result = Collections.unmodifiableList(window);
               window = null;
               downstream.push(result);
            }
         }
      }
      return Gatherer.<TR, State, List<TR>>ofSequential(State::new, State::integrate, State::finish);
   }

}
