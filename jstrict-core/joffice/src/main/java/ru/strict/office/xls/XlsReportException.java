package ru.strict.office.xls;

public class XlsReportException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "An error occurred at generate xls-report [%s] - %s";

    public XlsReportException(String templateName, Throwable cause) {
        super(String.format(MESSAGE_TEMPLATE, templateName, cause.getMessage()), cause);
    }

    public XlsReportException(String templateName, String message) {
        super(String.format(MESSAGE_TEMPLATE, templateName, message));
    }
}
