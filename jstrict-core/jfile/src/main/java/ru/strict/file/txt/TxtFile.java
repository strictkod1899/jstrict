package ru.strict.file.txt;

public class TxtFile extends BaseTxtFile<String> {

    public TxtFile(String filePath) {
        super(filePath);
    }

    @Override
    protected String mapToSource(String fileContent) {
        return fileContent;
    }

    @Override
    protected String mapToString(String source) {
        return source;
    }
}
