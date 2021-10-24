package ru.strict.office;

import org.junit.jupiter.api.Assertions;
import ru.strict.office.template.OfficeTemplate;
import ru.strict.office.template.TemplateConfiguration;
import ru.strict.util.ClassUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OfficeTemplateTest {

    @org.junit.jupiter.api.Test
    public void test() {
        String templateXlsName = "template.xls";
        String templateXlsxName = "template.xlsx";

        String pdfName = "template.pdf";

        String directoryPath = ClassUtil.getPathByClass(OfficeTemplateTest.class);
        String templateXlsDirectoryPath = directoryPath + File.separator + "xls" + File.separator;
        String templateXlsxDirectoryPath = directoryPath + File.separator + "xlsx" + File.separator;

        String outputTemplateXlsPath = templateXlsDirectoryPath + templateXlsName;
        String outputTemplateXlsxPath = templateXlsxDirectoryPath + templateXlsxName;
        String outputXlsPdfPath = templateXlsDirectoryPath + pdfName;
        String outputXlsxPdfPath = templateXlsxDirectoryPath + pdfName;

        OfficeTemplate templateXls = new OfficeTemplate(templateXlsName, outputTemplateXlsPath, true);
        OfficeTemplate templateXlsx = new OfficeTemplate(templateXlsxName, outputTemplateXlsxPath, true);
        templateXls.getConfiguration().setBorderWidth(1);
        templateXls.getConfiguration().setBorderConfigType(TemplateConfiguration.BorderConfigType.FIX);
        templateXls.getConfiguration().setFontWeight(7);

        List<TestModel> tests = new ArrayList<>();
        tests.add(new TestModel("name1name1name1name1name1name1name1name1name1name1name1name1name1name1name1name1name1", 1, "description1"));
        tests.add(new TestModel("name2", 2, "description2"));
        tests.add(new TestModel("name3", 3, "description3"));
        templateXls.addItem("tests", tests);
        templateXlsx.addItem("tests", tests);

        templateXls.toPdf();
        templateXlsx.toPdf();

        Assertions.assertTrue(Files.exists(Paths.get(outputTemplateXlsPath)));
        Assertions.assertTrue(Files.exists(Paths.get(outputXlsPdfPath)));
        Assertions.assertTrue(Files.exists(Paths.get(outputTemplateXlsxPath)));
        Assertions.assertTrue(Files.exists(Paths.get(outputXlsxPdfPath)));
    }
}
