package ru.strict.office.jxls;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ReportData {
    @NonNull
    private final Map<String, Object> data;

    public ReportData() {
        this.data = new HashMap<>();
    }

    public ReportData(@NonNull Map<String, Object> data) {
        this.data = data;
    }

    public void addItem(@NonNull String name, Object item) {
        data.put(name, item);
    }

    public void addItemFromReport(@NonNull String name, @NonNull ReportData reportData) {
        data.put(name, reportData.getData());
    }

    public void addItems(@NonNull Map<String, Object> items) {
        data.putAll(items);
    }

    public void addItemsFromReport(@NonNull ReportData reportData) {
        data.putAll(reportData.getData());
    }

    public <T> T getItem(String name) {
        return (T) data.get(name);
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
}
