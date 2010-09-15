package gestionale.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Contatto implements IsSerializable {
	
	private String ID="";
	private String RagioneSociale="";
	private String Precisazione="";
	private String PIVA="";
	private String Logo="";
	
	private String Indirizzo="";
	private String Telefono="";
	private String Cellulare="";
	private String Fax="";
	private String eMail="";
	
	private String SitoWeb="";
	private String TipoSoggetto="";
	private String Provvigione="";
	
	private String Note="";
	
	private String Latitudine="";
	private String Longitudine="";
	
	private String IDMercato="";
	

	
	
	
	public Contatto(){
		super();	
	}
	
	public String getHtmlText(){
		
		// Struttura * ETICHETTA ? VALORE * (il separatore è "?")
		
		String corpoHtml = new String("<b>"+this.getRagioneSociale()+ "</b><br>");
		if(this.getPrecisazione().length()!=0) corpoHtml=new String(corpoHtml + this.getPrecisazione()+"<br>");
		if(this.getPIVA().length()!=0) corpoHtml=new String(corpoHtml + "P.IVA:" + this.getPIVA()+"<br>");
		corpoHtml=new String(corpoHtml + "<br>");
		
		
		String stringResult;


		stringResult=parseTextIndirizzo( this.getIndirizzo() );
        if(stringResult!=null){ 
        	corpoHtml=new String(corpoHtml + stringResult);
        }
		
		stringResult=parseText( this.getTelefono() );
	        if(stringResult!=null){ 
	        	corpoHtml=new String(corpoHtml + stringResult);
	        }
    
        stringResult=parseText( this.getCellulare() );
	        if(stringResult!=null){ 
	        	corpoHtml=new String(corpoHtml + stringResult);
	        }
    
        stringResult=parseText( this.getFax() );
	        if(stringResult!=null){ 
	        	corpoHtml=new String(corpoHtml + stringResult);
	        }
	        
	    stringResult=parseText( this.geteMail() );
	        if(stringResult!=null){ 
	        	corpoHtml=new String(corpoHtml + stringResult);
	        }
	        
	    corpoHtml = corpoHtml + "<br>" + this.getNote();
        
        return corpoHtml;
	}
	
	private String parseTextIndirizzo(String stringa){
		if(stringa.length()==0) return null;
		int indiceA,indiceB;
		String risultato = new String("");
		String temp,temporanea;
		temp=new String("");
		
		while(stringa.length()>0){
			indiceA=stringa.indexOf('*');
			indiceB=stringa.indexOf('*', 1);
			
			temporanea=stringa.substring(indiceA+1,indiceB);
			stringa = stringa.substring(indiceB+1);
			String[] vetString = new String[10];
			System.out.println("Temporanea: " + temporanea);
			
			indiceA = temporanea.indexOf('+');
			vetString[0] = temporanea.substring(0, indiceA); // Etichetta
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[1] = temporanea.substring(indiceA+1, indiceB); // Via
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[2] = temporanea.substring(indiceB+1, indiceA); // Numero Civico
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[3] = temporanea.substring(indiceA+1, indiceB); // CAP
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[4] = temporanea.substring(indiceB+1, indiceA); // Località
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[5] = temporanea.substring(indiceA+1, indiceB); // Città
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[6] = temporanea.substring(indiceB+1, indiceA); // Provincia
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[7] = temporanea.substring(indiceA+1, indiceB); // Regione
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[8] = temporanea.substring(indiceB+1, indiceA); // Stato
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[9] = temporanea.substring(indiceA+1); // Campo aggiuntivo
			
			temp = temp + "<b>" + vetString[0] + "</b>" + ":" + "<br>";
			temp = temp + vetString[1] + ", " + vetString[2] + "<br>";
			temp = temp + vetString[3] + ", ";
			
			if(vetString[5].length()>0) temp = temp + vetString[4] + ", ";
			temp = temp + vetString[5] + "<br>";
			
			temp = temp + vetString[6] + ", " + vetString[7] + ", " + vetString[8] + "<br>";
			
			
			risultato = risultato + temp +"<br>";
		}
		
		return risultato;
	}
	
	private String parseText(String stringa){
		if(stringa.length()==0) return null;
		int indiceA,indiceB,indiceC;
		String risultato = new String("");
		String temp;
		
		while(stringa.length()>0){
			indiceA=stringa.indexOf('*');
			indiceB=stringa.indexOf('?');
			indiceC=stringa.indexOf('*', 1);
			
			temp=stringa.substring(indiceA+1, indiceB)+" - " +stringa.substring(indiceB+1, indiceC);
			risultato=risultato + temp + "<br>";
			
			stringa = stringa.substring(indiceC+1);
		}
		
		return risultato;
	}
	
	public Vector<String[]> getVectorIndirizzi(){
		Vector<String[]> v = new Vector<String[]>();
		String stringa = this.getIndirizzo();
		if(stringa.length()==0) return v;
		int indiceA,indiceB;
		String risultato = new String("");
		String temp,temporanea;
		temp=new String("");
		
		while(stringa.length()>0){
			indiceA=stringa.indexOf('*');
			indiceB=stringa.indexOf('*', 1);
			
			temporanea=stringa.substring(indiceA+1,indiceB);
			stringa = stringa.substring(indiceB+1);
			String[] vetString = new String[9];
			System.out.println("Temporanea: " + temporanea);
			
			indiceA = temporanea.indexOf('+');
			vetString[0] = temporanea.substring(0, indiceA); // Etichetta
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[1] = temporanea.substring(indiceA+1, indiceB); // Via
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[2] = temporanea.substring(indiceB+1, indiceA); // Numero Civico
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[3] = temporanea.substring(indiceA+1, indiceB); // CAP
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[4] = temporanea.substring(indiceB+1, indiceA); // Località
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[5] = temporanea.substring(indiceA+1, indiceB); // Città
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[6] = temporanea.substring(indiceB+1, indiceA); // Provincia
			
			indiceB = temporanea.indexOf('+', indiceA+1);
			vetString[7] = temporanea.substring(indiceA+1, indiceB); // Regione
			
			indiceA = temporanea.indexOf('+', indiceB+1);
			vetString[8] = temporanea.substring(indiceB+1); // Stato
			
			v.add(vetString);
		}
		
		return v;
	}

	public Vector<String[]> getVectorCampo(String campo){
		String stringa = null;
		
		if(campo.equals("Telefono")) stringa=this.getTelefono();
		if(campo.equals("Cellulare")) stringa=this.getCellulare();
		if(campo.equals("Fax")) stringa=this.getFax();
		if(campo.equals("eMail")) stringa=this.geteMail();
		
		
		Vector<String[]> v = new Vector<String[]>();
		
		if(stringa.length()==0) return v;
		int indiceA,indiceB,indiceC;
		String risultato = new String("");
		String temp,temporanea;
		temp=new String("");
		
		while(stringa.length()>0){
			indiceA=stringa.indexOf('*');
			indiceB=stringa.indexOf('?');
			indiceC=stringa.indexOf('*', 1);
			
			String[] str = new String[2];
			str[0] = stringa.substring(indiceA+1, indiceB);
			str[1] = stringa.substring(indiceB+1, indiceC);
			
			stringa = stringa.substring(indiceC+1);
			v.add(str);
		}
		
		return v;
	}
	
	public void setID(String iD) {
		ID = iD;
	}

	public String getID() {
		return ID;
	}

	public void setRagioneSociale(String ragioneSociale) {
		RagioneSociale = ragioneSociale;
	}

	public String getRagioneSociale() {
		return RagioneSociale;
	}

	public void setPrecisazione(String precisazione) {
		Precisazione = precisazione;
	}

	public String getPrecisazione() {
		return Precisazione;
	}

	public void setPIVA(String pIVA) {
		PIVA = pIVA;
	}

	public String getPIVA() {
		return PIVA;
	}

	public void setLogo(String logo) {
		Logo = logo;
	}

	public String getLogo() {
		return Logo;
	}

	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}

	public String getIndirizzo() {
		return Indirizzo;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setCellulare(String cellulare) {
		Cellulare = cellulare;
	}

	public String getCellulare() {
		return Cellulare;
	}

	public void setFax(String fax) {
		Fax = fax;
	}

	public String getFax() {
		return Fax;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String geteMail() {
		return eMail;
	}

	public void setSitoWeb(String sitoWeb) {
		SitoWeb = sitoWeb;
	}

	public String getSitoWeb() {
		return SitoWeb;
	}

	public void setTipoSoggetto(String tipoSoggetto) {
		TipoSoggetto = tipoSoggetto;
	}

	public String getTipoSoggetto() {
		return TipoSoggetto;
	}

	public void setProvvigione(String provvigione) {
		Provvigione = provvigione;
	}

	public String getProvvigione() {
		return Provvigione;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getNote() {
		return Note;
	}

	public void setLatitudine(String latitudine) {
		Latitudine = latitudine;
	}

	public String getLatitudine() {
		return Latitudine;
	}

	public void setLongitudine(String longitudine) {
		Longitudine = longitudine;
	}

	public String getLongitudine() {
		return Longitudine;
	}

	public void setIDMercato(String iDMercato) {
		IDMercato = iDMercato;
	}

	public String getIDMercato() {
		return IDMercato;
	}

	
	
}
