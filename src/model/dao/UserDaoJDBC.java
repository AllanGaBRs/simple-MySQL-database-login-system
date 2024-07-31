package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.Banco;
import db.DbException;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

	private Connection conn;
	private PreparedStatement st;
	private ResultSet rs;

	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(User user) {
		try {
			conn.setAutoCommit(false);

			st = conn.prepareStatement("INSERT INTO USERS (nome, email, senha) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, user.getName());
			st.setString(2, user.getEmail());
			st.setString(3, user.getSenha());
			
			int affected = st.executeUpdate();
			
			if(affected > 0) {
				rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					user.setId(id);
				}
				Banco.closeResultSet(rs);
			}
			conn.commit();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Banco.closeStatement(st);
		}

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmailSenha(String email, String senha) {
		try {
			st = conn.prepareStatement("SELECT * FROM USERS WHERE email = ? AND senha = ?",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, email);
			st.setString(2, senha);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				return new User(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("senha"));
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Banco.closeStatement(st);
		}
	}

}
