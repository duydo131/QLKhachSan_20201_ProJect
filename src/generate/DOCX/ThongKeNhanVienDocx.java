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
import model.add.StaffStatisticalModel;

public class ThongKeNhanVienDocx extends AbstractGenerateDocx<StaffStatisticalModel> implements GenerateDocx{
	
	private Ngay ngay;
	private List<StaffStatisticalModel> list;
	
	public ThongKeNhanVienDocx(Date date, List<StaffStatisticalModel> list) {
		this.list = addNo(list);
		ngay = new Ngay(date);
	}

	@Override
	public String generateDocx() {
		InputStream in;
		try {
			in = new FileInputStream("template/ThongKeNhanVienTemplate.docx");
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
			 
	        FieldsMetadata fieldsMetadata = report.createFieldsMetadata();
	        fieldsMetadata.load("ngay", Ngay.class);
	        fieldsMetadata.load("kh", StaffStatisticalModel.class, true);
	        
	        IContext context = report.createContext();
	        context.put("ngay", ngay);
	        context.put("kh", list);
	        
	        String file = "source/ThongKeNhanVien/ThongKeNhanVien_" + format.format(new Date()) + ".docx";
	        
	        OutputStream out = new FileOutputStream(new File(file));
	        report.process(context, out);
	        return file;
		} catch (IOException | XDocReportException e) {
			e.printStackTrace();
			return "";
		}
	}

}
