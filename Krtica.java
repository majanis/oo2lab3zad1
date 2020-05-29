package lab3domaci1;

import java.awt.*;

public class Krtica extends Zivotinja {

	public Krtica(Rupa rupa) {
		super(rupa);
		boja=Color.DARK_GRAY;
	}

	@Override
	public void ispoljiEfekatUdarene() {
		super.ispoljiEfekatUdarene();
		rupa.zaustavi();
	}

	@Override
	public void ispoljiEfekatPobegle() {
		rupa.getBasta().smanjiPovrce();
	}


//	@Override
//	protected void crtajKonkretnuZivotinju(Graphics g, int w, int h) {
////		g.fillOval((int)(dimenzija*rupa.getX()), (int)(dimenzija*rupa.getY()), (int)(w*dimenzija), (int)(h*dimenzija));
//		g.fillOval(rupa.getX(), rupa.getY(), w, h);
//		System.out.println("krug se crta na" + rupa.getX() + rupa.getY()); //???? ne radi!!!
//	}
	
	

}
