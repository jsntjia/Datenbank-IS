package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Tippspiel
 * 
 * Beispiel-Tabelle: CREATE TABLE Aufg2_Tippspiel ( id INTEGER NOT NULL
 * GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE) PRIMARY
 * KEY, name VARCHAR(255) NOT NULL, wettbewerb INTEGER DEFAULT 0, verwalter
 * INTEGER DEFAULT 0 );
 * 
 * @author Michael von Riegen
 * @version April 2009
 */
public class Tippspiel {

	// Attribute
	private int _id = -1;
	private String _name = "";
	private String _wettbewerb;
	private int _verwalter = 0;

	/**
	 * Erzeugt Tippspiel
	 */
	public Tippspiel() {
		// dummy
	}

	/**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @return the _wettbewerb
	 */
	public String get_wettbewerb() {
		return _wettbewerb;
	}

	/**
	 * @return the _verwalter
	 */
	public int get_verwalter() {
		return _verwalter;
	}

	/**
	 * @param _id
	 *            the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * @param _name
	 *            the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}

	/**
	 * @param _wettbewerb
	 *            the _wettbewerb to set
	 */
	public void set_wettbewerb(String _wettbewerb) {
		this._wettbewerb = _wettbewerb;
	}

	/**
	 * @param _verwalter
	 *            the _verwalter to set
	 */
	public void set_verwalter(int _verwalter) {
		this._verwalter = _verwalter;
	}

	/**
	 * Lädt ein Tippspiel via Id.
	 * 
	 * @param id
	 *            die Id des Datensatzes
	 * @return Tippspiel
	 */
	public Tippspiel load(int id) {
		try {
			// Hole Verbindung
			Connection con = DB2ConnectionManager.getInstance().getConnection();

			// Erzeuge Anfrage
			String selectSQL = "SELECT name, wettbewerb, verwalter FROM tippspiel WHERE TS_id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, id);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Tippspiel ts = new Tippspiel();
				ts.set_id(id);
				ts.set_name(rs.getString("name"));
				ts.set_wettbewerb(rs.getString("wettbewerb"));
				ts.set_verwalter(rs.getInt("verwalter"));

				rs.close();
				pstmt.close();
				return ts;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			if (get_id() == -1) {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit später generierte IDs zurückgeliefert werden!
				String insertSQL = "INSERT INTO tippspiel(name, wettbewerb, verwalter) VALUES (?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und führe Anfrage aus
				pstmt.setString(1, get_name());
				pstmt.setString(2, get_wettbewerb());
				pstmt.setInt(3, get_verwalter());
				pstmt.executeUpdate();

				// Hole die Id des engefügten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					set_id(rs.getInt(1));
				}

				rs.close();
				pstmt.close();
			} else {
				// Falls schon eine ID vorhanden ist, mache ein Update...
				String updateSQL = "UPDATE tippspiel SET name = ?, wb_name = ?, verwalter = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setString(1, get_name());
				pstmt.setString(2, get_wettbewerb());
				pstmt.setInt(3, get_verwalter());
				pstmt.setInt(4, get_id());
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

			String insertSQL = "delete from tippspiel where id = ?";

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
	
}