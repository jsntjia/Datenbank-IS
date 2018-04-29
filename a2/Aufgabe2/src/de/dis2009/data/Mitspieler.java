package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mitspieler {
	private int id = -1;
	private String _vorname;
	private String _name;
	private String _passwort;

	public Mitspieler() {
		this._vorname = null;
		this._name = null;
		this._passwort = null;
	}

	public Mitspieler(String vorname, String name, String passwort) {
		this._vorname = vorname;
		this._name = name;
		this._passwort = passwort;
	}

	public String get_vorname() {
		return _vorname;
	}

	public void set_vorname(String _vorname) {
		this._vorname = _vorname;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_passwort() {
		return _passwort;
	}

	public void set_passwort(String _passwort) {
		this._passwort = _passwort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Mitspieler create_Mitspieler_Instanz(String vorname,
			String name, String passwort) {

		return new Mitspieler(vorname, name, passwort);
	}

	/**
	 * Speichert das Tippspiel in der Datenbank. Ist noch keine ID vergeben
	 * worden, wird die generierte Id von DB2 geholt und dem Model übergeben.
	 */
	public void save() {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			// Füge neues Element hinzu, wenn das Objekt noch keine ID hat.
			if (getId() == -1) {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit später generierte IDs zurückgeliefert werden!
				String insertSQL = "INSERT INTO Mitspieler(vorname, nachname, passwort) VALUES (?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und führe Anfrage aus
				pstmt.setString(1, get_vorname());
				pstmt.setString(2, get_name());
				pstmt.setString(3, get_passwort());
				pstmt.executeUpdate();

				// Hole die Id des engefügten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					setId(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
			} else {
				// Falls schon eine ID vorhanden ist, mache ein Update...
				String updateSQL = "UPDATE mitspieler SET vorname = ?, name = ?, passwort = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setString(1, get_vorname());
				pstmt.setString(2, get_name());
				pstmt.setString(3, get_passwort());
				pstmt.setInt(4, getId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {

			String insertSQL = "delete from mitspieler where id = ?";

			PreparedStatement pstmt = con.prepareStatement(insertSQL,
					Statement.RETURN_GENERATED_KEYS);

			// Setze Anfrageparameter und führe Anfrage aus
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void login(){
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {

			String insertSQL = "Select * from mitspieler where id = ? and passwort = ?";

			PreparedStatement pstmt = con.prepareStatement(insertSQL,
					Statement.RETURN_GENERATED_KEYS);

			// Setze Anfrageparameter und führe Anfrage aus
			pstmt.setInt(1, getId());
			pstmt.setString(2, get_passwort());
			
			pstmt.close();
			} 
		catch (SQLException e) {
			System.out.println("Login Fehlgeschlagen. Bitte erneut versuchen.");		
		}
		
	}
	
}