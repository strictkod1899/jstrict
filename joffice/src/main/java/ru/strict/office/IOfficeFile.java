package ru.strict.office;

public interface IOfficeFile<SOURCE> {
    SOURCE getSource();
    void recreateFile();
    void write();
}
