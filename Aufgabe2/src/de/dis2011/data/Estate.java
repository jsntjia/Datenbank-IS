package de.dis2011.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.dis2011.data.DB2ConnectionManager;

/**
 CREATE TABLE estate(id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1, NO CACHE) PRIMARY KEY,
 city varchar(255),
 postalcode varchar(40),
 street varchar(40),
 street_number varchar(40),
 square_area varchar(40),
 makler_id int,
 constraint estate_constraint
 foreign key (makler_id) references makler(id) on delete cascade
 );
 */
public class Estate {
    private int id = -1;
    private String city;
    private String postalcode;
    private String street;
    private String street_number;
    private String square_area;


    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getSquare_area() {
        return square_area;
    }

    public void setSquare_area(String square_area) {
        this.square_area = square_area;
    }



    /**
     * Lädt einen Makler aus der Datenbank
     * @param id ID des zu ladenden Maklers
     * @return Makler-Instanz
     */
    public static Estate load(int id) {
        try {
            // Hole Verbindung
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM estate WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Estate ts = new Estate();
                ts.setId(id);
                ts.setCity(rs.getString("city"));
                ts.setPostalcode(rs.getString("postalcode"));
                ts.setStreet(rs.getString("street"));
                ts.setStreet_number(rs.getString("street_number"));
                ts.setSquare_area(rs.getString("square_area"));

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
     * Speichert den Makler in der Datenbank. Ist noch keine ID vergeben
     * worden, wird die generierte Id von DB2 geholt und dem Model übergeben.
     */
    public void save() {
        // Hole Verbindung
        Connection con = DB2ConnectionManager.getInstance().getConnection();
        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
            if (getId() == -1) {
                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO estate(city, postalcode, street, street_number,square_area) VALUES (?, ?, ?, ?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setString(1, getCity());
                pstmt.setString(2, getPostalcode());
                pstmt.setString(3, getStreet());
                pstmt.setString(4, getStreet_number());
                pstmt.setString(5, getSquare_area());

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
                String updateSQL = "UPDATE estate SET name = ?, address = ?, login = ?, password = ? WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrage Parameter
                pstmt.setString(1, getCity());
                pstmt.setString(2, getPostalcode());
                pstmt.setString(3, getStreet());
                pstmt.setString(4, getStreet_number());
                pstmt.setString(5, getSquare_area());

                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
