package lab3domaci1;
import java.awt.*;

public abstract class Zivotinja {
	
	protected Rupa rupa;
	protected boolean udarena=false;
	protected Color boja;
	protected double dimenzija;
	

	public Zivotinja(Rupa rupa) {
		this.rupa = rupa;
		dimenzija=rupa.getDimenzija()*(rupa.trenutniKorak/rupa.getBrKoraka());
	}

	public void ispoljiEfekatUdarene() {udarena=true;};
	public abstract void ispoljiEfekatPobegle();
	
//	protected abstract void crtajKonkretnuZivotinju(Graphics g, int w, int h);
//
//	public void crtajZivotinju(Rupa rupa) {
//		Graphics g=rupa.getGraphics();
//		int w=rupa.getWidth();
//		int h=rupa.getHeight();
//		g.setColor(boja);
//		crtajKonkretnuZivotinju(g, w, h);
//	}
//	
	
	public void crtajZivotinju(Rupa rupa) {
		rupa.setBackground(Color.BLUE);
//		Graphics g=rupa.getGraphics();
//		g.setColor(boja);
//		g.fillOval(rupa.getX(), rupa.getY(), rupa.getWidth()*(rupa.trenutniKorak/rupa.getBrKoraka()), rupa.getHeight()*(rupa.trenutniKorak/rupa.getBrKoraka()));
	}
	
}
