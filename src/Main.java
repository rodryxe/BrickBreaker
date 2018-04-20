import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame frame=new JFrame();
		Panel panel=new Panel();
		
		frame.setSize(Configuracion.ANCHO, Configuracion.ALTO);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(Configuracion.TITULO);
		frame.add(panel);	
				
	}
}
