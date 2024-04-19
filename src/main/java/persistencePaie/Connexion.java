package persistencePaie;

import classesPaie.ParametreConnectionC;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion implements Serializable {
	private static final long serialVersionUID = 1490070459121276604L;
	private static Connexion connexion;
	private static Connection con;
	ParametreConnectionC parameter;

	private Connexion() {
		try {

			this.parameter = new ParametreConnectionC();

			if (this.parameter.readChaine()) {

				Class.forName("com.mysql.jdbc.Driver");

				/*
				 * String url = "jdbc:mysql://localhost:3306/asystpaie?characterEncoding=utf8";
				 * con =DriverManager.getConnection(url, "root","diego123");
				 */

				String url = "jdbc:mysql://" + this.parameter.getNomHost() + ":3306/"
						+ this.parameter.getNomBaseDeDonnee() + "?characterEncoding=utf8";
				con = DriverManager.getConnection(url, this.parameter.getNomUtilisateur(),
						this.parameter.getMotDePasse());

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException sQLException) {
			System.out.println(sQLException.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (connexion == null) {
			connexion = new Connexion();
		}
		return con;
	}

	public static void initializeConnection() {
		connexion = null;
	}
}
