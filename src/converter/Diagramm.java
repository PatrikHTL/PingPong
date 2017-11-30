package converter;

import java.awt.Component;
import java.awt.Graphics;

public class Diagramm {
	// Basis der Zeichnung
	private Component base;
	// Randbereich in %
	private int border_percent;
	// xmin, xmax, deltax
	private double xmin;
	private double xmax;
	// ymin, ymax
	private double ymin;
	private double ymax;
	// Flag für Linienbeginn
	private boolean linestart = true;
	// Merker für letzten Linienpunkt
	private int x_last;
	private int y_last;

	public Diagramm(Component base, int border_percent, double xmin, double xmax, double ymin, double ymax) {
		this.base = base;
		this.border_percent = border_percent;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}

	public Diagramm(Component base) {
		this(base, 5, -1, 1, -1, 1);
	}

	private int xcoord_to_pixel(double x_coord) {
		int gesamtpixel = base.getWidth();
		int pix_rand = (gesamtpixel * border_percent) / 100;
		int pix_zeichen = gesamtpixel - 2 * pix_rand;
		if (x_coord < xmin) {
			return -1;
		} else if (x_coord > xmax) {
			return -1;
		} else {
			double xcoord_relat = x_coord - xmin;
			double x_bereich = xmax - xmin;
			int pix = (int) Math.round((double) pix_zeichen * xcoord_relat / x_bereich);
			int pix_gesamt = pix_rand + pix;
			return pix_gesamt;
		}
	}

	private int ycoord_to_pixel(double y_coord) {
		int gesamtpixel = base.getHeight();
		int pix_rand = (gesamtpixel * border_percent) / 100;
		int pix_zeichen = gesamtpixel - 2 * pix_rand;
		if (y_coord < ymin) {
			return -1;
		} else if (y_coord > ymax) {
			return -1;
		} else {
			double ycoord_relat = y_coord - ymin;
			double y_bereich = ymax - ymin;
			int pix = (int) Math.round((double) pix_zeichen * ycoord_relat / y_bereich);
			int pix_gesamt = pix_rand + (pix_zeichen - pix);
			return pix_gesamt;
		}
	}

	private void val_drawLine(Graphics g, double x0, double y0, double x1, double y1) {
		int xpix0 = xcoord_to_pixel(x0);
		int xpix1 = xcoord_to_pixel(x1);
		int ypix0 = ycoord_to_pixel(y0);
		int ypix1 = ycoord_to_pixel(y1);

		g.drawLine(xpix0, ypix0, xpix1, ypix1);
	}

	public void draw_coord(Graphics g) {
		// Zeichnen des Koordinatenkreuzes
		val_drawLine(g, 0.0, ymax, 0.0, ymin);
		val_drawLine(g, xmin, 0.0, xmax, 0.0);
		// Zeichnen der Skalierungsmarken
		double x_mark = (xmax - xmin) / 200.0;
		val_drawLine(g, -x_mark, 1.0, x_mark, 1.0);
		val_drawLine(g, -x_mark, 0.5, x_mark, 0.5);
		val_drawLine(g, -x_mark, -0.5, x_mark, -0.5);
		val_drawLine(g, -x_mark, -1.0, x_mark, -1.0);
		double y_mark = (ymax - ymin) / 100.0;
		double x_pos = Math.PI / 2.0;
		while (x_pos < xmax) {
			val_drawLine(g, x_pos, y_mark, x_pos, -y_mark);
			x_pos += Math.PI / 2.0;
		}
	}
	
	// Beginn einer neuen Diagrammlinie
	public void newLine() {
		linestart = true;
	}

	// Zeichne Diagrammlinie zu neuem Punkt
	public void nextLine(Graphics g, double x, double y) {
		int x_pix = xcoord_to_pixel(x);
		int y_pix = ycoord_to_pixel(y);
		if (x_pix >= 0 && y_pix >= 0) {  // wenn Koordinaten gültig
			if (linestart) {
				linestart = false;
			} else {
				g.drawLine(x_last, y_last, x_pix, y_pix);
			}
			x_last = x_pix;
			y_last = y_pix;
		}
	}
}
