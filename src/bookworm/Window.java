package bookworm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel loginPanel;
	private JPanel mainPanel;
	private JTextField textUser;
	private JTextField textPass;
	private CardLayout cardLayout;
	private Worker loggedWorker;
	private JLabel lblWelcome;
	private DefaultTableModel tModel;
	private DefaultComboBoxModel cbModel;
	ArrayList<Books> bookList = new ArrayList<>();
	private JTextField textFilter;
	JLabel lblIcon = new JLabel();
	JLabel lblIcon2 = new JLabel();
	private JTable tableResults;
	Conexion con = new Conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
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
	public Window() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/assets/brand.png")));
		
		con.conectar();
		
		cbModel = new DefaultComboBoxModel();
		
		tModel = new DefaultTableModel();
		
		cbModel.addElement("Title");
		cbModel.addElement("Genre");
		cbModel.addElement("Author");
		
		
		lblWelcome = new JLabel();
		lblWelcome.setText("Welcome, employee");
		
		
		tableResults = new JTable();
		tableResults.setOpaque(false);
		tableResults.setFont(new Font("Arial", Font.PLAIN, 12));
		tableResults.setBounds(585, 167, 393, 287);
		
		
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Arial", Font.BOLD, 14));
		lblName.setBounds(37, 162, 296, 23);
		
		JLabel lblCI = new JLabel("CI:");
		lblCI.setFont(new Font("Arial", Font.BOLD, 14));
		lblCI.setBounds(37, 196, 296, 23);
		
		setResizable(false);
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Bookworm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 572);
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(cardLayout);
		
		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(222, 184, 135));
		loginPanel.setBounds(0, 0, 998, 533);
		contentPane.add(loginPanel, "loginPanel");
		loginPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("BOOKWORM LOGIN");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setBounds(368, 0, 274, 29);
		lblTitle.setFont(new Font("Arial Black", Font.BOLD, 24));
		loginPanel.add(lblTitle);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setBounds(492, 163, 36, 14);
		lblUser.setFont(new Font("Arial", Font.PLAIN, 12));
		loginPanel.add(lblUser);
		
		textUser = new JTextField();
		textUser.setBounds(428, 188, 165, 20);
		textUser.setFont(new Font("Arial", Font.PLAIN, 12));
		textUser.setColumns(10);
		loginPanel.add(textUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(479, 229, 61, 14);
		lblPass.setFont(new Font("Arial", Font.PLAIN, 12));
		loginPanel.add(lblPass);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(467, 285, 89, 23);
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 12));
		loginPanel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUser.getText();
		        String password = textPass.getText();
		        Worker worker = con.Login(username, password);
		        if (worker != null) {
		        	loggedWorker = worker;
		        	lblName.setText("Name: " + loggedWorker.getName() + " " +loggedWorker.getLastName());
		        	lblCI.setText("CI: " + loggedWorker.getCI());
		            cardLayout.show(contentPane, "mainPanel");
		            lblWelcome.setText("Welcome, " + loggedWorker.getName());
		        } else {
		            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		        }
		        loadEmptyTable();
		        
		        hideColumn(tableResults, 0);
		        hideColumn(tableResults, 3);
	            hideColumn(tableResults, 5);
	            hideColumn(tableResults, 6);
	            hideColumn(tableResults, 7);
			}
		});
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(467, 373, 89, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
		loginPanel.add(btnExit);
		
		JLabel lblOwner = new JLabel("©Sebastián Faraone");
		lblOwner.setBounds(852, 508, 136, 14);
		lblOwner.setFont(new Font("Arial", Font.PLAIN, 14));
		loginPanel.add(lblOwner);
		
		textPass = new JTextField();
		textPass.setBounds(428, 254, 165, 20);
		textPass.setFont(new Font("Arial", Font.PLAIN, 12));
		textPass.setColumns(10);
		loginPanel.add(textPass);
		
		
		lblIcon.setBounds(0, 0, 100, 100);
		loginPanel.add(lblIcon);
		loadImage();
		
		mainPanel = new JPanel();
		mainPanel.setBackground(new Color(222, 184, 135));
		mainPanel.setBounds(0, 0, 998, 533);
		contentPane.add(mainPanel, "mainPanel");
		mainPanel.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Logout");
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loggedWorker = null;

		        textUser.setText("");
		        textPass.setText("");

		        cardLayout.show(contentPane, "loginPanel");
			}
		});
		btnNewButton_2.setBounds(37, 248, 83, 23);
		mainPanel.add(btnNewButton_2);
		
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
		lblWelcome.setBounds(362, 11, 303, 31);
		mainPanel.add(lblWelcome);
		
		mainPanel.add(lblName);
		
		mainPanel.add(lblCI);
		
		JComboBox comboFilter = new JComboBox();
		comboFilter.setFont(new Font("Arial", Font.PLAIN, 12));
		comboFilter.setBounds(765, 100, 83, 22);
		mainPanel.add(comboFilter);
		comboFilter.setModel(cbModel);
		
		textFilter = new JTextField();
		textFilter.setFont(new Font("Arial", Font.PLAIN, 12));
		textFilter.setBounds(585, 101, 170, 20);
		mainPanel.add(textFilter);
		textFilter.setColumns(10);
		
		
		lblIcon2.setBounds(0, 0, 100, 100);
		mainPanel.add(lblIcon2);
		loadImage2();
		
		JLabel lblFilterBy = new JLabel("Filter by:");
		lblFilterBy.setFont(new Font("Arial", Font.PLAIN, 12));
		lblFilterBy.setBounds(765, 75, 53, 14);
		mainPanel.add(lblFilterBy);
		
		JLabel lblSelectedBook = new JLabel("");
		lblSelectedBook.setBounds(225, 100, 350, 354);
		mainPanel.add(lblSelectedBook);
		
		tableResults.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableResults.setRowSelectionAllowed(true);
		
		tableResults.getSelectionModel().addListSelectionListener(event -> {
		    if (!event.getValueIsAdjusting()) {
		        int selectedRow = tableResults.getSelectedRow();
		        if (selectedRow >= 0) {
		            String coverPath = (String) tableResults.getValueAt(selectedRow, 7);
		            if (coverPath != null && !coverPath.isEmpty()) {
		                updateImage(lblSelectedBook, coverPath);
		            } else {
		                lblSelectedBook.setIcon(null);
		                lblSelectedBook.setText("No image available");
		            }
		        }
		    }
		});
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 AddBookWindow w = new AddBookWindow();
				w.setVisible(true);
			}
		});
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAdd.setBounds(235, 465, 89, 23);
		mainPanel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableResults.getSelectedRow();
		        if (selectedRow >= 0) {
		        	Books selectedBook = new Books();
		        	
		        	int id = Integer.parseInt((String) tableResults.getValueAt(selectedRow, 0));
		        	String title = (String) tableResults.getValueAt(selectedRow, 1);
		        	String genre = (String) tableResults.getValueAt(selectedRow, 2);
		        	String editorial = (String) tableResults.getValueAt(selectedRow, 3);
		        	int price = Integer.parseInt((String) tableResults.getValueAt(selectedRow, 4));
		        	String isbn = (String) tableResults.getValueAt(selectedRow, 5);
		        	String editions = (String) tableResults.getValueAt(selectedRow, 6);
		        	String cover = (String) tableResults.getValueAt(selectedRow, 7);
		            
		            selectedBook = new Books(id, title, genre, editorial, price, isbn, editions, cover);

		            

		            EditWindow editWindow = new EditWindow(selectedBook);
		            editWindow.setVisible(true);
		        }
			}
		});
		btnEdit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEdit.setBounds(362, 465, 89, 23);
		mainPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableResults.getSelectedRow();			
				
		        if (selectedRow >= 0) {
		            int selectedBook = Integer.parseInt(String.valueOf(tableResults.getValueAt(selectedRow, 0)));
		            int confirm  = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas eliminar este libro?", "Confirmar", JOptionPane.YES_NO_OPTION);
		            
		            if(confirm == JOptionPane.YES_OPTION) {
		            int result = con.deleteBook(selectedBook);
		            
		            if (result > 0) {
		                ((DefaultTableModel) tableResults.getModel()).removeRow(selectedRow);
		                JOptionPane.showMessageDialog(null, "Libro eliminado con exito");
			}
		        }
			}
			}
		});
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDelete.setBounds(486, 465, 89, 23);
		mainPanel.add(btnDelete);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tModel.setRowCount(0);
				
				
				String filter = textFilter.getText();
				String selection = comboFilter.getSelectedItem().toString();
				
				ArrayList<Books> filteredBooks = new ArrayList<>();
				
				if(selection.equals("Author")) {
					filteredBooks = con.filterByAuthor(filter);
				} else if(selection.equals("Title")) {
					filteredBooks = con.filterByTitle(filter);
				} else if(selection.equals("Genre")) {
					filteredBooks = con.filterByGenre(filter);
				}
				
				loadTable(filteredBooks);
				
				hideColumn(tableResults, 0);
				hideColumn(tableResults, 3);
	            hideColumn(tableResults, 5);
	            hideColumn(tableResults, 6);
	            hideColumn(tableResults, 7);
				
				}
			});
		btnSearch.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSearch.setBounds(595, 132, 89, 23);
		mainPanel.add(btnSearch);
		
		mainPanel.add(tableResults);
		
		comboFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tModel.setRowCount(0);
				
		        String filterValue = textFilter.getText();
		        ArrayList<Books> filteredBooks;

		        if(comboFilter.getSelectedItem().toString().equals("Author")) {
		            filteredBooks = con.filterByAuthor(filterValue); 
		        } else if (comboFilter.getSelectedItem().toString().equals("Genre")) {
		            filteredBooks = con.filterByGenre(filterValue); 
		        } else {
		        	filteredBooks = con.filterByTitle(filterValue);
		        }

		        for(Books book : filteredBooks) {
		            String[] row = { 
		                book.getTitle(), 
		                book.getGenre(), 
		                book.getEditorial(), 
		                String.valueOf(book.getPrice()) 
		            };
		            tModel.addRow(row);
		        }
		    }
		});
	}
	void loadImage(){
        ImageIcon imagen = new ImageIcon("src/assets/brand.png"); 
        imagen.setImage(imagen.getImage().getScaledInstance(lblIcon.getWidth(), lblIcon.getHeight(), Image.SCALE_SMOOTH));
        lblIcon.setIcon(imagen);
    }
	
	void loadImage2() {
		ImageIcon imagen2 = new ImageIcon("src/assets/brand.png"); 
        imagen2.setImage(imagen2.getImage().getScaledInstance(lblIcon2.getWidth(), lblIcon2.getHeight(), Image.SCALE_SMOOTH));
        lblIcon2.setIcon(imagen2);
	}
	
	void loadEmptyTable() {
		tableResults.setModel(new DefaultTableModel(
				new Object [][] {
					{},
	                {}
	                },
				new String [] {
	                }
	            ));
			
			DefaultTableModel tModel = new DefaultTableModel();
			
			tModel.addColumn("ID");
			tModel.addColumn("Titulo");
			tModel.addColumn("Genero");
			tModel.addColumn("Editorial");
			tModel.addColumn("Precio");
			tModel.addColumn("ISBN");
			tModel.addColumn("Ediciones");
			tModel.addColumn("Portada");
			
			tModel.addRow(new String[] {"ID", "Titulo", "Genero", "Editorial", "Precio", "ISBN", "Ediciones", "Portada"});
			
			tableResults.setModel(tModel);
	}
	
	void loadTable(ArrayList<Books> books) {
		   tableResults.setModel(new DefaultTableModel(
	                new Object [][] {
	                    {},
	                    {}
	                },
	                new String [] {

	                }
	            ));
		
		DefaultTableModel tModel = new DefaultTableModel();
		
		tModel.addColumn("ID");
		tModel.addColumn("Titulo");
		tModel.addColumn("Genero");
		tModel.addColumn("Editorial");
		tModel.addColumn("Precio");
		tModel.addColumn("ISBN");
		tModel.addColumn("Ediciones");
		tModel.addColumn("Portada");
		
		tModel.addRow(new String[] {"ID", "Titulo", "Genero", "Editorial", "Precio", "ISBN", "Ediciones", "Portada"});
		
		String[] f;
		
		for(Books book: books) {
			f = new String[8];
			
			f[0] = String.valueOf(book.getID());
			f[1] = book.getTitle();
			f[2] = book.getGenre();
			f[3] = book.getEditorial();
			f[4] = String.valueOf(book.getPrice());
			f[5] = String.valueOf(book.getISBN());
			f[6] = book.getEditions();
			f[7] = book.getCover();
			
			tModel.addRow(f);
		}
		
		tableResults.setModel(tModel);
	}
	
	public static boolean isNumeric (String input) {
		if(input == null) {
			return false;
		}
		try {
			int i = Integer.parseInt(input);
			
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
}
	
	private void updateImage(JLabel label, String imagePath) {
	    try {
	        ImageIcon icon = new ImageIcon(imagePath);
	        Image image = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
	        label.setIcon(new ImageIcon(image));
	        label.setText("");
	    } catch (Exception e) {
	        label.setIcon(null);
	    }
	}
	
	private void hideColumn(JTable tableResults, int columnIndex) {
		TableColumn column = tableResults.getColumnModel().getColumn(columnIndex);
		column.setMinWidth(0);
		column.setMaxWidth(0);
		column.setPreferredWidth(0);
		column.setResizable(false);
	}
	
}