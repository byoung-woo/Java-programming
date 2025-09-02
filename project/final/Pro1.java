
import java.awt.event.*;
import javax.swing.*;
import java.awt.color.*;
import java.awt.Color;
import java.awt.GridLayout;

public class Pro1 extends JFrame implements ActionListener{
	private JProgressBar progressBar1;
	private JProgressBar progressBar2;
	private JProgressBar progressBar3;
	private JProgressBar progressBar4;
	private JProgressBar progressBar5;
 
	private JButton voteButton1;
	private JButton voteButton2;
	private JButton voteButton3;
	private JButton voteButton4;
	private JButton voteButton5;
 
	private int totalVotes = 0;
	private int votesFor1 = 0;
	private int votesFor2 = 0; 
	private int votesFor3 = 0;
	private int votesFor4 = 0;
	private int votesFor5 = 0;
 
	public Pro1() {
		super("을사오적 인지도");
 
		voteButton1 = new JButton("이완용");
		voteButton2 = new JButton("이근택");
		voteButton3 = new JButton("이지용");
		voteButton4 = new JButton("박제순");
		voteButton5 = new JButton("권중현");
 
		progressBar1 = new JProgressBar(SwingConstants.VERTICAL, 0, 1000);
		progressBar1.setValue(0);
		progressBar1.setStringPainted(true);
 
		progressBar2 = new JProgressBar(SwingConstants.VERTICAL, 0, 1000);
		progressBar2.setValue(0);
		progressBar2.setStringPainted(true);
 
		progressBar3 = new JProgressBar(SwingConstants.VERTICAL, 0, 1000);
		progressBar3.setValue(0);
		progressBar3.setStringPainted(true);
 
		progressBar4 = new JProgressBar(SwingConstants.VERTICAL, 0, 1000);
		progressBar4.setValue(0);
		progressBar4.setStringPainted(true);
 
		progressBar5 = new JProgressBar(SwingConstants.VERTICAL, 0, 1000);
		progressBar5.setValue(0);
		progressBar5.setStringPainted(true);
 
		JPanel panel1 = new JPanel(new GridLayout(1,3));
		JPanel panel2 = new JPanel(new GridLayout(1,3));
		
		panel1.add(voteButton1);
		panel1.add(voteButton2);
		panel1.add(voteButton3);
		panel1.add(voteButton4);
		panel1.add(voteButton5);
		
		panel2.add(progressBar1);
		panel2.add(progressBar2);
		panel2.add(progressBar3);
		panel2.add(progressBar4);
		panel2.add(progressBar5);
		
		add(panel1, "South");
		add(panel2, "Center");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocation(200,300);
		setSize(500, 400);
		setVisible(true);
		
		progressBar1.setForeground(Color.red);
		progressBar2.setForeground(Color.green);
		progressBar3.setForeground(Color.blue);
		progressBar4.setForeground(Color.cyan);
		progressBar5.setForeground(Color.magenta);
		
		voteButton1.addActionListener(this);
		voteButton2.addActionListener(this);
		voteButton3.addActionListener(this);
		voteButton4.addActionListener(this);
		voteButton5.addActionListener(this);
	}
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource()==voteButton1) {
			votesFor1++;
		} else if(evt.getSource()==voteButton2) {
			votesFor2++;
		} else if(evt.getSource()==voteButton3) {
			votesFor3++;
		} else if(evt.getSource()==voteButton4) {
			votesFor4++;
		} else if(evt.getSource()==voteButton5) {
			votesFor5++;
		}
		totalVotes++;
		updateProgressBar(progressBar1, votesFor1);
		updateProgressBar(progressBar2, votesFor2);
		updateProgressBar(progressBar3, votesFor3);
		updateProgressBar(progressBar4, votesFor4);
		updateProgressBar(progressBar5, votesFor5);
	}
	private void updateProgressBar(JProgressBar progressBar, int votes) {
		double percentage = ((double) votes/ totalVotes)*1000;
		String formattedPercentage = String.format("%.1f", percentage / 10);
		progressBar.setValue((int)(Double.parseDouble(formattedPercentage))* 10);
		progressBar.setString(formattedPercentage + "%");
	}
	public static void main(String[] args) {
		new Pro1();
	}
}
