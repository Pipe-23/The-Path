
package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    protected Connection con = null;
    private String host = "localhost";
    private String puerto = "3306";
    private String sid = "proyectoprograiv";  //revisar
    private String usuario = "root";
    private String clave = "rootpass";
    
        public void conectar() {
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + puerto + "/" + sid + /*"?autoReconnect=true"*/ "?serverTimezone=UTC",
                    usuario, clave);
            
            System.out.println("Conectó");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    public void cerrarStatement(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    
    public void cerrarResultSet(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void cerrarPreparedStatement(PreparedStatement pstm){
        try
        {
            if(pstm != null && !pstm.isClosed())
            {
                pstm.close();
                pstm = null;
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
}
