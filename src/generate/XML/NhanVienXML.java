package generate.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import generate.Ngay;
import model.add.Staff;

public class NhanVienXML {
	public static void main(String[] args) throws XDocReportException,IOException {
	     
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        fieldsMetadata.load("ngay", Ngay.class);
        fieldsMetadata.load("nv", Staff.class, true);
         
        File xmlFieldsFile = new File("field/NhanVien/NhanVien.fields.xml");
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
