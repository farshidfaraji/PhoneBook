package arya.phonebook.test;

import java.sql.SQLException;

import arya.phonebook.dao.h2.model.LoginDao;
import arya.phonebook.model.Login;
import arya.phonebook.model.User;
import arya.phonebook.model.UsernamePassword;
import arya.phonebook.model.enums.EnumGender;

public class Main {

	public static void main(String[] args) {
		try {
			Login login = new Login(new User("farshid", "faraji", null, "fd.faraji@gmail.com", "6770002531", "karaj",
					null, "09364033275", EnumGender.MAN), new UsernamePassword("farshid", "1245"));
			LoginDao ld = new LoginDao();
			ld.insert(login);
			System.out.println(login.getId());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
