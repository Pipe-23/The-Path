package Service;

import Model.FacturaTO;
import Model.ProductoTO;
import Model.UsuarioTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author luisfelipecastro
 */
public class ServicioFactura extends Conexion{

    public List<FacturaTO> retornaFactura(){
        Statement stmt = null;
        ResultSet rs = null;
        List<FacturaTO> listafac = new ArrayList<FacturaTO>();
        try{
            conectar();
            stmt = con.createStatement();
            String sql = "Select * from facturas";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                int IdFactura = rs.getInt("IdFactura");
                String nombreCompleto = rs.getString("nombreCompleto");
                int cantidad = rs.getInt("cantidad");
                double iva = rs.getDouble("iva");
                double subTotal = rs.getDouble("subTotal");
                double total = rs.getDouble("total");
                FacturaTO facturaTO = new FacturaTO(IdFactura, nombreCompleto,cantidad, iva, subTotal,total);
                listafac.add(facturaTO);
            }
        } catch (Exception ex) {
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(stmt);
            desconectar();
        }
        return listafac;
    }
    
    
    public void insertarFactura(String nombreCompleto){
        try
        {
            conectar();
            String sql = "INSERT INTO facturas (nombreCompleto, cantidad, iva, subTotal, total) VALUES(?,0,0,0,0)";
            PreparedStatement preparedStmnt = con.prepareStatement(sql);
            preparedStmnt.setString(1, nombreCompleto);

            preparedStmnt.execute();
            cerrarPreparedStatement(preparedStmnt);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error debido a: " + ex.toString());
        } finally {
            desconectar();
        }
    }
    
    public void InsertaProductosXFactura(List<ProductoTO> listaCarrito)
    {
        int ultimaFactura = 0;
        double acumSubtotal = 0;
        double iva = 0;
        double total = 0;
        
        try
        {
            conectar();
            ultimaFactura = GetUltimaFactura();
            for (int i = 0; i < listaCarrito.size(); i++)
            {
                conectar();
                String sql = "INSERT INTO productos_x_factura (idFactura, idProducto) VALUES(?,?)";
                PreparedStatement preparedStmnt = con.prepareStatement(sql);
                preparedStmnt.setInt(1, ultimaFactura);
                preparedStmnt.setInt(2, listaCarrito.get(i).getIdProducto());
                preparedStmnt.execute();
                cerrarPreparedStatement(preparedStmnt);

                acumSubtotal = (acumSubtotal + listaCarrito.get(i).getPrecio());
            }
            
            iva = (acumSubtotal * 0.13);
            total = (acumSubtotal + iva);
            ActualizaFactura(listaCarrito.size(), iva, acumSubtotal, total, ultimaFactura);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error en InsertaProductosXFactura() " + e);
        }
        finally
        {
            desconectar();
        }
    }
    
    public int GetUltimaFactura()
    {
        Statement stmt = null;
        ResultSet rs = null;
        int ultimaFactura = 0;
        try
        {
            //
            String sql = "select max(idFactura) from facturas";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                ultimaFactura = rs.getInt("max(idFactura)");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error en GetUltimaFactura()" + e);
        }
        finally
        {
            desconectar();
        }
        return ultimaFactura;     
    }
    
    public void ActualizaFactura(int cantidad, double iva, double subtotal, double total, int idFactura)
    {
        try
        {
            conectar();
            String sql = "UPDATE facturas SET cantidad = ?, iva = ?, subTotal = ?, total= ? WHERE idFactura = ?";
            PreparedStatement preparedStmnt = con.prepareStatement(sql);
            preparedStmnt.setInt(1, cantidad);
            preparedStmnt.setDouble(2, iva);
            preparedStmnt.setDouble(3, subtotal);
            preparedStmnt.setDouble(4, total);
            preparedStmnt.setInt(5, idFactura);
            
            preparedStmnt.execute();
            
            cerrarPreparedStatement(preparedStmnt);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error en ActualizaFactura()" + e.toString());
        }
        finally
        {
            desconectar();
        }
    }
    
    public String RetornaNombreCompleto(String nombreUsuario)
    {
        Statement stmt = null;
        ResultSet rs = null;
        String nombreCompleto = "";
        try
        {
            conectar();
            String sql = "SELECT nombreCompleto FROM usuarios WHERE nombre_usuario = '" + nombreUsuario + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                nombreCompleto = rs.getString("nombreCompleto");
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error en RetornaNombreCompleto()" + e.toString());           
        }
        finally
        {
            desconectar();
        }
        return nombreCompleto;
    }
    
    public void eliminarFactura(FacturaTO fac)
    {
        try
        {
            conectar();
            String sql = "DELETE FROM facturas WHERE IdFactura = ?";
            PreparedStatement preparedstmt = con.prepareStatement(sql);
            preparedstmt.setInt(1, fac.getIdFactura());
            preparedstmt.execute();
            cerrarPreparedStatement(preparedstmt);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error debido a: " + ex.toString());
        } finally {
            desconectar();
        }
    }
    
    public String RetornaCorreoUsuario(String NombreUsuario)
    {
        Statement stmt = null;
        ResultSet rs = null;
        String correo = "";
        try
        {
            conectar();
            String sql = "SELECT email FROM usuarios WHERE nombre_usuario = '" + NombreUsuario + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next())
            {
                correo = rs.getString("email");
            }
            System.out.println(correo);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error en RetornaCorreoUsuario(): " + ex.toString());
        }
        finally {
            desconectar();
        }
        return correo;
    }
}
