package ru.strict.patterns.interpreter;

public interface IInterpreterContext {
    String readLine();
    boolean isReadyForRead();
    String getCurrentLine();
}
