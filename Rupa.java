package lab3domaci1;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Rupa extends Canvas implements Runnable {

	private Basta basta;
	private Zivotinja zivotinja;
	private Thread nit;
	private boolean radi = false;
	private int brKoraka; // flag
	int trenutniKorak = 1;
	private int dimenzija = (int) getWidth() * (int) getHeight();
	boolean slobodna;
	private boolean startovana = false;

	public int getDimenzija() {
		return dimenzija;
	}

	public Basta getBasta() {
		return basta;
	}

	public int getBrKoraka() {
		return brKoraka;
	}

	public void setBrKoraka(int brKoraka) {
		this.brKoraka = brKoraka;
	}

	public synchronized Zivotinja getZivotinja() {
		return zivotinja;
	}

	public synchronized void setZivotinja(Zivotinja zivotinja) {
		this.zivotinja = zivotinja;
		slobodna = false;
	}

	public Rupa(Basta basta) {
		this.basta = basta;
		setZivotinja(null);
		slobodna = true;
	}

	public synchronized void pokreni() {
		if (nit == null)
			return;
		if (nitPokrenuta())
			return;
		if (startovana == false)
			nit.start();
		System.out.println("POKRENUTA NIT RUPE ");
		radi = true;
		startovana = true;
		notifyAll();
	}

	public synchronized void stvori() {
		if (nit != null)
			return;
		nit = new Thread(this);
		radi = false;
		System.out.println("Nit rupe je stvorena");
	}

	public synchronized void zaustavi() {
		if (!nitPokrenuta() || nit == null)
			return;
		nit.interrupt();
		radi = false;
		if (zivotinja.udarena == false)
			zivotinja.ispoljiEfekatPobegle();
		setZivotinja(null);
		slobodna = true;
		notifyAll(); // obavestava bastu da je nit zaustavljena
		System.out.println("*************Nit rupe je zaustavljena!");
	}

	public boolean nitPokrenuta() {
		return radi;
	}

	public synchronized void zgaziRupu() {
		if (zivotinja == null)
			return;
		zivotinja.ispoljiEfekatUdarene();
		// slobodna=true;
		// notifyAll(); //nije potrebno jer se radi u metodi zaustavi, a u Basti se
		// stvara nova nit svakako
	}

	@Override
	public synchronized void paint(Graphics g) {
		// if (this.zivotinja==null)setBackground(new Color(210, 105, 15)); //flag!
		setBackground(new Color(210, 105, 15));
		if (this.zivotinja != null) {
			zivotinja.crtajZivotinju(this);
			// System.out.println("Pozvano je crtanje zivotinje");
		}
		revalidate(); // FLAG
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				while (!radi)
					wait();
			}
			while (!Thread.interrupted()) {
				while (trenutniKorak <= getBrKoraka()) {
					repaint();
					// synchronized(this)
					trenutniKorak++;
					Thread.sleep(100);
				}
				System.out.println("Prosao je broj koraka!*********" + getBrKoraka());
				Thread.sleep(2000);
				zaustavi();
				System.out.println("Nit je zaustavljena jer je kraj njenog run!*********");
				repaint();
			}
		} catch (InterruptedException g) {
		}
	}

}
