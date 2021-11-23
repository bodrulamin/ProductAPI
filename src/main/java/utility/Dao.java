/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import com.model.Product;
import com.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b
 */
public class Dao {

	private static Connection conn = null;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		String url = "jdbc:mysql://localhost:3306/myschema";
		String user = "root";
		String password = "123456";

		if (conn == null) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		}
		return conn;
	}

	public static User getUser(String username) throws SQLException, Exception {
		User user = null;
		String sql = "select * from users where username = ?";

		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setString(1, username);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			user = new User(
							rs.getInt("id"), rs.getString("username"), rs.getString("password")
			);
		}
		if (user == null) {
			throw new Exception("User Not Found");
		}

		return user;

	}

	public static String registerUser(User user) throws SQLException, ClassNotFoundException {
		String sql = "insert into users(username, password) values(?,?)";

		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());
		int result = pst.executeUpdate();

		if (result > 0) {
			return "Registration Successfull";
		}

		return "Registration Failed";
	}

	public static List<Product> getProducts() throws SQLException, Exception {
		List<Product> plist = new ArrayList<>();

		String sql = "select * from product";

		PreparedStatement pst = getConnection().prepareStatement(sql);

		ResultSet rs = pst.executeQuery();
		while (rs.next()) {

			plist.add(new Product(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getDouble("price"),
							rs.getInt("quantity"),
							rs.getString("remarks")
			));

		}

		return plist;

	}

	public static boolean addProduct(Product product) throws SQLException, ClassNotFoundException {
		String sql = "insert into product(name,price,quantity,remarks) values(?,?,?,?)";

		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setString(1, product.getName());
		pst.setDouble(2, product.getPrice());
		pst.setInt(3, product.getQuantity());
		pst.setString(4, product.getRemarks());

		int result = pst.executeUpdate();

		return result > 0;
	}

	public static boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
		String sql = "update product set name = ?, price = ?, quantity = ?, remarks = ? where id = ?";

		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setString(1, product.getName());
		pst.setDouble(2, product.getPrice());
		pst.setInt(3, product.getQuantity());
		pst.setString(4, product.getRemarks());
		pst.setInt(5, product.getId());


		int result = pst.executeUpdate();

		return result > 0;
	}

	public static boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
		String sql = "delete from product where id = ?";

		PreparedStatement pst = getConnection().prepareStatement(sql);
	
		pst.setInt(1,Integer.parseInt(id) );


		int result = pst.executeUpdate();

		return result > 0;
	}


	
}
