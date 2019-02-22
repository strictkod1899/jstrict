package ru.strict.file.json;

import ru.strict.file.IFileReader;

import java.util.List;
import java.util.Map;

public interface IJsonFile<TARGET> extends IFileReader<List<Map<String, Object>>> {
    void loadFromFileOrInitialize();
    TARGET readToTargetClass();
    void write(TARGET object);
}
