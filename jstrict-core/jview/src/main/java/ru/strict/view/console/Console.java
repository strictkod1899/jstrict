package ru.strict.view.console;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import ru.strict.util.AnsiColor;
import ru.strict.util.AnsiConsoleUtil;
import ru.strict.validate.CommonValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class Console {

    private static final String DEFAULT_CANCEL_VALUE = "--n";

    @Getter
    @Setter
    private String cancelValue;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String currentMessage;

    @Getter(AccessLevel.PROTECTED)
    private BufferedReader cmdInput;

    public Console() {
        this(null);
    }

    public Console(String cmdEncoding) {
        try {
            this.cmdInput = new BufferedReader(new InputStreamReader(
                    System.in,
                    Optional.ofNullable(cmdEncoding).orElse(Charset.defaultCharset().name())));

            currentMessage = "";
            cancelValue = DEFAULT_CANCEL_VALUE;
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void showMessage(String messageFormat, Object...args) {
        showMessage(String.format(messageFormat, args));
    }

    public void showMessage(String message) {
        currentMessage = message;
        AnsiConsoleUtil.print(message);
    }

    public void showWarning(String messageFormat, Object...args) {
        showWarning(String.format(messageFormat, args));
    }

    public void showWarning(String message) {
        AnsiConsoleUtil.println(message, AnsiColor.YELLOW_BOLD);
    }

    public void showError(String messageFormat, Object...args) {
        showError(String.format(messageFormat, args));
    }

    public void showError(String message) {
        AnsiConsoleUtil.println(message, AnsiColor.RED_BOLD);
    }

    public Integer inputInteger(String message) {
        showMessage(message);

        String command = null;
        try {
            command = getCmdInput().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer result = null;
        if (CommonValidator.isInteger(command)) {
            result = Integer.parseInt(command);
        }
        return result;
    }

    public String inputString(String message) {
        showMessage(message);

        String command = null;
        try {
            command = getCmdInput().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = null;
        if (!CommonValidator.isNullOrEmpty(command)) {
            result = command;
        }
        return result;
    }

    public Integer inputCommand(String message, int minValue, int maxValues) {
        Collection<Integer> correctValues = new ArrayList<>();
        for (int i = minValue; i <= maxValues; i++) {
            correctValues.add(i);
        }

        return inputCommand(message, Integer.class, correctValues);
    }

    public <RESULT> RESULT inputCommand(String message, Class<RESULT> inputType, Collection<RESULT> correctValues) {
        return inputCommand(message, inputType, correctValues, cancelValue);
    }

    public <RESULT> RESULT inputCommand(String message,
            Class<RESULT> inputType,
            Collection<RESULT> correctValues,
            String defaultCancelValue) {
        boolean isCorrectValue = false;
        RESULT command = null;

        do {
            Object inputtedCommand = inputString(message);

            if (String.valueOf(inputtedCommand).equals(defaultCancelValue)) {
                command = null;
                break;
            }

            if (inputType == Integer.class) {
                if (CommonValidator.isInteger(String.valueOf(inputtedCommand))) {
                    inputtedCommand = Integer.valueOf(String.valueOf(inputtedCommand));
                }
            }

            if (inputtedCommand != null && correctValues.contains(inputtedCommand)) {
                command = (RESULT) inputtedCommand;
                isCorrectValue = true;
            } else {
                showError("Ошибка: Данная команда не найдена");
            }
        } while (!isCorrectValue);

        return command;
    }
}
