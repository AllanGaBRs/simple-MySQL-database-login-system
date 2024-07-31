package model.dao;

import db.Banco;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoJDBC(Banco.getConnection());
	}
	
}
