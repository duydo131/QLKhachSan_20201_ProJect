package generate.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import generate.Ngay;
import generate.Total;
import model.add.SalesStatistical;

public class ThongKeDoanhThuXML {
	public static void main(String[] args) throws XDocReportException,IOException {
	     
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        fieldsMetadata.load("ngay", Ngay.class);
        fieldsMetadata.load("dt", SalesStatistical.class, true);
        fieldsMetadata.load("t", Total.class);
         
        File xmlFieldsFile = new File("field/ThongKeDoanhThu/ThongKeDoanhThu.fields.xml");
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
