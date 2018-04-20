import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Generador {
	
	public int[][] map;
	
	public int brickWidth;
	public int brickHeight;
	
	public Generador(int filas,int columnas){
		
		map=new int[filas][columnas];
		
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				map[i][j]=1;
			}
		}
		
		brickWidth= (Configuracion.ANCHO -160) / columnas;
		brickHeight= (Configuracion.ALTO - 450) / filas;
	}
	
	public void draw(Graphics2D g){
		for(int i=0;i<map.length;i++){
			for(int j=0;j<map[0].length;j++){
				if(map[i][j]>0){
					g.setColor(Color.WHITE);
					g.fillRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.BLACK);
					g.drawRect(j*brickWidth + 80, i*brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int valor, int file,int columna){
		map[file][columna]=valor;
	}

}
