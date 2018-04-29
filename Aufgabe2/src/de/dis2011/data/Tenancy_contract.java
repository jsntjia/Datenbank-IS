package de.dis2011.data;

import java.sql.*;

/**
 * Created by kbrusch on 4/24/17.
 */
public class Tenancy_contract {

    private int id = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAdditional_costs() {
        return additional_costs;
    }

    public void setAdditional_costs(String additional_costs) {
        this.additional_costs = additional_costs;
    }

    private String start_date;
    private String duration;
    private String additional_costs;


    public void save() {
        // Hole Verbindung
        Connection con = DB2ConnectionManager.getInstance().getConnection();
        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
            if (getId() == -1) {
                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO tenancy_contract(additional_cost, duration, start_date) VALUES (?, ?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setString(1, getAdditional_costs());
                pstmt.setString(2, getDuration());
                pstmt.setString(3, getStart_date());


                pstmt.executeUpdate();

                // Hole die Id des engefC<gten Datensatzes
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {

                    System.out.println(rs.getInt(1));
                    setId(rs.getInt(1));
                }

                rs.close();
                pstmt.close();
            } else {
                // Falls schon eine ID vorhanden ist, mache ein Update...
                String updateSQL = "UPDATE tenancy_contract SET date = ?, place_id = ? WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrage Parameter

                pstmt.setString(1, getAdditional_costs());
                pstmt.setString(2, getDuration());
                pstmt.setString(3, getStart_date());

                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
