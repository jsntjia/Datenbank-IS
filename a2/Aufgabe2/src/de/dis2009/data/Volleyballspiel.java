package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Volleyballspiel extends Begegnung{
	
	private int _begId;
	private int _saetzeA;
	private int _saetzeB;
	
	public Volleyballspiel(){
		this._begId = -1;
		this._saetzeA = -1;
		this._saetzeB = -1;
	}
	
	public Volleyballspiel(int begId, int saetzea, int saetzeb){
		this._begId = begId;
		this._saetzeA = saetzea;
		this._saetzeB = saetzeb;		
	}

	public int get_begId() {
		return _begId;
	}

	public void set_begId(int id) {
		_begId = id;
	}

	public int get_saetzeA() {
		return _saetzeA;
	}

	public void set_saetzeA(int _saetzea) {
		_saetzeA = _saetzea;
	}

	public int get_saetzeB() {
		return _saetzeB;
	}

	public void set_saetzeB(int _saetzeb) {
		_saetzeB = _saetzeb;
	}
	
	public void save() {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			
				String insertSQL = "INSERT INTO volleyballspiel(begId, saetzeA, saetzeB) VALUES (?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				pstmt.setInt(1, get_begId());
				pstmt.setInt(2, get_saetzeA());
				pstmt.setInt(3, get_saetzeB());
				pstmt.executeUpdate();
				pstmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
