package bookworm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class EditWindow extends JFrame {

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
	private JButton btnEdit;
	private JLabel lblIcon;
	Conexion con = new Conexion();

	/**
	 * Create the frame.
	 * @param selectedBook 
	 */
	public EditWindow(Books selectedBook) {
		
		con.conectar();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditWindow.class.getResource("/assets/brand.png")));
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
		
		textTitle = new JTextField(selectedBook.getTitle());
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
		
		textGenre = new JTextField(selectedBook.getGenre());
		textGenre.setBounds(49, 76, 167, 20);
		contentPane.add(textGenre);
		textGenre.setColumns(10);
		
		textEditorial = new JTextField(selectedBook.getEditorial());
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
		
		textPrice = new JTextField(String.valueOf(selectedBook.getPrice()));
		textPrice.setBounds(49, 138, 167, 20);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		lblISBN = new JLabel("ISBN:");
		lblISBN.setFont(new Font("Arial", Font.BOLD, 12));
		lblISBN.setBounds(10, 169, 46, 14);
		contentPane.add(lblISBN);
		
		textISBN = new JTextField(selectedBook.getISBN());
		textISBN.setBounds(49, 166, 167, 20);
		contentPane.add(textISBN);
		textISBN.setColumns(10);
		
		lblEditions = new JLabel("Editions:");
		lblEditions.setFont(new Font("Arial", Font.BOLD, 12));
		lblEditions.setBounds(10, 200, 54, 14);
		contentPane.add(lblEditions);
		
		textEditions = new JTextField(selectedBook.getEditions());
		textEditions.setBounds(69, 197, 147, 20);
		contentPane.add(textEditions);
		textEditions.setColumns(10);
		
		lblCover = new JLabel("Cover:");
		lblCover.setFont(new Font("Arial", Font.BOLD, 12));
		lblCover.setBounds(10, 225, 46, 14);
		contentPane.add(lblCover);
		
		textCover = new JTextField(selectedBook.getCover());
		textCover.setBounds(49, 225, 167, 20);
		contentPane.add(textCover);
		textCover.setColumns(10);
		
		btnEdit = new JButton("Edit book");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(emptyFields()) {
						JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio.");
						return;
					}
					
		            int id = selectedBook.getID();
		            String title = textTitle.getText();
		            String genre = textGenre.getText();
		            String editorial = textEditorial.getText();
		            int price = Integer.parseInt(textPrice.getText());
		            String isbn = textISBN.getText();
		            String editions = textEditions.getText();
		            String cover = textCover.getText();

		            int result = con.updateBook(id, title, genre, editorial, price, isbn, editions, cover);

		            if (result > 0) {
		                JOptionPane.showMessageDialog(null, "Cambios guardados con exito.");
		                dispose();
			}
				} catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(null, "El precio debe ser un número valido.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Verifique los datos ingresados.");
				}
			}
		});
		btnEdit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEdit.setBounds(10, 270, 89, 23);
		contentPane.add(btnEdit);
		
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
	
	private boolean emptyFields() {
		return textTitle.getText().trim().isEmpty() ||
				textGenre.getText().trim().isEmpty() ||
				textEditorial.getText().trim().isEmpty() ||
				textPrice.getText().trim().isEmpty() ||
				textISBN.getText().trim().isEmpty() ||
				textEditions.getText().trim().isEmpty() ||
				textCover.getText().trim().isEmpty();				
	}

}