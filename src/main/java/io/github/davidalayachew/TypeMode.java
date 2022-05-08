package io.github.davidalayachew;

/** Enum to represent the different ways to type. */
public sealed interface TypeMode permits Terminal, GUI
{

   /** Method to handle playing the game loop. */
   public void play();

}