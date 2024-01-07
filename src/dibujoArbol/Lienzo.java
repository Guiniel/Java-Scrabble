package dibujoArbol;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import estructuras.Arbol;
import estructuras.NodoBinario;

/**
 * En esta clase se especifican los parametros para dibujar el arbol
 *
 */
public class Lienzo extends JPanel{

	private Arbol arbolito;
	/**
	 * Declaracion del diametro
	 */
	public static final int DIAMETRO = 30;
	/**
	 * Declaracion del Radio
	 */
	public static final int RADIO= DIAMETRO/2;
	/**
	 * Declaracion del Ancho
	 */
	public static final int ANCHO = 50;
 
	/**
	 * Cuando modificamos el arbol, lo repintamos en el panel
	 * @param a Arbol a pintar
	 */
	public void setArbolito(Arbol a) {
		this.arbolito=a;
		repaint(); 
	}
	
	/**
	 * Metodo que llama al metodo pintar
	 */
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(new Color(245, 222, 179));
		pintar(g, getWidth()/2, 20, arbolito.getRaiz() );
	}
	
	/**
	 * Esto es lo que pinta el arbol
	 * @param g objeto grafico
	 * @param x	coordenadas en x
	 * @param y coordenadas en y
	 * @param n nodo a pintar
	 */
	private void pintar(Graphics g, int x, int y, NodoBinario n) {
		if (n==null) {
			return;
		}
		else {
			int extra = n.nodosCompletos(n)* (ANCHO/2);
			g.setColor(Color.RED);
			g.drawOval(x,y, DIAMETRO, DIAMETRO);
			g.setColor(Color.BLACK);
			g.drawString(n.getLetra(), x+12, y+18);
			if (n.getHizq()!=null) {
				g.setColor(Color.blue);
				g.drawLine(x+RADIO, y+ RADIO, x - ANCHO-extra, y+ANCHO+RADIO);
			}
			if (n.getHder()!=null) {
				g.setColor(Color.blue);
				g.drawLine(x+RADIO, y+ RADIO, x + ANCHO+extra, y+ANCHO+RADIO);
			}
			pintar(g,x-ANCHO-extra,y+ANCHO,n.getHizq());
			pintar(g,x+ANCHO+extra,y+ANCHO,n.getHder());
		}
	}
	
	 
}
