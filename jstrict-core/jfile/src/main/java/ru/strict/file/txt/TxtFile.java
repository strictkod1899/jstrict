package ru.strict.file.txt;

public class TxtFile extends BaseTxtFile<String> {

    public TxtFile(String filePath) {
        super(filePath);
    }

    @Override
    protected String mapToSource(StringBuilder fileContent) {
        return fileContent == null ? "" : fileContent.toString();
    }

    @Override
    protected StringBuilder mapToString(String source) {
        return source == null ? new StringBuilder() : new StringBuilder(source);
    }
}
