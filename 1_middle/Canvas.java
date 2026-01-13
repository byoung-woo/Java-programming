package 박병우exam;
import java.awt.*;
import java.awt.Canvas;

public class CanvasExam{

	public static void main(String[] args){
		Frame f=new Frame("Canvas");
		
		MyCanvas canvas=new MyCanvas();
		
		Color bgColor =new Color(0,110,0);
		canvas.setBackground(bgColor);
		
		f.add(canvas);
	
		f.setSize(1000,600);
		f.setVisible(true);
	}
}

class MyCanvas extends Canvas
{
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.fillOval(300,150,300,300);
	}
}
