package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


/**
 * Servlet implementation class DAO
 */
@WebServlet("/DAO")
public class DAO extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public DAO() {
		super();
		System.out.println("DAO Constructor");
	}

	public ArrayList<Item> getItems() {
		DBConnect db = new DBConnect("root","");
		String query = "SELECT * FROM users ORDER BY id ASC";
		ArrayList<Item> listOfUsers = new ArrayList<Item>();
		try{
			db.connect();
			// execute the query, and get a java result set
			ResultSet rs = db.getStatement().executeQuery(query);

			// iterate through the java result set
			while (rs.next())
			{
				int id = rs.getInt("id");
				String date = rs.getString("date");
				String note = rs.getString("note");
				boolean done = rs.getBoolean("done");
				Item item = new Item(id,date,note,done);
				listOfUsers.add(item);
			}
			rs.close();
			db.closeConnection();
			return listOfUsers;
		} catch (SQLException se){
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public boolean addItem(String date, String note) {
		DBConnect db = new DBConnect("root","");
		String query = "INSERT INTO users (date,note,done) VALUES (?,?,0)";

		PreparedStatement preparedStmt = null;
		try {
			preparedStmt = db.connect().prepareStatement(query);
			preparedStmt.setString (1, date);
			preparedStmt.setString (2, note);

			preparedStmt.executeUpdate();
			db.closeConnection();
			return true;
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	public boolean removeItem(String id) {
		DBConnect db = new DBConnect("root","");
		String query = "DELETE FROM users WHERE id = " + id;
		try{
			db.connect();
			int stmt = db.getStatement().executeUpdate(query);
			db.closeConnection();
			if (stmt == 1)
				return true;
			else
				return false;
		} catch (SQLException se){
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean doneItem(String id, String checked) {
		DBConnect db = new DBConnect("root","");
		String query = "UPDATE users SET done = "+ checked +" WHERE id = "+ id;
		try{
			db.connect();
			int stmt = db.getStatement().executeUpdate(query);
			db.closeConnection();
			if (stmt == 1)
				return true;
			else
				return false;
		} catch (SQLException se){
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateItem(String id, String updatedNote) {
		DBConnect db = new DBConnect("root","");
		String query = "UPDATE users SET note = '"+ updatedNote +"' WHERE id = "+ id;
		try{
			db.connect();
			int stmt = db.getStatement().executeUpdate(query);
			db.closeConnection();
			if (stmt == 1)
				return true;
			else
				return false;
		} catch (SQLException se){
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}


}
