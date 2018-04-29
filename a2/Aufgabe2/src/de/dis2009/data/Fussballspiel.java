package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Fussballspiel extends Begegnung{

	private int _begId;
	private int _toreA;
	private int _toreB;
	private int _gelb;
	private int _rot;
	
	public Fussballspiel(){
		this._begId = -1;
		this._toreA = -1;
		this._toreB = -1;
		this._gelb = -1;
		this._rot = -1;
	}
	
	public Fussballspiel(int begId, int toreA, int toreB, int gelb, int rot){
		this._begId = begId;
		this._toreA = toreA;
		this._toreB = toreB;
		this._gelb = gelb;
		this._rot = rot;
	}
	
	public int get_begId() {
		return _begId;
	}
	public void set_begId(int id) {
		_begId = id;
	}
	public int get_toreA() {
		return _toreA;
	}
	public void set_toreA(int _torea) {
		_toreA = _torea;
	}
	public int get_toreB() {
		return _toreB;
	}
	public void set_toreB(int _toreb) {
		_toreB = _toreb;
	}
	public int get_gelb() {
		return _gelb;
	}
	public void set_gelb(int _gelb) {
		this._gelb = _gelb;
	}
	public int get_rot() {
		return _rot;
	}
	public void set_rot(int _rot) {
		this._rot = _rot;
	}
	
	public void save() {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			
				String insertSQL = "INSERT INTO fussballspiel(begId, ToreA, ToreB, gelb, rot) VALUES (?, ?, ?, ?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				pstmt.setInt(1, get_begId());
				pstmt.setInt(2, get_toreA());
				pstmt.setInt(3, get_toreB());
				pstmt.setInt(4, get_gelb());
				pstmt.setInt(5, get_rot());
				pstmt.executeUpdate();
				pstmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}
