package Service;
import Model.ProductoTO;
import Model.UsuarioTO;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author Anthony Fernández
 */
public class ServicioProducto extends Conexion {
    public List<ProductoTO> retornaProductos(){
        Statement stmt = null;
        ResultSet rs = null;
        List<ProductoTO> listaUsuarios = new ArrayList<ProductoTO>();
        try{
            conectar();
            stmt = con.createStatement();
            String sql = "Select * from productos";
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                int idProducto = rs.getInt("idProducto");
                String NombreProducto = rs.getString("nombre_producto");
                String Categoria = rs.getString("categoria");
                double Precio = rs.getDouble("precio");
                int Inventario = rs.getInt("inventario");
                String Imagen = rs.getString("imagen");
                Integer Cantidad = null;
                ProductoTO productoTO = new ProductoTO(idProducto, NombreProducto, Categoria, Precio, Inventario, Imagen, Cantidad);
                listaUsuarios.add(productoTO);
            }
        } catch (Exception ex) {
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(stmt);
            desconectar();
        }
        return listaUsuarios;
    }
    
    public void InsertarProducto(String nombreProducto, String categoria, double precio, Integer inventario, String imagen){
        try
        {
            conectar();
            String sql = "INSERT INTO productos (nombre_producto, categoria, precio, inventario, imagen) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStmnt = con.prepareStatement(sql);
            preparedStmnt.setString(1, nombreProducto);
            preparedStmnt.setString(2, categoria);
            preparedStmnt.setDouble(3, precio);
            preparedStmnt.setInt(4, inventario);
            preparedStmnt.setString(5, imagen);
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
    public void EliminarProducto(ProductoTO producto)
    {
        try
        {
            conectar();
            //Borra en Intermedia
            String sql = "DELETE FROM productos_x_factura WHERE idProducto = ?";
            PreparedStatement preparedstmt = con.prepareStatement(sql);
            preparedstmt.setInt(1, producto.getIdProducto());
            preparedstmt.execute();
            cerrarPreparedStatement(preparedstmt);
            
            //Borra en Principal
            String sql1 = "DELETE FROM productos WHERE idProducto = ?";
            PreparedStatement preparedstmt1 = con.prepareStatement(sql1);
            preparedstmt1.setInt(1, producto.getIdProducto());
            preparedstmt1.execute();
            cerrarPreparedStatement(preparedstmt1);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error en EliminarProducto(): " + ex.toString());
            System.out.println("Error en EliminarProducto(): " + ex.toString());
            
        } finally {
            desconectar();
        }
    }
    
    public void ActualizarProducto(ProductoTO producto)
    {
        try
        {
            conectar();
            String sql = "UPDATE productos SET nombre_producto = ?, categoria = ?, precio = ?, inventario = ?, imagen = ? WHERE idProducto = ?";
            PreparedStatement preparedStmnt = con.prepareStatement(sql);
            preparedStmnt.setString(1, producto.getNombre_Producto());
            preparedStmnt.setString(2, producto.getCategoria());
            preparedStmnt.setDouble(3, producto.getPrecio());
            preparedStmnt.setInt(4, producto.getInventario());
            preparedStmnt.setString(5, producto.getImage());
            preparedStmnt.setInt(6, producto.getIdProducto());
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
    
    public String pathImg(){
        Statement stmt = null;
        ResultSet rs = null;
        String path = "";
        try
        {   
            conectar();
            stmt= con.createStatement();
             String sql = "SELECT valor from parametros where codgeneral = 'path_image'";
             rs = stmt.executeQuery(sql);
              while(rs.next())
            {
            path = rs.getString("valor");
            }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }finally {
            desconectar();
        }
        return path;
    }
    
    public String urlImg(){
        Statement stmt = null;
        ResultSet rs = null;
        String url = "";
        try
        {   
            conectar();
            stmt= con.createStatement();
             String sql = "SELECT valor from parametros where codgeneral = 'url_image'";
             rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                url = rs.getString("valor");
            }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }finally {
            desconectar();
        }
        return url;
    }
    
    public void ReduceInventario(ProductoTO producto)
    {
        Statement stmt = null;
        ResultSet rs = null;
        int inventarioActual = 0;
        int inventarioFinal = 0;
        
        try
        {
            conectar();
            stmt = con.createStatement();
            String sql = "SELECT inventario FROM productos WHERE idProducto =" + producto.getIdProducto().toString();
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                inventarioActual = rs.getInt("inventario");
            }
            
            inventarioFinal = (inventarioActual - producto.getCantidad());
            System.out.println(inventarioFinal);
            
            String update = "UPDATE productos SET inventario = ? WHERE idProducto = ?";
            PreparedStatement preparedStmnt = con.prepareStatement(update);
            preparedStmnt.setInt(1, inventarioFinal);
            preparedStmnt.setInt(2, producto.getIdProducto());
            preparedStmnt.execute();
            cerrarPreparedStatement(preparedStmnt);
        }
        catch(Exception e)
        {
            System.out.println("Error debido a: " + e.toString());
        }
        finally
        {
            desconectar();
        }
    }
    
    public void AumentaInvetario(ProductoTO producto)
    {
        Statement stmt = null;
        ResultSet rs = null;
        int inventarioActual = 0;
        int inventarioFinal = 0;
        
        try
        {
            conectar();
            stmt = con.createStatement();
            String sql = "SELECT inventario FROM productos WHERE idProducto =" + producto.getIdProducto().toString();
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                inventarioActual = rs.getInt("inventario");
            }
            
            inventarioFinal = (inventarioActual + producto.getCantidad());
            System.out.println(inventarioFinal);
            
            String update = "UPDATE productos SET inventario = ? WHERE idProducto = ?";
            PreparedStatement preparedStmnt = con.prepareStatement(update);
            preparedStmnt.setInt(1, inventarioFinal);
            preparedStmnt.setInt(2, producto.getIdProducto());
            preparedStmnt.execute();
            cerrarPreparedStatement(preparedStmnt);
        }
        catch(Exception e)
        {
            System.out.println("Error debido a: " + e.toString());
        }
        finally
        {
            desconectar();
        }
    }
    
    public boolean ProductoEnFactura(ProductoTO producto)
    {
        boolean bandera = false;
        Statement stmt = null;
        ResultSet rs = null;
        int cantidad = 0;
        try
        {
            conectar();
            stmt = con.createStatement();
            String sql = "SELECT COUNT(idProducto) FROM productos_x_factura WHERE idProducto =" + producto.getIdProducto().toString();
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                cantidad = rs.getInt("COUNT(idProducto)");
            }
            
            if(cantidad > 0)
            {
                bandera = true;
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error en ProductoEnFactura():" + ex.toString());
        }
        
        return bandera;
    }
    
}