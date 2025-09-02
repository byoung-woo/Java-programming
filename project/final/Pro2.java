
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.*;

public class Pro2 extends JFrame{
	private JTextArea textArea;
	private JButton countButton;
	private JTextArea CountText;
	private JLabel insertLabel;
	
	public Pro2() {
		setTitle("���� ��, �ܾ� ��, ���� �� ���");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea= new JTextArea(10, 30);
		countButton = new JButton("����/�ܾ�/���� �� ���");
		CountText = new JTextArea("0���� 0�ܾ� 0��",1, 15);
		insertLabel = new JLabel("�Ʒ� �ؽ�Ʈ ������ ������ �����ÿ�.");
		
		countButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				int wordCount = countWords(text);
				int charCount = countCharacters(text);
				int lineCount = countLines(text);
				
				CountText.setText(charCount+"���� "+wordCount+"�ܾ� "+lineCount+"��");

			}
		});
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		JPanel panel0 = new JPanel();
		panel0.add(insertLabel);
		
		JPanel panel = new JPanel();
		panel.add(countButton);
		panel.add(CountText);
		
		add(panel0, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
		pack();
		setLocation(200,300);
		setSize(500, 415);
		setLocationRelativeTo(null);
	}
	
	private int countWords(String text) {
		StringTokenizer tokenizer = new StringTokenizer(text);
		return tokenizer.countTokens();
	}
	
	private int countCharacters(String text) {
		return text.replaceAll("\\s+","").length();
	}
	
	private int countLines(String text) {
		return text.split("\n").length;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				new Pro2().setVisible(true);
			}
		});
	}

	
}


