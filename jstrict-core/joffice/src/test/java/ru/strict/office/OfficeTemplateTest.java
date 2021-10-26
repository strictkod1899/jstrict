package ru.strict.office;

import org.junit.jupiter.api.Test;
import ru.strict.office.template.OfficeTemplate;
import ru.strict.office.template.TemplateConfiguration;
import ru.strict.util.ClassUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OfficeTemplateTest {

    @Test
    void test() {
        var templateXlsName = "template.xls";
        var templateXlsxName = "template.xlsx";

        var pdfName = "template.pdf";

        var directoryPath = ClassUtil.getPathByClass(OfficeTemplateTest.class);
        var templateXlsDirectoryPath = directoryPath + File.separator + "xls" + File.separator;
        var templateXlsxDirectoryPath = directoryPath + File.separator + "xlsx" + File.separator;

        var outputTemplateXlsPath = templateXlsDirectoryPath + templateXlsName;
        var outputTemplateXlsxPath = templateXlsxDirectoryPath + templateXlsxName;
        var outputXlsPdfPath = templateXlsDirectoryPath + pdfName;
        var outputXlsxPdfPath = templateXlsxDirectoryPath + pdfName;

        var templateXls = new OfficeTemplate(templateXlsName, outputTemplateXlsPath, true);
        var templateXlsx = new OfficeTemplate(templateXlsxName, outputTemplateXlsxPath, true);
        templateXls.getConfiguration().setBorderWidth(1);
        templateXls.getConfiguration().setBorderConfigType(TemplateConfiguration.BorderConfigType.FIX);
        templateXls.getConfiguration().setFontWeight(7);

        var testsModels = new ArrayList<>();
        testsModels.add(new TestModel("name1name1name1name1name1name1name1name1name1name1name1name1name1name1name1name1name1", 1, "description1"));
        testsModels.add(new TestModel("name2", 2, "description2"));
        testsModels.add(new TestModel("name3", 3, "description3"));
        templateXls.addItem("tests", testsModels);
        templateXlsx.addItem("tests", testsModels);

        templateXls.toPdf();
        templateXlsx.toPdf();

        assertTrue(Files.exists(Paths.get(outputTemplateXlsPath)));
        assertTrue(Files.exists(Paths.get(outputXlsPdfPath)));
        assertTrue(Files.exists(Paths.get(outputTemplateXlsxPath)));
        assertTrue(Files.exists(Paths.get(outputXlsxPdfPath)));
    }
}
