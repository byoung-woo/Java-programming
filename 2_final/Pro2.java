
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
		setTitle("문자 수, 단어 수, 라인 수 계산");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textArea= new JTextArea(10, 30);
		countButton = new JButton("문자/단어/라인 수 계산");
		CountText = new JTextArea("0문자 0단어 0줄",1, 15);
		insertLabel = new JLabel("아래 텍스트 영역에 문장을 넣으시오.");
		
		countButton.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				String text = textArea.getText();
				int wordCount = countWords(text);
				int charCount = countCharacters(text);
				int lineCount = countLines(text);
				
				CountText.setText(charCount+"문자 "+wordCount+"단어 "+lineCount+"줄");

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


