package lab3domaci1;

import java.awt.*;
import java.awt.event.*;

//import pomocni.Igra.ploca;

//import javax.swing.GroupLayout.Alignment;

//import main.Igra;

public class Igra extends Frame {
	
	private Basta basta;
	Basta getBasta() {return basta;}
	
	public Igra() {
		super("igra");
		basta=new Basta(4,4);
		if (basta!=null) System.out.println("Stvorila se basta");
		add(basta);
		setBounds(300, 300, 600, 600);
		
		popuniProzor();
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				 basta.zaustavi();
				dispose();
			}
			
		});
		//basta.pokreni();
		setVisible(true);
		setResizable(true);
	}
	
	private class ploca extends Panel {

		private CheckboxGroup grupa=new CheckboxGroup();
		private Checkbox lako=new Checkbox("Lako",false, grupa);
		private Checkbox srednje=new Checkbox("Srednje",false, grupa);
		private Checkbox tesko=new Checkbox("Tesko",false, grupa);
		ploca(){

		ItemListener osluskivac=new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if ((Checkbox)e.getSource()==lako) {
					basta.setCekanje(1000);
					basta.setBrKoraka(10);
				}
				 if ((Checkbox)e.getSource()==srednje) {
					basta.setCekanje(750);
					basta.setBrKoraka(8);
				}
				 if ((Checkbox)e.getSource()==tesko){
					basta.setCekanje(500);
					basta.setBrKoraka(6);
				}
			}
		};
		lako.addItemListener(osluskivac);
		srednje.addItemListener(osluskivac);
		tesko.addItemListener(osluskivac);
		
		Button kreni=new Button("Kreni");
		add(kreni,BorderLayout.SOUTH);
		
		kreni.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (kreni.getLabel()=="Kreni") {
				basta.pokreni();
				kreni.setLabel("Stani");

				lako.setEnabled(false);
				srednje.setEnabled(false);
				tesko.setEnabled(false);
			}
				else if (kreni.getLabel()=="Stani") {
				basta.zaustavi();
				kreni.setLabel("Kreni");
				lako.setEnabled(true);
				srednje.setEnabled(true);
				tesko.setEnabled(true);			
				}
			}
			
		});
		
		
		setLayout(new GridLayout(11,1));
		Label naslov=new Label("     Tezina:      ");
		naslov.setAlignment(Label.CENTER);
		Font font=new Font(null,Font.BOLD,15);
		naslov.setFont(font);
		add(naslov);
		add(lako); add(srednje); add(tesko); add(kreni); add(new Label());
		Font font1=new Font(null, Font.BOLD,20);
		Label povrce=new Label("Povrce:");
		povrce.setFont(font1);
		povrce.setAlignment(Label.CENTER); add(povrce);
		//System.out.println(String.valueOf(getBasta().getKolicinaPovrca())+ "iz Igra koda");
		//}
		}
	
	
}
	
	private ploca stvari=new ploca();
	public void popuniProzor() {
		add(stvari,"East");
		
	}
	
	public static void main (String[] varg) {
		Igra i=new Igra();
	}

}
