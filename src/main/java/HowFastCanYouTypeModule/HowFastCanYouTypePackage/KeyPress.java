
package HowFastCanYouTypePackage;


public sealed interface KeyPress
   permits
      CorrectKeyPress,
      IncorrectKeyPress,
      Backspace
{

   long epochTimestampInMilliseconds();

}