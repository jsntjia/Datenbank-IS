package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Spielt {
	private int _mitspielerid;
	private int _tippspielid;
	
	public Spielt(){
		_mitspielerid = 0;
		_tippspielid = 0;
	}
	public Spielt(int mitspielerid, int tippspielid){
		_mitspielerid = mitspielerid;
		_tippspielid = tippspielid;
	}
	public int get_mitspielerid() {
		return _mitspielerid;
	}
	public void set_mitspielerid(int _mitspielerid) {
		this._mitspielerid = _mitspielerid;
	}
	public int get_tippspielid() {
		return _tippspielid;
	}
	public void set_tippspielid(int _tippspielid) {
		this._tippspielid = _tippspielid;
	}
	public static Spielt create_Spielt_Instanz(int mitspielerid, int tippspielid) {

		return new Spielt(mitspielerid, tippspielid);
	}
	public void save() {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
				// Achtung, hier wird noch ein Parameter mitgegeben,
				// damit später generierte IDs zurückgeliefert werden!
				String insertSQL = "INSERT INTO spielt(mitspieler, tippspiel) VALUES (?, ?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				// Setze Anfrageparameter und führe Anfrage aus
				pstmt.setInt(1, get_mitspielerid());
				pstmt.setInt(2, get_tippspielid());
				pstmt.executeUpdate();
				pstmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void delete(int mitspielerid, int tippspielid) {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {

			String insertSQL = "delete from spielt where mitspieler = ? and tippspiel = ?";

			PreparedStatement pstmt = con.prepareStatement(insertSQL,
					Statement.RETURN_GENERATED_KEYS);

			// Setze Anfrageparameter und führe Anfrage aus
			pstmt.setInt(1, mitspielerid);
			pstmt.setInt(2, tippspielid);
			pstmt.executeUpdate();
			pstmt.close();
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Spielt load(int id) {
		try {
			// Hole Verbindung
			Connection con = DB2ConnectionManager.getInstance().getConnection();

			// Erzeuge Anfrage
			String selectSQL = "SELECT SpId FROM spielt WHERE id = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, id);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Spielt sp = new Spielt();
				sp.set_mitspielerid(id);
				sp.set_tippspielid(rs.getInt("SpId"));


				rs.close();
				pstmt.close();
				return sp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
