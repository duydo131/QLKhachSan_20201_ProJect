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
import generate.Ngay;
import generate.Total;
import model.add.SalesStatistical;

public class ThongKeDoanhThuDocx extends AbstractGenerateDocx<SalesStatistical> implements GenerateDocx{
	
	private Ngay ngay;
	private List<SalesStatistical> list;
	private Total total;
	
	public ThongKeDoanhThuDocx(Date date, List<SalesStatistical> list, Total total) {
		this.list = addNo(list);
		ngay = new Ngay(date);
		this.total = total;
	}

	@Override
	public String generateDocx() {
		InputStream in;
		try {
			in = new FileInputStream("template/ThongKeDoanhThuTemplate.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
			 
	        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
	        fieldsMetadata.load("ngay", Ngay.class);
	        fieldsMetadata.load("dt", SalesStatistical.class, true);
	        fieldsMetadata.load("t", Total.class);
	        
	        IContext context = report.createContext();
	        context.put("ngay", ngay);
	        context.put("dt", list);
	        context.put("t", total);
	        
	        String file = "source/ThongKeDoanhThu/ThongKeDoanhThu_" + format.format(new Date()) + ".docx";
	        
	        OutputStream out = new FileOutputStream(new File(file));
	        report.process(context, out);
	        return file;
		} catch (IOException | XDocReportException e) {
			e.printStackTrace();
			return "";
		}
	}

}
