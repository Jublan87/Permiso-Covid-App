package com.unla.grupo21.exporters;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.unla.grupo21.models.UserModel;
 
 
public class UserPDFExporter {
    private List<UserModel> listUsers;
     
    public UserPDFExporter(List<UserModel> listUsers) {
        this.listUsers = listUsers;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Nombre", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Apellido", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Tipo", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Documento", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);      
        
        cell.setPhrase(new Phrase("Username", font));
        table.addCell(cell);  
        
        cell.setPhrase(new Phrase("Perfil", font));
        table.addCell(cell);  
    }
     
    private void writeTableData(PdfPTable table) {
        for (UserModel user : listUsers) {
            table.addCell(user.getNombre());
            table.addCell(user.getApellido());
            table.addCell(user.getTipoDocumento().toString());
            table.addCell(user.getDocumento().toString());
            table.addCell(user.getEmail());
            table.addCell(user.getUsername());
            table.addCell(user.getUserRole().getRole());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
    	Rectangle A4Apaisada = new RectangleReadOnly(842,595);
        Document document = new Document(A4Apaisada);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Lista de Usuarios", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {2f, 2f, 0.6f, 1.2f, 2.5f, 2f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }
}