package ru.strict.file.archive;

public class ExtractArchiveException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Can not extract archive - %s. Item = %s. Reason = %s";

    public ExtractArchiveException(String archiveFilePath, String item, String reason) {
        super(String.format(MESSAGE_TEMPLATE, archiveFilePath, item, reason));
    }
}
