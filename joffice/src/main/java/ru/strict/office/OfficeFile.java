package ru.strict.office;

import ru.strict.utils.UtilFile;
import ru.strict.validate.ValidateBaseValue;

public abstract class OfficeFile<SOURCE, FORMAT extends IOfficeFormat>
        implements IOfficeFile<SOURCE>, AutoCloseable {

    protected SOURCE source;

    private String filePath;
    private FORMAT format;

    public OfficeFile(String filePath) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new IllegalArgumentException("filePath is NULL");
        }
        format = getFormatByCaption(UtilFile.getFileExtension(filePath));
        if(!filePath.endsWith(format.getCaption())){
            filePath = String.format("%s.%s", filePath, format.getCaption());
        }
        this.filePath = filePath;
        source = initializeSource();
    }

    public OfficeFile(String filePath, FORMAT format) {
        if(ValidateBaseValue.isEmptyOrNull(filePath)){
            throw new IllegalArgumentException("filePath is NULL");
        }
        if(format == null){
            throw new IllegalArgumentException("format is NULL");
        }
        if(!filePath.endsWith(format.getCaption())){
            filePath += "." + format.getCaption();
        }

        this.filePath = filePath;
        this.format = format;
        source = initializeSource();
    }

    protected abstract SOURCE initializeSource();
    protected abstract FORMAT getFormatByCaption(String format);

    @Override
    public SOURCE getSource() {
        return source;
    }

    public String getFilePath() {
        return filePath;
    }

    public FORMAT getFormat() {
        return format;
    }
}
