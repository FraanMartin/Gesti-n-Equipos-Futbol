package paquete;

import java.awt.*;

import javax.swing.*;

public class Panel extends JPanel {
	
	public Image fondo = new ImageIcon("logoliga.png").getImage();
	private final int PANEL_ALTO = 130;
	private final int PANEL_ANCHO = 800;

	public Panel() {
		this.setLayout(null);
		this.setSize(PANEL_ANCHO, PANEL_ALTO);
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(fondo, 0, 0, null);
	}
	
}
