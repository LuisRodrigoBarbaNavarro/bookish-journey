package Code;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Scrollbar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Analizador L\u00E9xico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, 14));
		textField.setBounds(53, 110, 247, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setBackground(new Color(0, 0, 0, 0));
		textField.setOpaque(false);  
		textField.setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 196, 411, 229);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton btnAnalizar = new JButton("Analizar");
		btnAnalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        File file = new File("Code.txt");
		        PrintWriter write;
		        /*
		        try {
		            write = new PrintWriter(file);
		            write.print(textField.getText());
		            write.close();
		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		        }
		        */
		        try {
		            Reader reader = new BufferedReader(new FileReader("Code.txt"));
		            Lexer lexer = new Lexer(reader);
		            String result = "";
		            while (true) {
		                Tokens tokens = lexer.yylex();
		                if (tokens == null) {
		                	result += "Fin";
		                    textArea.setText(result);
		                    return;
		                }
		                System.out.println(lexer.lexeme);
		                switch (tokens) {
		                    case Error:
		                    	result += "Error\n";
		                        break;
		                    default:
		                    	result += tokens + "\n";
		                        break;
		                }
		            }
		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (IOException ex) {
		            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		btnAnalizar.setBackground(Color.WHITE);
		btnAnalizar.setFont(new Font("Arial", Font.BOLD, 16));
		btnAnalizar.setForeground(Color.BLACK);
		btnAnalizar.setBounds(366, 116, 89, 23);
		btnAnalizar.setBackground(new Color(0, 0, 0, 0));
		btnAnalizar.setOpaque(false);  
		btnAnalizar.setBorder(null); 
		contentPane.add(btnAnalizar);

		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(View.class.getResource("/Images/lexer-background-view.png")));
		label.setBounds(0, 0, 513, 473);
		contentPane.add(label);
	}
}
