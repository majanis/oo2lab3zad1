package lab3domaci1;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Basta extends Panel implements Runnable {
	
	private int kolicinaPovrca=100; //u slucaju da treba da se svaki pritisak dugmeta kreni vodi kao nova igra, staviti ovo u pokreni
	private double cekanje;
	private Rupa[][] matrica;
	private int brojKoraka;
	private Thread nit;
	private int vrste, kolone;
	
	public int getBrojKoraka() {
		return brojKoraka;
	}

	public void setCekanje(double cekanje) {
		this.cekanje = cekanje;
	}
	
	public int getKolicinaPovrca() {return kolicinaPovrca;}

	public void setBrKoraka(int brojKoraka) {
		this.brojKoraka = brojKoraka;
		for (int i=0; i<vrste; i++)
			for (int j=0; j<kolone; j++) {
				//if (matrica[i][j].nitPokrenuta())
				matrica[i][j].setBrKoraka(brojKoraka);
				matrica[i][j].setVisible(true);
	}
	}
	
	public Basta (int vrste, int kolone) {
		super(new GridLayout(vrste, kolone));
		this.vrste=vrste; this.kolone=kolone;
		setBackground(Color.GREEN);
		matrica=new Rupa[vrste][kolone];

		for (int i=0; i<vrste; i++)
			for (int j=0; j<kolone; j++) {
				Rupa r=matrica[i][j]=new Rupa(this);
				add(r);
				r.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						r.zgaziRupu();
					}
				});	
			
			}
		setLayout(new GridLayout(vrste, kolone, 15, 15));
		repaint(); //flag
		//pokreni();
	}
	
	public void smanjiPovrce() {
		kolicinaPovrca--;
		if (kolicinaPovrca==0) zaustavi();
	}
	
	public synchronized void pokreni() {
		System.out.println("BASTA SE POKRENULA");
		//kolicinaPovrca=100;
		nit=new Thread(this);
		nit.start();
		notifyAll();
	}
	
	public synchronized void zaustavi() {
		if (nit!=null) {
		for (int i=0; i<vrste; i++)
			for (int j=0; j<kolone; j++)
				//if (matrica[i][j].nitPokrenuta())
				matrica[i][j].zaustavi();
		nit.interrupt();
		System.out.println("BASTA SE ZAUSTAVILA");
		nit=null;
	}
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i=0; i<vrste; i++)
			for (int j=0; j<kolone; j++) {
				matrica[i][j].revalidate();
				matrica[i][j].repaint();
			}
	}

//	@Override
//	public void run() {
//		try {
//			for (int i=0; i<vrste; i++)
//				for (int j=0; j<kolone; j++)
//				{
//					if (matrica[i][j].slobodna==true) {
//						Krtica k=new Krtica(matrica[i][j]);
//						matrica[i][j].setZivotinja(k);
//						System.out.println("Krtica je dodata rupi " + i + j);
//						matrica[i][j].stvori();
//						matrica[i][j].pokreni();
//
//						revalidate();
//						repaint();
//						Thread.sleep((long)cekanje);
//						this.setCekanje(cekanje*0.99);
//						System.out.println("Povrce: " + kolicinaPovrca);
//					}
////				}
////			repaint();
////			Thread.sleep((long)cekanje);
////			this.setCekanje(cekanje*0.99);
//				}
//				} catch(InterruptedException g) {}
//	}
	
	private int generisiRandomVrstu() {
		return (int)(Math.random()*vrste);
	}
	
	private int generisiRandomKolonu() {
		return (int)(Math.random()*kolone);
		}
	
	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
			int i=generisiRandomVrstu();
			int j=generisiRandomKolonu();
			if (matrica[i][j].getZivotinja()==null) {
				System.out.println("Izabrana je rupa" +i +j);
				Krtica k=new Krtica(matrica[i][j]);
				matrica[i][j].setZivotinja(k);
				System.out.println("Krtica je dodata rupi " + i + j);
				matrica[i][j].stvori();
				matrica[i][j].pokreni();

				revalidate();
				repaint();
				Thread.sleep((long)cekanje);
				this.setCekanje(cekanje*0.99); //flag
				System.out.println("Povrce: " + kolicinaPovrca);
			}
			}
			
		} catch(InterruptedException g) {}
	}
	
	
	

//	@Override
//	public void run() {
//		try {
//			synchronized (this) {while (nit==null) wait();}
//			while (!Thread.interrupted()) {
//			namestanje: while (true) {
//				int indeksVrsta=(int)Math.random()*vrste;
//				int indeksKolona=(int)Math.random()*kolone;
//				if (matrica[indeksVrsta][indeksKolona].slobodna==true) {
//					Krtica k=new Krtica(matrica[indeksVrsta][indeksKolona]);
//					matrica[indeksVrsta][indeksKolona].setZivotinja(k);
//					matrica[indeksVrsta][indeksKolona].stvori();
//					matrica[indeksVrsta][indeksKolona].pokreni();
//					break namestanje;
//			}
//			}
//				repaint();
//				Thread.sleep((long)(cekanje));
//				this.setCekanje(cekanje-0.01*cekanje);
//			}
//		} catch (InterruptedException g) {}
//		
//	}
	
	

	
	
	
	
	
	
	
}
