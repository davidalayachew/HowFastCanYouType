
package HowFastCanYouTypePackage;

public record RemoveTextAction(int numOfCharactersRemoved, long epochTimestampInMilliseconds) implements TextAction {}
