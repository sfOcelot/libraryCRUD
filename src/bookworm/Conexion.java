package bookworm;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Conexion {

    Connection con;
    Statement statement;
    ResultSet result;
    PreparedStatement prepStatement;

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria", "root", "");
            statement = con.createStatement();
            System.out.println("Conectado Correctamente");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

      }
    
    
    
        public Worker Login(String usuario, String pass) {
        	Worker worker = null;
        	
    	try {
    		statement = con.createStatement();
			prepStatement = con.prepareStatement("SELECT * FROM trabajador WHERE Usuario= ? AND Password= ?");
			prepStatement.setString(1, usuario);
			prepStatement.setString(2, pass);
			result = prepStatement.executeQuery();
			if(result.next()) {
				worker = new Worker();
				worker.setName(result.getString("Nombre"));
				worker.setLastName(result.getString("Apellido"));
				worker.setCI(result.getString("CI"));
				worker.setPhone(result.getString("Telefono"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return worker;
    }
        
        public ArrayList<Books> filterByAuthor(String value) {
        	ArrayList<Books> bookList = new ArrayList<>();
        	try {
        		statement = con.createStatement();
        		prepStatement = con.prepareStatement("SELECT libros.ID, libros.Titulo, libros.Genero, libros.Editorial, libros.Precio, libros.ISBN, libros.Ediciones, libros.Portada "
        			    + "FROM libros "
        			    + "JOIN escritos ON libros.ID = escritos.IDLibro "
        			    + "JOIN autores ON autores.ID = escritos.IDAutor "
        			    + "WHERE autores.Nombre LIKE ? OR autores.Apellido LIKE ?");
        		prepStatement.setString(1, "%" + value + "%");
        		prepStatement.setString(2, "%" + value + "%");
        		result = prepStatement.executeQuery();
        		while(result.next()) {
        			int ID = result.getInt("ID");
        			String title = result.getString("Titulo");
        			String genre = result.getString("Genero");
        			String editorial = result.getString("Editorial");
        			int price = result.getInt("Precio");
        			String ISBN = result.getString("ISBN");
        			String editions = result.getString("Ediciones");
        			String cover = result.getString("Portada");
        			
        			bookList.add(new Books(ID, title, genre, editorial, price, ISBN, editions, cover));
        		}
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        	return bookList;
        } 
        
        public ArrayList<Books> filterByGenre(String value) {
        	ArrayList<Books> bookList = new ArrayList<>();
        	try {
        		statement = con.createStatement();
        		prepStatement = con.prepareStatement("SELECT * FROM libros WHERE Genero LIKE ?");
        		prepStatement.setString(1, "%" + value + "%");
        		result = prepStatement.executeQuery();
        		while(result.next()) {
        			int ID = result.getInt("ID");
        			String title = result.getString("Titulo");
        			String genre = result.getString("Genero");
        			String editorial = result.getString("Editorial");
        			int price = result.getInt("Precio");
        			String ISBN = result.getString("ISBN");
        			String editions = result.getString("Ediciones");
        			String cover = result.getString("Portada");
        			
        			bookList.add(new Books(ID, title, genre, editorial, price, ISBN, editions, cover));
        		}
        	} catch (SQLException e) {
        		e.printStackTrace();
        	} return bookList;
    }
        
        public ArrayList<Books> filterByTitle(String value) {
        	ArrayList<Books> bookList = new ArrayList<>();
        	try {
        		statement = con.createStatement();
        		prepStatement = con.prepareStatement("SELECT * FROM libros WHERE Titulo LIKE ?");
        		prepStatement.setString(1, "%" + value + "%");
        		result = prepStatement.executeQuery();
        		while(result.next()) {
        			int ID = result.getInt("ID");
        			String title = result.getString("Titulo");
        			String genre = result.getString("Genero");
        			String editorial = result.getString("Editorial");
        			int price = result.getInt("Precio");
        			String ISBN = result.getString("ISBN");
        			String editions = result.getString("Ediciones");
        			String cover = result.getString("Portada");
        			
        			bookList.add(new Books(ID, title, genre, editorial, price, ISBN, editions, cover));
        		}
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        	return bookList;
        }
        
        public int addBook(Books book){        	
        	int i = 0;
        	try {
        		prepStatement = con.prepareStatement("INSERT INTO libros (Titulo, ISBN, Genero, Editorial, Ediciones, Precio, Portada) VALUES (?, ?, ?, ?, ?, ?, ?)");
                prepStatement.setString(1,book.getTitle());
                prepStatement.setString(2, book.getISBN());
                prepStatement.setString(3, book.getGenre());
                prepStatement.setString(4, book.getEditorial());
                prepStatement.setString(5, book.getEditions());
                prepStatement.setInt(6, book.getPrice());
                prepStatement.setString(7, book.getCover());
                i = prepStatement.executeUpdate();
                } catch (SQLException ex) {
                	i = 0;
                System.out.println(ex);
            } return i;
        } 
        
        public int deleteBook(int ID){
        	int i = 0;
            try {
            	prepStatement = con.prepareStatement("DELETE FROM libros WHERE ID = ?");
                prepStatement.setInt(1, ID);
                i = prepStatement.executeUpdate();
            } catch (SQLException ex) {
            	i = 0;
                System.out.println(ex);
            } return i;
        }
        
        public int updateBook(int ID, String title, String genre, String editorial, int price, String isbn, String editions, String cover) {
            int i = 0;
            try {
            	prepStatement = con.prepareStatement("UPDATE libros SET Titulo = ?, Genero = ?, Editorial = ?, Precio = ?, ISBN = ?, Ediciones = ?, Portada = ? WHERE ID = ?");
                prepStatement.setString(1, title);
                prepStatement.setString(2, genre);
                prepStatement.setString(3, editorial);
                prepStatement.setInt(4, price);
                prepStatement.setString(5, isbn);
                prepStatement.setString(6, editions);
                prepStatement.setString(7, cover);
                prepStatement.setInt(8, ID);

                i = prepStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return i;
        }
        
}