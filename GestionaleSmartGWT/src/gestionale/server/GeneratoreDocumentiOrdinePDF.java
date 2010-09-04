package gestionale.server;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.Executors;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class GeneratoreDocumentiOrdinePDF {
	
	public static final String RESULT = "provaPDF.pdf";
	public static final String RESOURCE = "MIAMilano.jpg";
	public static final float[][] COLUMNS = { { 280, 40, 600, 800 } };
	
	public static String[][] dati = new String[][]{ new String[]{"A","B","C","D","E"}, 
													new String[]{"A","B","C","D","E"}, 
													new String[]{"A","B","C","D","E"}, 
													new String[]{"A","B","C","D","E"} 
													};
	
	private String Ordine = "Ordine 18/09/2010";
	private String Titolo = "Trasportatore";
	private String RagioneSociale = "Trasportatore";
	private String Telefono = "Trasportatore";
	private String Fax = "Trasportatore";
	
	private String Intestazione = "GINO SRL\nLOVE AND ROLL";

	public GeneratoreDocumentiOrdinePDF(String idordine) throws DocumentException, MalformedURLException, IOException{

		// step 1
        Document document = new Document(PageSize.A4, 30, 30, 40, 30);
        // step 2
        PdfWriter writer =  PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        writer.setCompressionLevel(0);
        
        // step 3
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        
        // step 4
        //Aggiungo logo
        Image img = Image.getInstance(RESOURCE);
        img.scalePercent(80);
        img.setAbsolutePosition(30,(PageSize.A4.getHeight() - img.getScaledHeight()-40));
        writer.getDirectContent().addImage(img);
        //writer.getDirectContent().addImage(img, 30, 0, 0, 800, 0, 0);
        //document.add(img);
        
        /*
        //Aggiungo titolo
        Paragraph p = new Paragraph(Titolo, new Font(FontFamily.HELVETICA, 22));
        p.setAlignment(Element.ALIGN_RIGHT);
       
        Chunk c = new Chunk(Titolo, new Font(FontFamily.HELVETICA, 22));
        Phrase ph = new Phrase(Titolo, new Font(FontFamily.HELVETICA, 22));
        
        //document.add(p);
        document.add(c);
        document.add(ph);
        */
        
        //writer.getDirectContent().addImage(img, 200, 0, 0,121, 40, PageSize.A4.getHeight()-141);
        
        //COLONNA INTESTAZIONE
        ColumnText ct = new ColumnText(writer.getDirectContent());
        
        ct.addText(new Chunk(Titolo, new Font(FontFamily.HELVETICA, 22))); ct.addText(Chunk.NEWLINE);
        ct.addText(new Chunk("ragionesociale + precisazione", new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
        ct.addText(new Chunk("indirizzo", new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
        ct.addText(new Chunk("telefono", new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
        ct.addText(new Chunk("fax", new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
        
        
        
        ct.setAlignment(Element.ALIGN_JUSTIFIED);
        ct.setExtraParagraphSpace(6);
        ct.setLeading(0, 1.2f);
        ct.setFollowingIndent(27);
        int linesWritten = 0;
        int column = 0;
        int status = ColumnText.START_COLUMN;
        while (ColumnText.hasMoreText(status)) {
            ct.setSimpleColumn(
                    COLUMNS[column][0], COLUMNS[column][1],
                    COLUMNS[column][2], COLUMNS[column][3]);
            ct.setYLine(COLUMNS[column][3]);
            status = ct.go();
            linesWritten += ct.getLinesWritten();
            column = Math.abs(column - 1);
            if (column == 0)
                document.newPage();
        }
 
        ct.addText(new Phrase("Lines written: " + linesWritten));
        ct.go();
        ct.addText(new Phrase("Lines written: " + linesWritten));
        ct.go();
        
        
        
        PdfPTable table = new PdfPTable(new float[]{150,150,150,150,150});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        
        table.addCell("Pedana");
        table.addCell("Merce");
        table.addCell("Colli");
        table.addCell("Imballaggio");
        table.addCell("Cliente");
        for(int i=0; i<5; i++) table.addCell("");
        
        
        for(int i=0; i<dati.length; i++){
        	for(int j=0; j<dati[0].length; j++){
        		table.addCell(new Phrase(dati[i][j], new Font(FontFamily.HELVETICA, 8)));
        	}
        }
        
        table.setTotalWidth(PageSize.A4.getWidth()-60f);
        table.writeSelectedRows(0, -1, 30, PageSize.A4.getHeight() - 200, writer.getDirectContent());
        
        
        
        // step 5
        document.close();
        
    
		
	}
	
}
