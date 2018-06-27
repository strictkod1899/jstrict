package ru.strict.patterns;

public interface IVisitor<RESULT> {
    RESULT visit(IVisitorSubject<RESULT> subject);
}
