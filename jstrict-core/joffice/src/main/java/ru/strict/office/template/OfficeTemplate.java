package ru.strict.office.template;

import com.itextpdf.text.*;
import org.apache.commons.io.FilenameUtils;
import org.jxls.common.Context;
import ru.strict.util.ClassUtil;
import ru.strict.util.ResourcesUtil;
import ru.strict.validate.Validator;
import ru.strict.office.template.TemplateConfiguration.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;

/**
 * Заполнить excel документ на основе шаблона. Шаблон описывается с помощью языка выражений разметки Apache JEXL.
 */
public class OfficeTemplate implements ITemplate {

    private final static String FONT_FILE_NAME = "calibri.ttf";
    private final static String FONT_FILE_PATH = ClassUtil.getPathByClass(OfficeTemplate.class) + File.separator + FONT_FILE_NAME;
    private final static File FONT_FILE = ResourcesUtil.getResourceAsFile(FONT_FILE_NAME, FONT_FILE_PATH, OfficeTemplate.class);

    protected final String templatePath;
    protected final TemplateFormat templateExtension;
    protected final String outputDirectoryPath;
    /**
     * Название конечного файла без расширения
     */
    protected final String outputTemplateName;
    protected final TemplateFormat outputTemplateExtension;

    /**
     * Метка: файл шаблона хранится в ресурасах или нет
     */
    protected final boolean resourceTemplate;
    private final Context context;

    protected final String outputTemplate;
    protected final String outputPdf;

    private TemplateConfiguration configuration;

    public OfficeTemplate(String templatePath, String outputTemplatePath) {
        this(templatePath, outputTemplatePath, true);
    }

    public OfficeTemplate(String templatePath, String outputTemplatePath, boolean resourceTemplate) {
        Validator.isEmptyOrNull(templatePath, "templatePath");
        Validator.isEmptyOrNull(outputTemplatePath, "outputPath");

        this.templatePath = templatePath;
        this.templateExtension = TemplateFormat.getByCaption(FilenameUtils.getExtension(templatePath));
        Validator.isNull(templateExtension, "templateExtension");

        this.outputDirectoryPath = Paths.get(outputTemplatePath).getParent().toString();
        this.outputTemplateName = FilenameUtils.getBaseName(outputTemplatePath);
        this.outputTemplateExtension = TemplateFormat.getByCaption(FilenameUtils.getExtension(outputTemplatePath));
        Validator.isNull(outputTemplateExtension, "outputTemplateExtension");

        this.resourceTemplate = resourceTemplate;
        this.context = new Context();
        this.configuration = createDefaultConfiguration();

        this.outputTemplate = outputDirectoryPath + File.separator + outputTemplateName + "." + outputTemplateExtension.getCaption();
        this.outputPdf = outputDirectoryPath + File.separator + outputTemplateName + ".pdf";
    }

    private TemplateConfiguration createDefaultConfiguration() {
        TemplateConfiguration configuration = new TemplateConfiguration();
        configuration.setMarginLeft(100f);
        configuration.setMarginRight(100f);
        configuration.setMarginTop(100f);
        configuration.setMarginBottom(100f);

        configuration.setFontFilePath(FONT_FILE_PATH);
        configuration.setFontSize(0);
        configuration.setFontWeight(1f);
        configuration.setFontStyle(Font.UNDEFINED);
        configuration.setFontColor(null);
        configuration.setEncoding("cp1251");

        configuration.setGetSheetType(GetSheetType.AUTO);
        configuration.setUsageSheetsCount(1);
        configuration.setBorderConfigType(BorderConfigType.AUTO);
        configuration.setBorderWidth(0f);
        configuration.setWidthPercentage(100f);
        configuration.setMinPadding(5);

        return configuration;
    }

    @Override
    public TemplateConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public void addItem(String name, Object item) {
        Validator.isNull(item, "template item");
        context.putVar(name, item);
    }

    @Override
    public void removeItem(String name) {
        if (name != null) {
            context.removeVar(name);
        }
    }

    @Override
    public <T> T getItem(String name) {
        return (T) context.getVar(name);
    }

    @Override
    public Map<String, Object> getItems() {
        return context.toMap();
    }

    @Override
    public void fillTemplate() {
        TemplateFiller templateFiller = new TemplateFiller(this);
        templateFiller.fillTemplate();
    }

    @Override
    public void toPdf() {
        fillTemplate();

        PdfConverter converter = new PdfConverter(this);
        converter.toPdf();
    }

    Context getContext() {
        return context;
    }
}
