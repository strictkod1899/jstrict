package ru.strict.file.properties;

import ru.strict.file.FileProcessingException;
import ru.strict.util.PropertiesUtil;
import ru.strict.util.ResourcesUtil;
import ru.strict.validate.CommonValidate;
import ru.strict.validate.Validator;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class PropertiesFile {
    private final File file;
    private final Path path;
    private final File fileWithSuffix;
    private final Path pathWithSuffix;
    private final Supplier<InputStream> inputStreamSupplier;
    private final Supplier<InputStream> inputStreamWithSuffixSupplier;

    public PropertiesFile(String filePath) {
        this(filePath, null);
    }

    public PropertiesFile(String filePath, String suffix) {
        this.inputStreamSupplier = null;
        this.inputStreamWithSuffixSupplier = null;

        Validator.isNullOrEmpty(filePath, "filePath");

        this.file = new File(filePath);
        this.path = Paths.get(file.getAbsolutePath());

        if (suffix == null) {
            this.fileWithSuffix = null;
            this.pathWithSuffix = null;
        } else {
            var filePathWithSuffix = PropertiesNameUtil.getFilePathWithSuffix(file.getAbsolutePath(), suffix);
            this.fileWithSuffix = new File(filePathWithSuffix);
            this.pathWithSuffix = Paths.get(fileWithSuffix.getAbsolutePath());
        }
    }

    public PropertiesFile(Supplier<InputStream> inputStreamSupplier) {
        this(inputStreamSupplier, null);
    }

    protected PropertiesFile(Supplier<InputStream> inputStreamSupplier,
            Supplier<InputStream> inputStreamWithSuffixSupplier) {
        this.file = null;
        this.path = null;
        this.fileWithSuffix = null;
        this.pathWithSuffix = null;
        this.inputStreamSupplier = inputStreamSupplier;
        this.inputStreamWithSuffixSupplier = inputStreamWithSuffixSupplier;
    }

    public String readValue(String key) {
        return readValue(key, null, null);
    }

    public String readValueToUTF8(String key) {
        return readValue(key, null, "UTF-8");
    }

    public String readValueToUTF8(String key, String fileEncoding) {
        return readValue(key, fileEncoding, "UTF-8");
    }

    public String readValue(String key, String fileEncoding, String targetEncoding) {
        return file == null
                ? readValueByInputStream(key, fileEncoding, targetEncoding)
                : readValueByFile(key, fileEncoding, targetEncoding);
    }

    private String readValueByInputStream(String key, String fileEncoding, String targetEncoding) {
        String value = null;
        if (inputStreamWithSuffixSupplier != null) {
            value = readValueByInputStream(inputStreamWithSuffixSupplier, key, fileEncoding, targetEncoding);
        }
        if (CommonValidate.isNullOrEmpty(value)) {
            value = readValueByInputStream(inputStreamSupplier, key, fileEncoding, targetEncoding);
        }

        return value;
    }

    private String readValueByInputStream(Supplier<InputStream> inputStreamSupplier,
            String key,
            String fileEncoding,
            String targetEncoding) {
        try (var inputStream = inputStreamSupplier.get()) {
            return PropertiesUtil.getValue(inputStream, key, fileEncoding, targetEncoding);
        } catch (Exception ex) {
            throw new FileProcessingException(ex);
        }
    }

    private String readValueByFile(String key, String fileEncoding, String targetEncoding) {
        String value = null;
        if (fileWithSuffix != null && Files.exists(pathWithSuffix)) {
            value = PropertiesUtil.getValue(fileWithSuffix.getAbsolutePath(), key, fileEncoding, targetEncoding);
        }
        if (CommonValidate.isNullOrEmpty(value) && Files.exists(path)) {
            value = PropertiesUtil.getValue(file.getAbsolutePath(), key, fileEncoding, targetEncoding);
        }

        return value;
    }

    public static PropertiesFile fromResource(String resourceFilePath, String suffix) {
        var filePathWithSuffix = PropertiesNameUtil.getResourcePathWithSuffix(resourceFilePath, suffix);

        return new PropertiesFile(() -> ResourcesUtil.getResourceStream(resourceFilePath),
                () -> ResourcesUtil.getResourceStream(filePathWithSuffix));
    }
}
