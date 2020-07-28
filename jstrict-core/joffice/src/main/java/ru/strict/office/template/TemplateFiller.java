package ru.strict.office.template;

import net.sf.jooreports.templates.DocumentTemplate;
import net.sf.jooreports.templates.DocumentTemplateFactory;
import org.jxls.util.JxlsHelper;
import ru.strict.utils.FileUtil;
import ru.strict.utils.ResourcesUtil;
import ru.strict.validate.Validator;

import java.io.*;

final class TemplateFiller {

    private final OfficeTemplate template;

    TemplateFiller(OfficeTemplate template) {
        Validator.isNull(template, "template");
        this.template = template;
    }

    void fillTemplate() {
        try {
            FileUtil.recreateFile(template.OUTPUT_TEMPLATE);

            switch (template.templateExtension) {
                case XLS:
                case XLSX:
                    fillExcelTemplate();
                    break;
                case ODS:
                    fillOdsTemplate();
                    break;
                case ODT:
                case DOC:
                case DOCX:
                    fillOdtAndDocTemplate();
                    break;
                default:
                    throw new UnsupportedOperationException(String.format("Unsupported templateExtension [%s]", template.templateExtension));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void fillExcelTemplate() throws Exception {
        try (InputStream templateInput = template.resourceTemplate ?
                ResourcesUtil.getResourceStream(template.templatePath) :
                new FileInputStream(template.templatePath);
             OutputStream templateOutput = new FileOutputStream(new File(template.OUTPUT_TEMPLATE))) {
            JxlsHelper.getInstance().processTemplate(templateInput, templateOutput, template.getContext());
        }
    }

    private void fillOdsTemplate() throws Exception {
        try (InputStream templateInput = template.resourceTemplate ?
                ResourcesUtil.getResourceStream(template.templatePath) :
                new FileInputStream(template.templatePath);
             OutputStream templateOutput = new FileOutputStream(new File(template.OUTPUT_TEMPLATE))) {

        }
    }

    private void fillOdtAndDocTemplate() throws Exception {
        DocumentTemplateFactory documentTemplateFactory = new DocumentTemplateFactory();
        try (InputStream templateInput = template.resourceTemplate ?
                ResourcesUtil.getResourceStream(template.templatePath) :
                new FileInputStream(template.templatePath);
             OutputStream templateOutput = new FileOutputStream(new File(template.OUTPUT_TEMPLATE))) {

            DocumentTemplate documentTemplate = documentTemplateFactory.getTemplate(templateInput);
            documentTemplate.createDocument(template.getItems(), templateOutput);
        }
    }
}
