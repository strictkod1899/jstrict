package ru.strict.file;

public interface IFileWriter<T> {
    void write();
    void write(T source);
}
