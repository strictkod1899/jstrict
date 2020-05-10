package ru.strict.file.json;

import ru.strict.file.IFileReader;
import ru.strict.file.IFileWriter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface IJsonFile<TARGET> extends IFileReader<Object>, IFileWriter<TARGET> {
    void loadFromFileOrInitialize();

    TARGET readToTargetClass();
}
