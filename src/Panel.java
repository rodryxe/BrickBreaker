import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener, ActionListener {

	private static final long serialVersionUID = 1L;

	private boolean running = false;

	private int puntos = 0;

	private int bloques = Configuracion.NUM_BLOQUES;

	private Timer timer;

	private int jugadorX = Configuracion.INICIO_JUGADOR;

	private int pelotaX = Configuracion.INICIO_PELOTA_X;
	private int pelotaY = Configuracion.INICIO_PELOTA_Y;
	private int pelotadirX = Configuracion.VEL_PEL_X;
	private int pelotadirY = Configuracion.VEL_PEL_Y;

	private Generador map;

	public Panel() {

		map = new Generador(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(Configuracion.DELAY, this);
		timer.start();

	}

	@Override
	public void paint(Graphics g) {

		// background

		g.setColor(Color.BLACK);
		g.fillRect(1, 1, Configuracion.ANCHO - 8, Configuracion.ALTO - 8);

		// bloques

		map.draw((Graphics2D) g);

		// bordes

		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 3, Configuracion.ALTO - 8);
		g.fillRect(0, 0, Configuracion.ANCHO - 8, 3);
		g.fillRect(Configuracion.ANCHO - 9, 0, 3, Configuracion.ALTO - 8);

		// puntuacion

		g.setColor(Color.WHITE);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + puntos, Configuracion.POS_PUNTOS_X, Configuracion.POS_PUNTOS_Y);

		// raqueta

		g.setColor(Color.GREEN);
		g.fillRect(jugadorX, Configuracion.POSICION_Y_JUG, Configuracion.TAM_JUG_X, Configuracion.TAM_JUG_Y);

		// pelota

		g.setColor(Color.YELLOW);
		g.fillOval(pelotaX, pelotaY, Configuracion.TAM_PEL_X, Configuracion.TAM_PEL_Y);

		if (bloques <= 0) {
			running = false;
			pelotadirX = 0;
			pelotadirY = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 35));
			g.drawString("YOU WIN :3", Configuracion.ANCHO / 2 - 200, Configuracion.ALTO / 2 + 30);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Presiona Intro para resetear.", Configuracion.ANCHO / 2 - 120, Configuracion.ALTO / 2 + 60);
		}

		if (pelotaY > Configuracion.ALTO) {
			running = false;
			pelotadirX = 0;
			pelotadirY = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 35));
			g.drawString("Game Over con: " + puntos + " puntos.", Configuracion.ANCHO / 2 - 220,
					Configuracion.ALTO / 2 + 30);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Presiona Intro para resetear.", Configuracion.ANCHO / 2 - 120, Configuracion.ALTO / 2 + 60);
		}

		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {

		timer.start();

		if (running) {

			if (new Rectangle(pelotaX, pelotaY, Configuracion.TAM_PEL_X, Configuracion.TAM_PEL_Y)
					.intersects(new Rectangle(jugadorX, Configuracion.POSICION_Y_JUG, Configuracion.TAM_JUG_X,
							Configuracion.TAM_JUG_Y))) {
				pelotadirY = -pelotadirY;
			}
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;

						Rectangle brickrect = new Rectangle(brickX, brickY, map.brickWidth, map.brickHeight);
						Rectangle pelotarect = new Rectangle(pelotaX, pelotaY, Configuracion.TAM_PEL_X,
								Configuracion.TAM_PEL_Y);

						if (pelotarect.intersects(brickrect)) {
							puntos += Configuracion.PUNTOS_BLOQUE;
							map.setBrickValue(0, i, j);
							bloques--;

							if (pelotaX - 1 + Configuracion.TAM_PEL_X <= brickrect.x
									|| pelotaX + 1 >= brickrect.x + brickrect.width) {
								pelotadirX = -pelotadirX;
							} else {
								pelotadirY = -pelotadirY;
							}
							break A;
						}

					}
				}
			}
			pelotaX += pelotadirX;
			pelotaY += pelotadirY;

			if (pelotaX < 0) {
				pelotadirX = -pelotadirX;
			}
			if (pelotaY < 0) {
				pelotadirY = -pelotadirY;
			}
			if (pelotaX > Configuracion.ANCHO - Configuracion.TAM_PEL_X - 10) {
				pelotadirX = -pelotadirX;
			}

		}
		repaint();

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (jugadorX >= 600) {
				jugadorX = 600;
			} else {
				moverDerecha();
			}

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (jugadorX <= 10) {
				jugadorX = 10;
			} else {
				moverIzquierda();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!running) {

				running = true;
				puntos = 0;
				bloques = Configuracion.NUM_BLOQUES;
				jugadorX = Configuracion.INICIO_JUGADOR;

				pelotaX = ((int) new Random().nextInt(600)) + 50;
				pelotaY = ((int) new Random().nextInt(250)) + 330;
				pelotadirX = Configuracion.VEL_PEL_X;
				pelotadirY = Configuracion.VEL_PEL_Y;

				map = new Generador(3, 7);
				repaint();

			}
		}

	}

	public void moverDerecha() {
		running = true;
		jugadorX += Configuracion.VEL_JUG;
	}

	public void moverIzquierda() {
		running = true;
		jugadorX -= Configuracion.VEL_JUG;
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}

}
