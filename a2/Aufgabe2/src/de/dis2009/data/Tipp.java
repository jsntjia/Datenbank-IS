package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tipp {

	private int _begId;
	private int _id;
	private int _ergebnisA;
	private int _ergebnisB;
	
	public Tipp(){
		this._begId = -1;
		this._id = -1;
		this._ergebnisA = -1;
		this._ergebnisB = -1;
	}
	
	
	
	public int get_begId() {
		return _begId;
	}
	public void set_begId(int id) {
		_begId = id;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int get_ergebnisA() {
		return _ergebnisA;
	}
	public void set_ergebnisA(int _ergebnisa) {
		_ergebnisA = _ergebnisa;
	}
	public int get_ergebnisB() {
		return _ergebnisB;
	}
	public void set_ergebnisB(int _ergebnisb) {
		_ergebnisB = _ergebnisb;
	}
	
	
	public void save() {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit später generierte IDs zurückgeliefert werden!
				String insertSQL = "INSERT INTO tipp(id, begid, ergebnisA, ergebnisB) VALUES (?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und führe Anfrage aus
				pstmt.setInt(1, get_id());
				pstmt.setInt(2, get_begId());
				pstmt.setInt(3, get_ergebnisA());
				pstmt.setInt(4, get_ergebnisB());
				pstmt.executeUpdate();
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
}
