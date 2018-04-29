package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Begegnung {

	private int _begId;
	private String _datum;
	private String _uhrzeit;
	private String _teamA;
	private String _teamB;
	
	
	public Begegnung() {
		this._begId = -1;
		this._datum = null;
		this._teamA = null;
		this._teamB = null;
		this._uhrzeit = null;
	}
	
	public Begegnung(int begId, String datum, String uhrzeit, String teamA, String teamB){
		this._begId = begId;
		this._datum = datum;
		this._teamA = teamA;
		this._teamB = teamB;
		this._uhrzeit = uhrzeit;
	}
	
	public int get_begId() {
		return _begId;
	}
	public void set_begId(int id) {
		_begId = id;
	}
	public String get_datum() {
		return _datum;
	}
	public void set_datum(String _datum) {
		this._datum = _datum;
	}
	public String get_uhrzeit() {
		return _uhrzeit;
	}
	public void set_uhrzeit(String _uhrzeit) {
		this._uhrzeit = _uhrzeit;
	}
	public String get_teamA() {
		return _teamA;
	}
	public void set_teamA(String _teama) {
		_teamA = _teama;
	}
	public String get_teamB() {
		return _teamB;
	}
	public void set_teamB(String _teamb) {
		_teamB = _teamb;
	}
	
	public void save(String wb_name) {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			// Füge neues Element hinzu, wenn das Objekt noch keine ID hat.
			if (get_begId() == -1) {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit später generierte IDs zurückgeliefert werden!
				String insertSQL = "INSERT INTO begegnung(datum, uhrzeit, TeamA, TeamB, WB_NAME) VALUES (?, ?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und führe Anfrage aus
				pstmt.setString(1, get_datum());
				pstmt.setString(2, get_uhrzeit());
				pstmt.setString(3, get_teamA());
				pstmt.setString(4, get_teamB());
				pstmt.setString(5, wb_name);
				pstmt.executeUpdate();

				// Hole die Id des engefügten Datensatzes
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					set_begId(rs.getInt(1));
				}

				rs.close();
				pstmt.close();
			} else {
				// Falls schon eine ID vorhanden ist, mache ein Update...
				String updateSQL = "UPDATE begegnung SET datum = ?, uhrzeit = ?, teamA = ?, teamB = ? WHERE id = ?";
				PreparedStatement pstmt = con.prepareStatement(updateSQL);

				// Setze Anfrage Parameter
				pstmt.setString(1, get_datum());
				pstmt.setString(2, get_uhrzeit());
				pstmt.setString(3, get_teamA());
				pstmt.setString(4, get_teamB());
				pstmt.setInt(5, get_begId());
				pstmt.executeUpdate();

				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
