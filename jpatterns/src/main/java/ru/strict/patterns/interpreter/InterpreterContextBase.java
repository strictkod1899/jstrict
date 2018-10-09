package ru.strict.patterns.interpreter;

import java.io.*;

public class InterpreterContextBase implements IInterpreterContext, AutoCloseable {

    private BufferedReader reader;
    private File fileForRead;
    private String currentLine;

    public InterpreterContextBase(File fileForRead) {
        this.fileForRead = fileForRead;
        this.currentLine = "";
        try {
            reader = new BufferedReader(new FileReader(fileForRead));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String readLine(){
        String line = null;
        try {
            if(reader.ready()) {
                line = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        currentLine = line;
        return line;
    }

    @Override
    public boolean isReadyForRead(){
        try {
            return reader.ready();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentLine() {
        return currentLine;
    }

    protected BufferedReader getReader() {
        return reader;
    }

    public File getFileForRead() {
        return fileForRead;
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        fileForRead = null;
        currentLine = null;
    }
}
