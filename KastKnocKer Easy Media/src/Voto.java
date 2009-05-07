import java.io.Serializable;


public class Voto implements Serializable{

	private int Voto;
	private int Crediti;
	private String NomeEsame;
	
	public Voto(String NomeEsame,int Crediti,int Voto){
		if(Voto>30)this.Voto=30;
		if(Voto<18)this.Voto=18;
		this.Voto=Voto;	
		this.Crediti=Crediti;
		this.NomeEsame=NomeEsame;
	}
	
	public int getVoto(){
		return Voto;
	}
	
	public int getCrediti(){
		return Crediti;
	}
	
	public String getNomeEsame(){
		return NomeEsame;
	}
	
	public void setVoto(int Voto){
		this.Voto=Voto;
	}
}
