package bookworm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddBookWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTitle;
	private JLabel lblGenre;
	private JTextField textGenre;
	private JTextField textEditorial;
	private JLabel lblEditorial;
	private JLabel lblPrice;
	private JTextField textPrice;
	private JLabel lblISBN;
	private JTextField textISBN;
	private JLabel lblEditions;
	private JTextField textEditions;
	private JLabel lblCover;
	private JTextField textCover;
	private JButton btnAdd;
	private JLabel lblIcon;
	Conexion con = new Conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBookWindow frame = new AddBookWindow();
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
	public AddBookWindow() {
		
		con.conectar();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddBookWindow.class.getResource("/assets/brand.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 253, 487);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(222, 184, 135));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 12));
		lblTitle.setBounds(10, 51, 36, 14);
		contentPane.add(lblTitle);
		
		textTitle = new JTextField();
		textTitle.setBounds(49, 48, 167, 20);
		contentPane.add(textTitle);
		textTitle.setColumns(10);
		
		JButton btnExit = new JButton("Close");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExit.setBounds(10, 414, 89, 23);
		contentPane.add(btnExit);
		
		lblGenre = new JLabel("Genre: ");
		lblGenre.setFont(new Font("Arial", Font.BOLD, 12));
		lblGenre.setBounds(10, 79, 46, 14);
		contentPane.add(lblGenre);
		
		textGenre = new JTextField();
		textGenre.setBounds(49, 76, 167, 20);
		contentPane.add(textGenre);
		textGenre.setColumns(10);
		
		textEditorial = new JTextField();
		textEditorial.setBounds(69, 107, 147, 20);
		contentPane.add(textEditorial);
		textEditorial.setColumns(10);
		
		lblEditorial = new JLabel("Editorial:");
		lblEditorial.setFont(new Font("Arial", Font.BOLD, 12));
		lblEditorial.setBounds(10, 110, 54, 14);
		contentPane.add(lblEditorial);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Arial", Font.BOLD, 12));
		lblPrice.setBounds(10, 141, 46, 14);
		contentPane.add(lblPrice);
		
		textPrice = new JTextField();
		textPrice.setBounds(49, 138, 167, 20);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		lblISBN = new JLabel("ISBN:");
		lblISBN.setFont(new Font("Arial", Font.BOLD, 12));
		lblISBN.setBounds(10, 169, 46, 14);
		contentPane.add(lblISBN);
		
		textISBN = new JTextField();
		textISBN.setBounds(49, 166, 167, 20);
		contentPane.add(textISBN);
		textISBN.setColumns(10);
		
		lblEditions = new JLabel("Editions:");
		lblEditions.setFont(new Font("Arial", Font.BOLD, 12));
		lblEditions.setBounds(10, 200, 54, 14);
		contentPane.add(lblEditions);
		
		textEditions = new JTextField();
		textEditions.setBounds(69, 197, 147, 20);
		contentPane.add(textEditions);
		textEditions.setColumns(10);
		
		lblCover = new JLabel("Cover:");
		lblCover.setFont(new Font("Arial", Font.BOLD, 12));
		lblCover.setBounds(10, 225, 46, 14);
		contentPane.add(lblCover);
		
		textCover = new JTextField();
		textCover.setBounds(49, 225, 167, 20);
		contentPane.add(textCover);
		textCover.setColumns(10);
		
		btnAdd = new JButton("Add book");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Books book = new Books(textTitle.getText(), textGenre.getText(), textEditorial.getText(), Integer.parseInt(textPrice.getText()), 
				textISBN.getText(), textEditions.getText(), textCover.getText());
				
				if(textTitle.getText() == null || textGenre.getText() == null || textEditorial.getText() == null || textPrice.getText() == null || textISBN.getText() == null
						|| textEditions.getText() == null || textCover.getText() == null) {
					JOptionPane.showMessageDialog(null, "No pueden haber campos vacios");
				}
				con.addBook(book);
			}
		});
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdd.setBounds(10, 273, 89, 23);
		contentPane.add(btnAdd);
		
		lblIcon = new JLabel("New label");
		lblIcon.setBounds(0, 0, 46, 40);
		contentPane.add(lblIcon);
		
		loadImage();
		
	}
	
	void loadImage(){
        ImageIcon imagen = new ImageIcon("src/assets/brand.png"); 
        imagen.setImage(imagen.getImage().getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH));
        lblIcon.setIcon(imagen);
    }
	
}
