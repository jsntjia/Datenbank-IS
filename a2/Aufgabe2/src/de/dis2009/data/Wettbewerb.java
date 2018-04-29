package de.dis2009.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Wettbewerb {
	private String _name;

	public Wettbewerb(){
		this._name = null;
	}
	
	public Wettbewerb(String name){
		this._name = name;		
	}
	
	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}
	
	public void save() {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		try {
			
				String insertSQL = "INSERT INTO wettbewerb(name) VALUES (?)";

				PreparedStatement pstmt = con.prepareStatement(insertSQL,
						Statement.RETURN_GENERATED_KEYS);

				pstmt.setString(1, get_name());
				pstmt.executeUpdate();
				pstmt.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	
}
