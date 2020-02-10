package com.th.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import com.th.entity.EmpKhoaHoc;
import com.th.entity.KhoaHoc;
import com.th.repository.KhoaHocRepository;

@Service
public class KhoaHocService {

	@Autowired
	private KhoaHocRepository khoaHocRepository;
	
	public List<KhoaHoc> listAll(){
		return khoaHocRepository.findAll();
	}
	
	public void save(KhoaHoc khoaHoc) {
		khoaHocRepository.save(khoaHoc);
	}
	
//	public void savemota(EmpKhoaHoc empKhoaHoc) {
//		khoaHocRepository.save(empKhoaHoc);
//	}
	
	public KhoaHoc get(int id) {
		return khoaHocRepository.findById(id).get();
	}
	
	public void delete(int id) {
		 khoaHocRepository.deleteById(id);
	}

	
	
	public boolean createPdf(List<EmpKhoaHoc> khoahoc, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {

		Document document = new Document(PageSize.A4, 15, 15, 45, 30);

		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "employees" + ".pdf"));
			document.open();
//			BaseFont bFont = BaseFont.createFont("mangal.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			 
			BaseFont courier = BaseFont.createFont("assets/Lato-Regular.ttf", BaseFont.IDENTITY_H,BaseFont.EMBEDDED);		
			Font tableHeader = new Font(courier, 12, Font.NORMAL);
			Font tableBody = new Font(courier, 11, Font.NORMAL);
			Font mainFont = new Font(courier, 14, Font.NORMAL);
			
//			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			
			Paragraph paragraph = new Paragraph("Danh sách học viên khóa học", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			;

//			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
//			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

			PdfPCell ten = new PdfPCell(new Paragraph("Danh sách học viên", tableHeader));

			ten.setBorderColor(BaseColor.BLACK);
			ten.setPaddingLeft(10);
			ten.setHorizontalAlignment(Element.ALIGN_CENTER);
			ten.setVerticalAlignment(Element.ALIGN_CENTER);
			ten.setBackgroundColor(BaseColor.GRAY);
			ten.setExtraParagraphSpace(5f);
			table.addCell(ten);

			PdfPCell mota = new PdfPCell(new Paragraph("Mô tả", tableHeader));

			mota.setBorderColor(BaseColor.BLACK);
			mota.setPaddingLeft(10);
			mota.setHorizontalAlignment(Element.ALIGN_CENTER);
			mota.setVerticalAlignment(Element.ALIGN_CENTER);
			mota.setBackgroundColor(BaseColor.GRAY);
			mota.setExtraParagraphSpace(5f);
			table.addCell(mota);
			
			PdfPCell ngaybatdau = new PdfPCell(new Paragraph("Ngày bắt đầu", tableHeader));
			
			ngaybatdau.setBorderColor(BaseColor.BLACK);
			ngaybatdau.setPaddingLeft(10);
			ngaybatdau.setHorizontalAlignment(Element.ALIGN_CENTER);
			ngaybatdau.setVerticalAlignment(Element.ALIGN_CENTER);
			ngaybatdau.setBackgroundColor(BaseColor.GRAY);
			ngaybatdau.setExtraParagraphSpace(5f);
			table.addCell(ngaybatdau);
//
			PdfPCell ngayketthuc = new PdfPCell(new Paragraph("Ngày kết thúc", tableHeader));
			
			ngayketthuc.setBorderColor(BaseColor.BLACK);
			ngayketthuc.setPaddingLeft(10);
			ngayketthuc.setHorizontalAlignment(Element.ALIGN_CENTER);
			ngayketthuc.setVerticalAlignment(Element.ALIGN_CENTER);
			ngayketthuc.setBackgroundColor(BaseColor.GRAY);
			ngayketthuc.setExtraParagraphSpace(5f);
			table.addCell(ngayketthuc);
			
			for(EmpKhoaHoc kh: khoahoc) {
				
				
				PdfPCell tenValue = new PdfPCell(new Paragraph(kh.getEmp().getTen(), tableBody));
				tenValue.setBorderColor(BaseColor.BLACK);
				tenValue.setPaddingLeft(10);
				tenValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				tenValue.setVerticalAlignment(Element.ALIGN_CENTER);
				tenValue.setBackgroundColor(BaseColor.WHITE);
				tenValue.setExtraParagraphSpace(5f);
				table.addCell(tenValue);
				
				
				PdfPCell motaValue = new PdfPCell(new Paragraph(kh.getMota(), tableBody));
				motaValue.setBorderColor(BaseColor.BLACK);
				motaValue.setPaddingLeft(10);
				motaValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				motaValue.setVerticalAlignment(Element.ALIGN_CENTER);
				motaValue.setBackgroundColor(BaseColor.WHITE);
				motaValue.setExtraParagraphSpace(5f);
				table.addCell(motaValue);
				
				System.out.println("Hello: "+kh.getThoigianbatdau().toString());
 				
				
				PdfPCell ngaybatdauValue = new PdfPCell(new Paragraph(kh.getThoigianbatdau().toString(), tableBody));
				ngaybatdauValue.setBorderColor(BaseColor.BLACK);
				ngaybatdauValue.setPaddingLeft(10);
				ngaybatdauValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ngaybatdauValue.setVerticalAlignment(Element.ALIGN_CENTER);
				ngaybatdauValue.setBackgroundColor(BaseColor.WHITE);
				ngaybatdauValue.setExtraParagraphSpace(5f);
				table.addCell(ngaybatdauValue);
//				
				PdfPCell ngayketthucValue = new PdfPCell(new Paragraph(kh.getThoigianketthuc().toString(), tableBody));
				ngayketthucValue.setBorderColor(BaseColor.BLACK);
				ngayketthucValue.setPaddingLeft(10);
				ngayketthucValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ngayketthucValue.setVerticalAlignment(Element.ALIGN_CENTER);
				ngayketthucValue.setBackgroundColor(BaseColor.WHITE);
				ngayketthucValue.setExtraParagraphSpace(5f);
				table.addCell(ngayketthucValue);
				
			}
			 
			document.add(table);
			document.close();
			writer.close();
			
			return true; 
		} catch (Exception e) {
			return false;
		}
 
	}

	public boolean createExcel(List<EmpKhoaHoc> khoahoc, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if (!exists) {
			new File(filePath).mkdirs();
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(file+"/"+"employees"+".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("Danh sách học viên");
			workSheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFillForegroundColor(HSSFColor.AQUA.index);
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerRow = workSheet.createRow(0);
			
			HSSFCell firstName = headerRow.createCell(0);
			firstName.setCellValue("Họ tên");
			firstName.setCellStyle(headerCellStyle);
			
			HSSFCell lastName = headerRow.createCell(1);
			lastName.setCellValue("Mô tả");
			lastName.setCellStyle(headerCellStyle);
			
			HSSFCell email = headerRow.createCell(2);
			email.setCellValue("Thời gian bắt đầu");
			email.setCellStyle(headerCellStyle);
			
			HSSFCell phoneNumber = headerRow.createCell(3);
			phoneNumber.setCellValue("Thời gian kết thúc");
			phoneNumber.setCellStyle(headerCellStyle);
			
			int i = 1;

			for(EmpKhoaHoc kh: khoahoc) {
				HSSFRow bodyRow = workSheet.createRow(i);
				
				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
				
				HSSFCell firstNameValue = bodyRow.createCell(0);
				firstNameValue.setCellValue(kh.getEmp().getTen());
				firstNameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell lastNameValue = bodyRow.createCell(1);
				lastNameValue.setCellValue(kh.getMota());
				lastNameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell emailValue = bodyRow.createCell(2);
				emailValue.setCellValue(kh.getThoigianbatdau().toString());
				emailValue.setCellStyle(bodyCellStyle);
				
				HSSFCell phoneNumberValue = bodyRow.createCell(3);
				phoneNumberValue.setCellValue(kh.getThoigianketthuc().toString());
				phoneNumberValue.setCellStyle(bodyCellStyle);
				
				i++;
			}
			
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
}
