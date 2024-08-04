
package HowFastCanYouTypePackage;

public record IncorrectInsertTextAction(char characterTyped, long epochTimestampInMilliseconds) implements TextAction {}
