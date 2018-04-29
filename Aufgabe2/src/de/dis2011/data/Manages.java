package de.dis2011.data;

import java.sql.*;

/**
 * Created by kbrusch on 4/21/17.
 */
public class Manages {
    private int makler_id;

    public int getMakler_id() {
        return makler_id;
    }

    public void setMakler_id(int makler_id) {
        this.makler_id = makler_id;
    }

    public int getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(int estate_id) {
        this.estate_id = estate_id;
    }

    private int estate_id;

    /**
     * Lädt einen Makler aus der Datenbank
     * @param id ID des zu ladenden Maklers
     * @return Makler-Instanz

    public static Makler load(int id) {
        try {
            // Hole Verbindung
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM manages WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Manages ts = new Manages();
                pstmt.setInt(1, getMakler_id());
                pstmt.setInt(2, getEstate_id());
                rs.close();
                pstmt.close();
                return ts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
     */
    /**
     * Speichert den Makler in der Datenbank. Ist noch keine ID vergeben
     * worden, wird die generierte Id von DB2 geholt und dem Model übergeben.
     */
    public void save() {
        // Hole Verbindung
        Connection con = DB2ConnectionManager.getInstance().getConnection();
        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.

                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO manages(estate_id, makler_id) VALUES (?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setInt(1, getMakler_id());
                pstmt.setInt(2, getEstate_id());
                pstmt.executeUpdate();


                pstmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
