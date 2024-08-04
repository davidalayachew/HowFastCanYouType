
package HowFastCanYouTypePackage;

public sealed interface TextAction
   permits
      CorrectInsertTextAction,
      IncorrectInsertTextAction,
      RemoveTextAction
{

   long epochTimestampInMilliseconds();

}