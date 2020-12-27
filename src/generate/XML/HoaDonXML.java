package generate.XML;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import generate.InfoCustomer;
import generate.InfoPay;
import generate.Ngay;

public class HoaDonXML {
	public static void main(String[] args) throws XDocReportException,IOException {
	     
        FieldsMetadata fieldsMetadata = new FieldsMetadata(TemplateEngineKind.Velocity.name());

        fieldsMetadata.load("ng", Ngay.class);
        fieldsMetadata.load("p", InfoPay.class, true);
        fieldsMetadata.load("in", InfoCustomer.class);
         
        File xmlFieldsFile = new File("field/HoaDon/HoaDon.fields.xml");
        fieldsMetadata.saveXML(new FileOutputStream(xmlFieldsFile), true);
    }
}
