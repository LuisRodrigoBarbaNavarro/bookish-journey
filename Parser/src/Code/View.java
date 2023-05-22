package Code;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java_cup.runtime.Symbol;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setTitle("Analizador Sint\u00E1ctico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 173, 291, 224);
		contentPane.add(scrollPane);
		
		JTextArea uploadTextArea = new JTextArea();
		uploadTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane.setViewportView(uploadTextArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(421, 173, 291, 224);
		contentPane.add(scrollPane_1);
		
		JTextArea lexerTextArea = new JTextArea();
		lexerTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollPane_1.setViewportView(lexerTextArea);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(64, 540, 649, 52);
		contentPane.add(scrollPane_2);
		
		JTextArea parserTextArea = new JTextArea();
		parserTextArea.setFont(new Font("Arial", Font.BOLD, 14));
		scrollPane_2.setViewportView(parserTextArea);
		
		JButton uploadButton = new JButton("");
		uploadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        JFileChooser chooser = new JFileChooser();
		        chooser.showOpenDialog(null);
		        File file = new File(chooser.getSelectedFile().getAbsolutePath());
		        
		        try {
		            String string = new String(Files.readAllBytes(file.toPath()));
		            lexerTextArea.setText(string);
		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		        } catch (IOException ex) {
		            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		uploadButton.setContentAreaFilled(false);
		uploadButton.setBorderPainted(false);
		uploadButton.setFocusPainted(false);
		uploadButton.setFocusTraversalKeysEnabled(false);
		uploadButton.setFocusable(false);
		uploadButton.setIcon(new ImageIcon(View.class.getResource("/Images/file.png")));
		uploadButton.setBounds(332, 97, 60, 60);
		uploadButton.setBackground(Color.black);
		uploadButton.setBorder(null);
		uploadButton.setOpaque(false);
		contentPane.add(uploadButton);
		
		JButton clearButton_1 = new JButton("");
		clearButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadButton.setText(null);
				lexerTextArea.setText(null);
			}
		});
		clearButton_1.setContentAreaFilled(false);
		clearButton_1.setBorderPainted(false);
		clearButton_1.setFocusPainted(false);
		clearButton_1.setFocusTraversalKeysEnabled(false);
		clearButton_1.setFocusable(false);
		clearButton_1.setIcon(new ImageIcon(View.class.getResource("/Images/trash-can.png")));
		clearButton_1.setBounds(692, 97, 60, 60);
		clearButton_1.setBackground(Color.black);
		clearButton_1.setBorder(null);
		clearButton_1.setOpaque(false);
		contentPane.add(clearButton_1);
		
		JButton clearButton_2 = new JButton("");
		clearButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parserTextArea.setText(null);
			}
		});
		clearButton_2.setContentAreaFilled(false);
		clearButton_2.setBorderPainted(false);
		clearButton_2.setFocusPainted(false);
		clearButton_2.setFocusTraversalKeysEnabled(false);
		clearButton_2.setFocusable(false);
		clearButton_2.setIcon(new ImageIcon(View.class.getResource("/Images/trash-can.png")));
		clearButton_2.setBounds(692, 469, 60, 60);
		clearButton_2.setBackground(Color.black);
		clearButton_2.setBorder(null);
		clearButton_2.setOpaque(false);
		contentPane.add(clearButton_2);
		
		JButton analysisButton_2 = new JButton("Analizar");
		analysisButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String string = uploadTextArea.getText();
		        Sintax s = new Sintax(new Code.LexerCup(new StringReader(string)));
		        
		        try {
		            s.parse();
		            parserTextArea.setText("Análisis sintáctico realizado exitosamente.");
		            parserTextArea.setForeground(new Color(25, 111, 61));
		        } catch (Exception ex) {
		            Symbol sym = s.getSymbol();
		            parserTextArea.setText("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Valor: \"" + sym.value + "\"");
		            parserTextArea.setForeground(Color.red);
		        }
			}
		});
		analysisButton_2.setContentAreaFilled(false);
		analysisButton_2.setBorderPainted(false);
		analysisButton_2.setFocusPainted(false);
		analysisButton_2.setFocusTraversalKeysEnabled(false);
		analysisButton_2.setFocusable(false);
		analysisButton_2.setForeground(Color.WHITE);
		analysisButton_2.setFont(new Font("Arial", Font.BOLD, 18));
		analysisButton_2.setBounds(577, 436, 103, 35);
		analysisButton_2.setBackground(Color.BLACK);
		analysisButton_2.setBorder(null);
		analysisButton_2.setOpaque(false);
		contentPane.add(analysisButton_2);
		
		JButton analysisButton_1 = new JButton("Analizar");
		analysisButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
		            int c = 1;
		            String expr = (String) uploadTextArea.getText();
		            Lexer lexer = new Lexer(new StringReader(expr));
		            String result = "Línea " + c + "\n";
		            
		            while (true) {
		                Tokens token = lexer.yylex();
		                if (token == null) {
		                    lexerTextArea.setText(result);
		                    return;
		                }
		                switch (token) {
		                    case tk_salto_linea:
		                        c++;
		                        result += "Línea " + c + "\n";
		                        break;
		                    default:
		                    	result += "    < " + token + ", "+ lexer.lexeme + " >\n";
		                        break;
		                }
		            }
		        } catch (IOException ex) {
		            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
		        }
			}
		});
		analysisButton_1.setContentAreaFilled(false);
		analysisButton_1.setBorderPainted(false);
		analysisButton_1.setFocusPainted(false);
		analysisButton_1.setFocusTraversalKeysEnabled(false);
		analysisButton_1.setFocusable(false);
		analysisButton_1.setForeground(Color.WHITE);
		analysisButton_1.setFont(new Font("Arial", Font.BOLD, 18));
		analysisButton_1.setBounds(577, 60, 103, 35);
		analysisButton_1.setBackground(Color.black);
		analysisButton_1.setBorder(null);
		analysisButton_1.setOpaque(false);
		contentPane.add(analysisButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(View.class.getResource("/Images/parser-background-view.png")));
		lblNewLabel.setBounds(0, 0, 779, 592);
		contentPane.add(lblNewLabel);
	}
}
