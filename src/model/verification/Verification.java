package model.verification;

import java.util.Scanner;

import db.DbException;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Verification {

	private static UserDao userDao = DaoFactory.createUserDao();
	
	public static User path(String email, String senha, Scanner sc) {
		User path = userVerification(email, senha);
		if(path != null) {
			return path;
		}
		return newUser(sc);
	}
	
	private static User newUser(Scanner sc) {
		System.out.println("Ops! user dont exists. Create a new user:");
		System.out.print("User name: ");
		String name = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Password: ");
		String senha = sc.nextLine();
		User newUser = new User(null, name, email, senha);
		User path = userVerification(email, senha);
		if(path != null) {
			throw new DbException("Error: user exists");
		}
		userDao.insert(newUser);
		System.out.println("Inserted! your id: " + newUser.getId());
		return newUser;
	}
	
	private static User userVerification(String email, String senha) {
		User user = userDao.findByEmailSenha(email, senha);
		if(user == null) {
			return user;
		}
		return user;
	}
	
	
}
