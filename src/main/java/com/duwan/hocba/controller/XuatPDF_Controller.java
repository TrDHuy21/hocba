package com.duwan.hocba.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class XuatPDF_Controller {

    @GetMapping("/giaovien/export-pdf")
    public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
        // Thiết lập response header
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=example.pdf");

        // Tạo một đối tượng Document
        Document document = new Document();

        // Ghi nội dung vào Document
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        document.add(new Paragraph("Hello, this is a sample PDF!"));
        document.close();
    }
}
