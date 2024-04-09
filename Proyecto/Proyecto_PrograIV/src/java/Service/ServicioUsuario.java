/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import Model.UsuarioTO;
import java.sql.PreparedStatement;

public class ServicioUsuario extends Conexion {

    private String conocerRol(Boolean rolBD) {
        Boolean roling = false;
        String rol = "";

        if (roling.equals(rolBD)) {
            rol = "Usuario";
        } else if (roling.equals(rolBD)) {
            rol = "Administrador";
        }
        return rol;
    }

    public boolean existeUsuario(String usuario, String contrasena) {

        Statement stmt = null;
        ResultSet rs = null;
        String vUsuario = "";
        String pass = "";
        try {
            conectar();
            stmt = con.createStatement();
            String sql = "SELECT * from usuarios WHERE nombre_usuario = '" + usuario + "' && contrasena = '" + contrasena + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                usuario = rs.getString("nombre_usuario");
                pass = rs.getString("contrasena");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(stmt);
            desconectar();
        }

        if (usuario.equals(usuario) && pass.equals(contrasena)) {
            System.out.println("Entró");
            return true;
        }
        return false;

    }
    
    public boolean obtieneTipoUsuario(String usuario, String contrasena)
    {
        Statement stmt = null;
        ResultSet rs = null;
        String tipoUsuario = "";
        try { 
            conectar();
            stmt = con.createStatement();
            String sql = "SELECT tipo_usuario from usuarios WHERE nombre_usuario = '" + usuario + "' && contrasena = '" + contrasena + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                tipoUsuario = rs.getString("tipo_usuario");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(stmt);
            desconectar();
        }

        if (tipoUsuario.equals("Administrador")) {
            return true;
        } else {
            return false;
        }
    }
    
    public void cambiarContrasenna(String usuario, String contrasenna) { //para usuario estandar (cambiar contraseña)
        try {
            conectar();
            String sql = "UPDATE usuarios SET contrasena = ? WHERE  nombre_usuario= ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, contrasenna);
            stmt.setString(2, usuario);
            stmt.execute();

            cerrarPreparedStatement(stmt);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            desconectar();
        }
    }
    
    public List<UsuarioTO> retornaUsuarios() {
        Statement stmt = null;
        ResultSet rs = null;
        List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();

        try {
            conectar();
            stmt = con.createStatement();
            String sql = "Select * from usuarios ";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("idUsuarios");
                String nombre = rs.getString("nombre_usuario");
                String contrasena = rs.getString("contrasena");
                String tipoUsuario = rs.getString("tipo_usuario");
                String estado = rs.getString("estado");
                String email = rs.getString("email");
                String nombreCompleto = rs.getString("nombreCompleto");

                UsuarioTO usuarioTO = new UsuarioTO(id, nombre, contrasena, tipoUsuario, estado, email, nombreCompleto);
                listaUsuarios.add(usuarioTO);
            }

        } catch (Exception ex) {

        } finally {
            cerrarResultSet(rs);
            cerrarStatement(stmt);
            desconectar();
        }

        return listaUsuarios;
    }

    public void actualizar(UsuarioTO user) { //para usuario estandar (cambiar contraseña o nombreUsuario)

        PreparedStatement stmt = null;

        try {
            conectar();
            
            String sql = "UPDATE usuarios SET nombre_usuario = ?, contrasena = ?, tipo_usuario = ?, estado = ?, email = ?, nombreCompleto = ? WHERE idUsuarios = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNombre_Usuario());
            stmt.setString(2, user.getContrasena());
            stmt.setString(3, user.getTipo_Usuario());
            stmt.setString(4, user.getEstado());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getNombreCompleto());
            stmt.setInt(7, user.getIdUsuario());
            stmt.execute();
            
            cerrarPreparedStatement(stmt);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            cerrarPreparedStatement(stmt);
            desconectar();
        }
    }

    public void EliminarUsuario(UsuarioTO usuario) {
        try {
            conectar();
            String sql = "DELETE FROM usuarios WHERE idUsuarios = ?";

            PreparedStatement preparedstmt = con.prepareStatement(sql);
            preparedstmt.setInt(1, usuario.getIdUsuario());
            preparedstmt.execute();

            cerrarPreparedStatement(preparedstmt);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error debido a: " + ex.toString());
        } finally {
            desconectar();
        }
    }

    public void InsertarUsuario(UsuarioTO usuario) {
        try {
            conectar();
            String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, tipo_usuario, estado, email, nombreCompleto) VALUES(?,?,?,?,?,?)";

            PreparedStatement preparedStmnt = con.prepareStatement(sql);
            preparedStmnt.setString(1, usuario.getNombre_Usuario());
            preparedStmnt.setString(2, usuario.getContrasena());
            preparedStmnt.setString(3, usuario.getTipo_Usuario());
            preparedStmnt.setString(4, usuario.getEstado());
            preparedStmnt.setString(5, usuario.getEmail());
            preparedStmnt.setString(6, usuario.getNombreCompleto());

            preparedStmnt.execute();

            cerrarPreparedStatement(preparedStmnt);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error debido a: " + ex.toString());
        } finally {
            desconectar();
        }
    }
    
    public boolean ValidaUsuarioCambioPass(String usuario)
    {
        Statement stmt = null;
        ResultSet rs = null;
        String vUsuario = "";
        String pass = "";
        try {
            conectar();
            stmt = con.createStatement();
            String sql = "SELECT * from usuarios WHERE nombre_usuario = '" + usuario + "'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                vUsuario = rs.getString("nombre_usuario");
                System.out.println("Valor de usuario: " + usuario);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            cerrarResultSet(rs);
            cerrarStatement(stmt);
            desconectar();
        }

        if (usuario.equals(vUsuario)) {
            return true;
        }
            return false;
        
    }
    
    public void InsertaUsuarioRegistro(String nombreUsuario, String contrasena, String email, String nombreCompleto)
    {
        try
        {
            conectar();
            String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, tipo_usuario, estado, email, nombreCompleto) VALUES(?,?,?,?,?,?)";
            
            PreparedStatement preparedStmnt = con.prepareStatement(sql);
            preparedStmnt.setString(1, nombreUsuario);
            preparedStmnt.setString(2, contrasena);
            preparedStmnt.setString(3, "reg");
            preparedStmnt.setString(4, "A");
            preparedStmnt.setString(5, email);
            preparedStmnt.setString(6, nombreCompleto);

            preparedStmnt.execute();

            cerrarPreparedStatement(preparedStmnt);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        } finally {
            desconectar();
        }
    }

}
