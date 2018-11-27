package models;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DbConn {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    private void connDb() {
        try {
            DriverManager.registerDriver(new Driver());
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taxi_park", "root", "root");

        } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Vector<ReservedPlace> listReservedPlace() {

        connDb();
        try {
            Vector<ReservedPlace> list = new Vector();
            String query = "select * from reservedplace order by place";
            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ReservedPlace(rs.getLong(1), rs.getString(2)));

            }
            return list;

        } catch (Exception e) {
        } finally {
            close();
        }
        return null;
    }
    
   

    public void addCar(Autopark ap) {
        try {
            connDb();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into autopark values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, ap.getSerial_num());
            ps.setString(2, ap.getName());
            ps.setString(3, ap.getSurname());
            ps.setString(4, ap.getPhone());
            ps.setString(5, ap.getCar_num());
            ps.setString(6, ap.getMark());
            ps.setString(7, ap.getModel());
            ps.setString(8, ap.getColor());
            ps.setString(9, ap.getPlace());
            
            int rowcount = ps.executeUpdate();
            conn.commit();

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

    }
    public void deleteAutopark (Autopark ap){
        try {
            connDb();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("delete from autopark where name=?");
            ps.setString(1, ap.getName());
            int rows = ps.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void addFullPlace(FullPlace fp) {
        try {
            connDb();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into fullplace values(?,?)");
            ps.setLong(1, fp.getId());
            ps.setString(2, fp.getReservedPlace());
            int rows = ps.executeUpdate();
            conn.commit();
            
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex1) {
                    
                }
                finally{
                    close();
                }
            }
        }

    }
    public void deleteFullPlace(FullPlace fp){
        try {
            connDb();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("delete from fullplace where id=?");
            ps.setLong(1, fp.getId());
            int rows = ps.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            if(conn!=null)
                try {
                    conn.close();
            } catch (SQLException ex1) {
                Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    public void addReservedPlace (ReservedPlace rp){
        try {
            connDb();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("insert into reservedplace values(?,?)");
            ps.setLong(1, rp.getId());
            ps.setString(2, rp.getPlace());
            int rows = ps.executeUpdate();
            conn.commit();
                    } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteReservedPlace(ReservedPlace rp) {
        try {
            connDb();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("delete from reservedplace where id=?");
            ps.setLong(1, rp.getId());
            int rows = ps.executeUpdate();
            conn.commit();
            

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }

    }
    public Vector<FullPlace> listFullPlace(){
        try {
            connDb();
            Vector<FullPlace> list = new Vector<>();
            String query = "select * from fullplace order by reservedplace";
            ps  = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new FullPlace(rs.getLong(1), rs.getString(2)));
                
                
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
   }
    
    public List<Autopark> listAutopark (){
        try {
            connDb();
            List<Autopark> list = new ArrayList<>();
            ps = conn.prepareStatement("select*from autopark");
            rs = ps.executeQuery();
            while (rs.next()) {
              list.add(new Autopark(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
                
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            close();
        }
        return null;
    }

    public FullPlace findPlaceIdByText(String placeText) {

        try {
            connDb();
            ps = conn.prepareStatement("select*from fullplace where reservedplace = ? ");
            ps.setString(1, placeText);
            rs = ps.executeQuery();
            if (rs.next()) {
             FullPlace f = new FullPlace(rs.getLong(1),rs.getString(2));
             return f;
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            close();
        }
       return null;
    }

}

