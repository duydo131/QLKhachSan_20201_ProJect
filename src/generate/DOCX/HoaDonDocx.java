package generate.DOCX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import generate.InfoCustomer;
import generate.InfoPay;
import generate.Ngay;

public class HoaDonDocx extends AbstractGenerateDocx<InfoPay> implements GenerateDocx{
	
	private Ngay ngay;
	private List<InfoPay> list;
	private InfoCustomer info;
	
	public HoaDonDocx(Date date, List<InfoPay> list, InfoCustomer info) {
		this.list = addNo(list);
		ngay = new Ngay(date);
		this.info = info;
	}

	@Override
	public String generateDocx() {
		InputStream in;
		try {
			in = new FileInputStream("template/HoaDonTemplate.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
			 
	        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
	        fieldsMetadata.load("ng", Ngay.class);
	        fieldsMetadata.load("p", InfoPay.class, true);
	        fieldsMetadata.load("in", InfoCustomer.class);
	        
	        IContext context = report.createContext();
	        context.put("ng", ngay);
	        context.put("p", list);
	        context.put("in", info);
	        
	        String file = "source/HoaDon/HoaDon_" + format.format(new Date()) + ".docx";
	        
	        OutputStream out = new FileOutputStream(new File(file));
	        report.process(context, out);
	        return file;
		} catch (IOException | XDocReportException e) {
			e.printStackTrace();
			return "";
		}
	}

}
