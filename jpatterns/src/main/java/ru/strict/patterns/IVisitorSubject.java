package ru.strict.patterns;

public interface IVisitorSubject<RESULT> {
    RESULT acceptVisitor(IVisitor<RESULT> visitor);
}
