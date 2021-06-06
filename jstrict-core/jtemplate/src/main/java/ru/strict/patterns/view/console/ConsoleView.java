package ru.strict.patterns.view.console;

import ru.strict.components.AnsiColor;
import ru.strict.utils.AnsiConsoleUtil;
import ru.strict.validate.CommonValidate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class ConsoleView<M> extends BaseView<M> implements IConsoleView {

    private String defaultCancelValue;

    private String currentMessage;
    private BufferedReader cmdInput;

    private void init(){
        currentMessage = "";
        defaultCancelValue = "--n";
    }

    public ConsoleView(M model) {
        super(model);
        this.cmdInput = new BufferedReader(new InputStreamReader(System.in));
        init();
    }

    public ConsoleView(M model, String cmdEncoding) {
        super(model);
        try {
            this.cmdInput = new BufferedReader(
                    new InputStreamReader(
                            System.in,
                            Optional.ofNullable(cmdEncoding).orElse(Charset.defaultCharset().name())
                    )
            );
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        init();
    }

    @Override
    public void showMessage(String message){
        currentMessage = message;
        AnsiConsoleUtil.print(message);
    }

    @Override
    public void showWarning(String message) {
        AnsiConsoleUtil.println(message, AnsiColor.YELLOW_BOLD);
    }

    @Override
    public void showError(String message){
        AnsiConsoleUtil.println(message, AnsiColor.RED_BOLD);
    }

    @Override
    public Integer inputInteger(String message){
        showMessage(message);

        String command = null;
        try {
            command = getCmdInput().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer result = null;
        if(CommonValidate.isInteger(command)) {
            result = Integer.parseInt(command);
        }
        return result;
    }

    @Override
    public String inputString(String message){
        showMessage(message);

        String command = null;
        try {
            command = getCmdInput().readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String result = null;
        if(!CommonValidate.isEmptyOrNull(command)) {
            result = command;
        }
        return result;
    }

    @Override
    public Integer inputCommand(String message, int minValue, int maxValues){
        Collection<Integer> correctValues = new ArrayList<>();
        for (int i = minValue; i <= maxValues; i++){
            correctValues.add(i);
        }

        return inputCommand(message, Integer.class, correctValues);
    }

    @Override
    public <RESULT> RESULT inputCommand(String message, Class<RESULT> inputType, Collection<RESULT> correctValues){
        return inputCommand(message, inputType, correctValues, defaultCancelValue);
    }

    @Override
    public <RESULT> RESULT inputCommand(String message, Class<RESULT> inputType, Collection<RESULT> correctValues, String defaultCancelValue){
        boolean isCorrectValue = false;
        RESULT command = null;

        do{
            Object inputtedCommand = inputString(message);

            if (String.valueOf(inputtedCommand).equals(defaultCancelValue)){
                command = null;
                break;
            }

            if(inputType == Integer.class) {
                if(CommonValidate.isInteger(String.valueOf(inputtedCommand))) {
                    inputtedCommand = Integer.valueOf(String.valueOf(inputtedCommand));
                }
            }

            if(inputtedCommand != null && correctValues.contains(inputtedCommand)){
                command = (RESULT) inputtedCommand;
                isCorrectValue = true;
            } else{
                showError("Ошибка: Данная команда не найдена");
            }
        }while(!isCorrectValue);

        return command;
    }

    @Override
    public String getCurrentMessage() {
        return currentMessage;
    }

    @Override
    public void destroy() {
        currentMessage = null;
        cmdInput = null;
        super.destroy();
    }

    protected BufferedReader getCmdInput() {
        return cmdInput;
    }

    protected void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public String getDefaultCancelValue() {
        return defaultCancelValue;
    }

    public void setDefaultCancelValue(String defaultCancelValue) {
        this.defaultCancelValue = defaultCancelValue;
    }
}
