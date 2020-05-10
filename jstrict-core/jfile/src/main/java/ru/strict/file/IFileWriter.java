package ru.strict.file;

public interface IFileWriter<SOURCE> {
    void write();

    void write(SOURCE source);
}
