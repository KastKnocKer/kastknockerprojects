package gestionale.server;

import gestionale.shared.Contatto;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Vector;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratoreDocumentiOrdinePDF {
	
	public static final String ROOT = "webapps/Gestionale/PDF/";
	public static final String RESOURCE = ROOT + "MIAMilano.jpg";
	public static final float[][] COLUMNS = { { 280, 40, 600, 800 } };
	
	private String[] CampiOrdine = null;
	private MySQLAccess db;
	private Vector<String[]> url;
	
	public static String[][] dati = null;
	
	private String IDOrdine = null;
	private String Ordine = null;
	
	private Vector<String[]> vettoreRecords = null;
	
	////PDF
	private Vector<String[]> data;
	private int numPedaneIntere,numPedaneMezze,numcolliMezzaPedana;
	private int IndexPedana = 12;
	private int IndexPedanaMezza = 0;
	
	public GeneratoreDocumentiOrdinePDF(String idordine){
		IDOrdine = idordine;	//Salvo ID dell'ORDINE
		
		//Configuro la connessione al database
		db = new MySQLAccess("db_gestionale", "programma", "programma");
		db.setPublicHost("localhost");
		db.connetti();
		
		//Recupero i dati dell'ordine
		String query = "SELECT * FROM ordini WHERE ID = '"+idordine+"';";
		Vector<String[]> vOrdine = db.eseguiQuery( query );
		
		CampiOrdine = vOrdine.get(0);
		
		if(CampiOrdine[7].equals("Ordinario")){
			startOrdineOrdinario();
		} else if(CampiOrdine[7].equals("Speciale")){
			
		}
	}
	
	
	
	private void startOrdineOrdinario(){
		String query = "SELECT ord.DataInvioOrdine, p.*, o.Quantita, o.NumPedane, i.Descrizione, c.RagioneSociale, t.RagioneSociale, t.ID, f.RagioneSociale, f.ID FROM ordine_dettaglio o JOIN ordini ord JOIN prodotti_catalogati p JOIN imballaggio i JOIN contatti c JOIN contatti t JOIN contatti f ON o.IDProdotto = p.ID AND o.IDCliente = c.ID AND o.IDImballaggio = i.ID AND o.IDOrdine = ord.ID AND o.IDTrasportatore = t.ID AND o.IDFornitore = f.ID WHERE o.IDOrdine = '"+IDOrdine+"' ORDER BY o.IDFornitore, o.IDTrasportatore, o.IDCliente, o.IDProdotto";
		Vector<String[]> v = db.eseguiQuery( query );	if(v.size() == 0) return;
		Ordine = new String(IDOrdine);
		for(int i=Ordine.length(); i<7; i++){
			Ordine = "0" + Ordine;
		}
		
		data = new Vector<String[]>();
		Vector<String[]> temp = null;
		String[] record = null;
		String C = "";
		String F = "";
		String T = "";
		
		for(int i=0; i<v.size(); i++){
			record = v.get(i);
			if( C.equals(record[10]) && F.equals(record[13]) && T.equals(record[11]) ){
				temp.add(record);
			}else{
				C = record[10];
				F = record[13];
				T = record[11];
				if(i>0) generaPedane(temp);
				temp = new Vector<String[]>();
				temp.add(record);
			}
			
			if(i == v.size()-1) generaPedane(temp);
		}
	}
	
	private void generaPedane(Vector<String[]> v){
		String IDFornitore = null;
		if(v.size() > 0){
			IDFornitore = v.get(0)[14];
			IndexPedana = getNumPedanaFornitore(IDFornitore);////////
		}else{
			return;
		}
		
		
		String[] record = null;
		IndexPedanaMezza = 0;
		
		for(int i=0; i<v.size(); i++){
			record = v.get(i);
			numcolliMezzaPedana = (int) (Integer.parseInt(record[7])/Float.parseFloat(record[8]));
			numPedaneIntere = (int) (Float.parseFloat(record[8])/1);						//Pedane intere del dettaglio
			numPedaneMezze = (int) ((2*Float.parseFloat(record[8]))%2);			//Mezze pedane del dettaglio
			
			System.out.println("Num colli, ped int, ped mezze: " + numcolliMezzaPedana +"  "+ numPedaneIntere+"  " +numPedaneMezze);
			
			//Inserisco le pedane intere
			for(int j=0; j<numPedaneIntere; j++){
				String[] newRecord = new String[20];
				
				newRecord[0] = Integer.toString( IndexPedana ); IndexPedana++;
				newRecord[1] = record[3]+" "+record[4]+" "+record[5]+" "+record[6];
				newRecord[2] = Integer.toString( numcolliMezzaPedana*2 );
				newRecord[3] = record[9];
				newRecord[4] = record[10];
				newRecord[5] = record[11];
				newRecord[6] = record[12];
				newRecord[7] = record[13];
				newRecord[8] = record[14];
				
				data.add(newRecord);
			}
			
			//Inserisco le mezze pedane
			if(numPedaneMezze == 1){	//Se c'� la mezza pedana 
				String[] newRecord = new String[20];
				
				newRecord[1] = record[3]+" "+record[4]+" "+record[5]+" "+record[6];
				newRecord[2] = Integer.toString( numcolliMezzaPedana );
				newRecord[3] = record[9];
				newRecord[4] = record[10];
				newRecord[5] = record[11];
				newRecord[6] = record[12];
				newRecord[7] = record[13];
				newRecord[8] = record[14];
				
				if(IndexPedanaMezza == 0){	//ed � presente un'altra mezza pedana da completare SE 0 non c'�
					newRecord[0] = Integer.toString( IndexPedana )+"A";
					IndexPedanaMezza=IndexPedana;
					IndexPedana++;
				}else{
					newRecord[0] = Integer.toString( IndexPedanaMezza )+"B";
					IndexPedanaMezza=0;
				}
				
				data.add(newRecord);
			}
			
		}
		setNumPedanaFornitore(IDFornitore, IndexPedana);
	}
	
	public String[][] generaPDF(){
		if(CampiOrdine[7].equals("Ordinario")){
			return generaPDFOrdineOrdinario();
		} else if(CampiOrdine[7].equals("Speciale")){
			return null;
		}
		return null;
	}
	
	public String[][] generaPDFOrdineOrdinario(){
		if(data.size() == 0) return null;	//Niente ordinazioni
		
		url = new Vector<String[]>();
		String idtrasp = ""; String idforn = "";
		
		for(int i=0; i<data.size(); i++){
			String[] rec = data.get(i);
			
			if(rec[6].equals(idtrasp)){
				
			}else{
				CreaPDFTrasportatori(IDOrdine,rec[6],rec[5]);
				idtrasp = rec[6];
			}
			if(rec[8].equals(idforn)){
				
			}else{
				CreaPDFFornitori(IDOrdine,rec[8],rec[7]);
				idforn = rec[8];
			}
		}
		
		String[][] urls =  new String[url.size()][];
		for(int i=0; i<url.size(); i++){
			urls[i] = url.get(i);
		}
		
		return urls;
	}
	
	
	private void CreaPDFTrasportatori(String Ordine,String IDTrasportatore, String ragSoc){
		// step 1
        Document document = new Document(PageSize.A4, 30, 30, 40, 30);
        // step 2
        PdfWriter writer = null;
        String filename = null;
        String filenamefull = null;
		try {
			
			filename = "PDF\\"+ this.Ordine+" - T - "+ragSoc+".pdf";
			filenamefull = ROOT + this.Ordine+" - T - "+ragSoc+".pdf";
			writer = PdfWriter.getInstance(document, new FileOutputStream( filenamefull ));
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
	        
	       
	        //COLONNA INTESTAZIONE
		
	        ColumnText ct = new ColumnText(writer.getDirectContent());
	        String query = "SELECT * FROM contatti WHERE ID = '"+IDTrasportatore+"'";
	        Vector<String[]> v = db.eseguiQuery( query );	if(v.size() == 0) return;
	        String[] record = v.get(0);
	        Contatto contatto = new Contatto();
			
	        contatto.setID(record[0]);
	        contatto.setRagioneSociale(record[1]);
	        contatto.setPrecisazione(record[2]);
	        contatto.setPIVA(record[3]);
			contatto.setIndirizzo(record[5]);
			contatto.setTelefono(record[6]);
			contatto.setFax(record[8]);
			contatto.seteMail(record[9]);
			contatto.setTipoSoggetto(record[11]);
			
			ct.addText(new Chunk( "Trasportatore" 								, new Font(FontFamily.HELVETICA, 22))); ct.addText(Chunk.NEWLINE);
	        ct.addText(new Chunk( ragSoc										, new Font(FontFamily.HELVETICA, 16))); ct.addText(Chunk.NEWLINE);
	        
			
			
			Vector<String[]> vIndirizzi = contatto.getVectorIndirizzi();
	        String[] indirizzo;
	        if(vIndirizzi.size()>0){
	        	indirizzo = vIndirizzi.get(0);
	        	ct.addText(new Chunk( "Indirizzo: "+indirizzo[1]+" "+indirizzo[2]			, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
	        	ct.addText(new Chunk( "CAP: "+indirizzo[3]+", "+indirizzo[5]+" ("+indirizzo[6]+")"			, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
		        
	        }
	        
	        Vector<String[]> vTelefoni = contatto.getVectorCampo("Telefono");
	        Vector<String[]> vFax = contatto.getVectorCampo("Fax");
	        String[] telefono;
	        String[] fax;
	        
	        if(vTelefoni.size()>0){
	        	telefono = vTelefoni.get(0);
	        	ct.addText(new Chunk( "Tel: "+ telefono[1]				, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
		        
	        }
	        if(vFax.size()>0){
	        	fax = vFax.get(0);
	        	ct.addText(new Chunk( "Fax: "+ fax[1]							, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
		        
	        }
	        
	        
	        
	        
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
	 
	        //ct.addText(new Phrase("Lines written: " + linesWritten));
	        //ct.go();
	        //ct.addText(new Phrase("Lines written: " + linesWritten));
	        //ct.go();
	        
	        Paragraph ord = new Paragraph("Ordine del "+CampiOrdine[1]+":", new Font(FontFamily.HELVETICA, 12));
		   
	        document.add(new Paragraph(" ", new Font(FontFamily.HELVETICA, 90)));
	        document.add(ord);
	        
	        //Compilazione tabella
	        
	        PdfPTable table = new PdfPTable(new float[]{10,21,8,25,18,18});
	        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
	        
	        table.addCell("Pedana");
	        table.addCell("Merce");
	        table.addCell("Colli");
	        table.addCell("Imballaggio");
	        table.addCell("Cliente");
	        table.addCell("Fornitore");
	        for(int i=0; i<6; i++) table.addCell("");
	        
	        for(int i=0; i<data.size(); i++){
	        	String[] rec = data.get(i);
	        	if(rec[6].equals(IDTrasportatore)){
	        	table.addCell(new Phrase(rec[0], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[1], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[2], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[3], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[4], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[7], new Font(FontFamily.HELVETICA, 8)));
	        	
	        	}

	        }
	        
	        table.setTotalWidth(PageSize.A4.getWidth()-60f);
	        table.writeSelectedRows(0, -1, 30, PageSize.A4.getHeight() - 220, writer.getDirectContent());
	        
	        
	        
	        // step 5
	        document.close();
	        System.out.println("CLOSAAAAAAAAAAAAAAAAAAAAA");
	        
	        url.add(new String[]{filename, "Ordine trasportatore: " + ragSoc});
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private void CreaPDFFornitori(String Ordine,String IDFornitore, String ragSoc){
		// step 1
        Document document = new Document(PageSize.A4, 30, 30, 40, 30);
        // step 2
        PdfWriter writer = null;
        String filename = null;
        String filenamefull = null;
		try {
			
			filename = "PDF\\"+ this.Ordine+" - F - "+ragSoc+".pdf";
			filenamefull = ROOT + this.Ordine+" - F - "+ragSoc+".pdf";
			writer = PdfWriter.getInstance(document, new FileOutputStream( filenamefull ));
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
	        
	       
	        //COLONNA INTESTAZIONE
		
	        ColumnText ct = new ColumnText(writer.getDirectContent());
	        String query = "SELECT * FROM contatti WHERE ID = '"+IDFornitore+"'";
	        Vector<String[]> v = db.eseguiQuery( query );	if(v.size() == 0) return;
	        String[] record = v.get(0);
	        Contatto contatto = new Contatto();
			
	        contatto.setID(record[0]);
	        contatto.setRagioneSociale(record[1]);
	        contatto.setPrecisazione(record[2]);
	        contatto.setPIVA(record[3]);
			contatto.setIndirizzo(record[5]);
			contatto.setTelefono(record[6]);
			contatto.setFax(record[8]);
			contatto.seteMail(record[9]);
			contatto.setTipoSoggetto(record[11]);
			
			ct.addText(new Chunk( "Fornitore" 	, new Font(FontFamily.HELVETICA, 22))); ct.addText(Chunk.NEWLINE);
	        ct.addText(new Chunk( ragSoc		, new Font(FontFamily.HELVETICA, 16))); ct.addText(Chunk.NEWLINE);
	        
			
			
			Vector<String[]> vIndirizzi = contatto.getVectorIndirizzi();
	        String[] indirizzo;
	        if(vIndirizzi.size()>0){
	        	indirizzo = vIndirizzi.get(0);
	        	ct.addText(new Chunk( "Indirizzo: "+indirizzo[1]+" "+indirizzo[2]			, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
	        	ct.addText(new Chunk( "CAP: "+indirizzo[3]+", "+indirizzo[5]+" ("+indirizzo[6]+")"			, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
		        
	        }
	        
	        Vector<String[]> vTelefoni = contatto.getVectorCampo("Telefono");
	        Vector<String[]> vFax = contatto.getVectorCampo("Fax");
	        String[] telefono;
	        String[] fax;
	        
	        if(vTelefoni.size()>0){
	        	telefono = vTelefoni.get(0);
	        	ct.addText(new Chunk( "Tel: "+ telefono[1]				, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
		        
	        }
	        if(vFax.size()>0){
	        	fax = vFax.get(0);
	        	ct.addText(new Chunk( "Fax: "+ fax[1]							, new Font(FontFamily.HELVETICA, 12))); ct.addText(Chunk.NEWLINE);
		        
	        }
	        
	        
	        
	        
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
	 
	        //ct.addText(new Phrase("Lines written: " + linesWritten));
	        //ct.go();
	        //ct.addText(new Phrase("Lines written: " + linesWritten));
	        //ct.go();
	        
	        Paragraph ord = new Paragraph("Ordine del "+CampiOrdine[1]+":", new Font(FontFamily.HELVETICA, 12));
		   
	        document.add(new Paragraph(" ", new Font(FontFamily.HELVETICA, 90)));
	        document.add(ord);
	        
	        //Compilazione tabella
	        
	        PdfPTable table = new PdfPTable(new float[]{10,21,8,18,18});
	        table.getDefaultCell().setBorder(Rectangle.BOTTOM);
	        
	        table.addCell("Pedana");
	        table.addCell("Merce");
	        table.addCell("Colli");
	        table.addCell("Imballaggio");
	        table.addCell("Trasportatore");
	        for(int i=0; i<5; i++) table.addCell("");
	        
	        for(int i=0; i<data.size(); i++){
	        	String[] rec = data.get(i);
	        	if(rec[8].equals(IDFornitore)){
	        	table.addCell(new Phrase(rec[0], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[1], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[2], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[3], new Font(FontFamily.HELVETICA, 8)));
	        	table.addCell(new Phrase(rec[5], new Font(FontFamily.HELVETICA, 8)));
	        	
	        	}

	        }
	        
	        table.setTotalWidth(PageSize.A4.getWidth()-60f);
	        table.writeSelectedRows(0, -1, 30, PageSize.A4.getHeight() - 220, writer.getDirectContent());
	        
	        
	        
	        // step 5
	        document.close();
	        System.out.println("CLOSAAAAAAAAAA");
	        
	        url.add(new String[]{filename, "Ordine trasportatore: " + ragSoc});
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public String[][] getUrls() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/*
	private void GeneraPDF(){
		vettoreRecords  = new Vector<Record>();
		System.out.println("GENERAAAAAAAAAAA");
		
		
		
		String[] rec = v.get(0);
		Ordine = rec[0];
		
		Vector<Record> records = new Vector<Record>();
		Record record = null;
		
		for(int i=0; i<v.size(); i++){
			record = new Record();
			
			record.setAttribute("prodotto", rec[3]+" "+rec[4]+" "+rec[5]+" cal "+rec[6]);
			record.setAttribute("colli", rec[7]);
			record.setAttribute("imballaggio", rec[9]);
			record.setAttribute("cliente", rec[9]);
			record.setAttribute("fornitore", 0);
			record.setAttribute("trasportatore", 0);
			record.setAttribute("pedana", 0);
			record.setAttribute("numpedane", rec[8]);
			
			records.add(record);
		}
		
		try{
		
        
        
        
        
        //writer.getDirectContent().addImage(img, 30, 0, 0, 800, 0, 0);
        //document.add(img);
        
        
        //Aggiungo titolo
        Paragraph p = new Paragraph(Titolo, new Font(FontFamily.HELVETICA, 22));
        p.setAlignment(Element.ALIGN_RIGHT);
       
        Chunk c = new Chunk(Titolo, new Font(FontFamily.HELVETICA, 22));
        Phrase ph = new Phrase(Titolo, new Font(FontFamily.HELVETICA, 22));
        
        //document.add(p);
        document.add(c);
        document.add(ph);
        
        
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
        
        //Compilazione tabella
        
        PdfPTable table = new PdfPTable(new float[]{150,150,150,150,150});
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        
        table.addCell("Pedana");
        table.addCell("Merce");
        table.addCell("Colli");
        table.addCell("Imballaggio");
        table.addCell("Cliente");
        for(int i=0; i<5; i++) table.addCell("");
        
        
        Record r = null;
        for(int i=0; i<vettoreRecords.size(); i++){
        	r = vettoreRecords.get(i);
        	table.addCell(new Phrase(	r.getAttribute("pedana")		, new Font(FontFamily.HELVETICA, 8)));
        	table.addCell(new Phrase(	r.getAttribute("prodotto")		, new Font(FontFamily.HELVETICA, 8)));
        	table.addCell(new Phrase(	r.getAttribute("colli")			, new Font(FontFamily.HELVETICA, 8)));
        	table.addCell(new Phrase(	r.getAttribute("imballaggio")	, new Font(FontFamily.HELVETICA, 8)));
        	table.addCell(new Phrase(	r.getAttribute("cliente")		, new Font(FontFamily.HELVETICA, 8)));
        }
        
        table.setTotalWidth(PageSize.A4.getWidth()-60f);
        table.writeSelectedRows(0, -1, 30, PageSize.A4.getHeight() - 200, writer.getDirectContent());
        
        
        
        
        
		}catch(Exception E){
			
		}
	}
*/
	
	private int getNumPedanaFornitore(String IDFornitore){
		String query = "SELECT NumPedana FROM num_pedane_fornitore WHERE IDFornitore = '"+IDFornitore+"'";
		Vector<String[]> r = db.eseguiQuery(query);
		
		if(r.size() == 0 || r == null){//Non esiste il record
			query = "INSERT INTO num_pedane_fornitore (IDFornitore, NumPedana) VALUES ('"+IDFornitore+"','1')";
			db.eseguiAggiornamento(query);
			return 1;
		}
		String[] result = r.get(0);
		return Integer.parseInt( result[0] );
	}
	
	private boolean setNumPedanaFornitore(String IDFornitore, int value){
		String query = "UPDATE num_pedane_fornitore SET NumPedana = '"+value+"' WHERE IDFornitore = '"+IDFornitore+"'";
		return db.eseguiAggiornamento(query);
	}
}
