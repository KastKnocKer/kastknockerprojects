package Grafica;
import java.awt.Color;

import javax.swing.*;

import Liste.ListaOrdini;

public class MainPanel extends JPanel{
	
	private JListOrdini JList_ListaOrdini;
    private javax.swing.JPanel JPanelListaOrdini;
    private javax.swing.JTabbedPane JTabbedPaneVisualizzazione;
    private JPanelListaClienti PVisualClienti;
    private JPanelVisualizzazioneOrdini PVisualOrdine;
    private JPanelListaProdotti PVisualProdotti;
    private javax.swing.JScrollPane ScrollPaneListaOrdini;
	
	public MainPanel(){
		super();
		
		JPanelListaOrdini = new javax.swing.JPanel();
        ScrollPaneListaOrdini = new javax.swing.JScrollPane();
        JList_ListaOrdini = new JListOrdini();
        JTabbedPaneVisualizzazione = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());

        
        ScrollPaneListaOrdini.setViewportView(JList_ListaOrdini);

        javax.swing.GroupLayout JPanelListaOrdiniLayout = new javax.swing.GroupLayout(JPanelListaOrdini);
        JPanelListaOrdini.setLayout(JPanelListaOrdiniLayout);
        JPanelListaOrdiniLayout.setHorizontalGroup(
            JPanelListaOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneListaOrdini, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
        );
        JPanelListaOrdiniLayout.setVerticalGroup(
            JPanelListaOrdiniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ScrollPaneListaOrdini, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );

        add(JPanelListaOrdini, java.awt.BorderLayout.WEST);
        add(JTabbedPaneVisualizzazione, java.awt.BorderLayout.CENTER);
		
		
		
        PVisualOrdine = new JPanelVisualizzazioneOrdini();
        PVisualProdotti = new JPanelListaProdotti();
        PVisualClienti = new JPanelListaClienti();
        
        JTabbedPaneVisualizzazione.addTab("Visualizzazione Ordine", PVisualOrdine);
        JTabbedPaneVisualizzazione.addTab("Catalogo Prodotti", PVisualProdotti);
        JTabbedPaneVisualizzazione.addTab("Database Clienti", PVisualClienti);
        
} /*Fine costruttore*/

	
}
