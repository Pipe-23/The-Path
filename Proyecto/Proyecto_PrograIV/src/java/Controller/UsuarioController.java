/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.UsuarioTO;
import Service.ServicioProducto;
import Service.ServicioUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import org.primefaces.PrimeFaces;

/**
 *
 * @author luisf
 */
@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private UsuarioTO usuario;
    private String contrasena;
    private String nombreUsuario;
    private String tipoUsuario;
    private String estado;
    private String email;
    private String nombreCompleto;
    private List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();
    private UsuarioTO selectedUsuario = new UsuarioTO();
    private boolean esNuevo = false;
    private ServicioUsuario serviciousuario;

    public UsuarioController() {
        serviciousuario = new ServicioUsuario();
        this.setListaUsuarios(serviciousuario.retornaUsuarios());
    }
    
    public void ingresar() {
        System.out.println("EL VALOR INGRESADO PARA EL USER ES: " + this.getNombreUsuario());
        System.out.println("EL VALOR INGRESADO PARA EL PASS ES: " + this.getContrasena());
        System.out.println("EL VALOR INGRESADO PARA EL ESTADO ES: " + this.getEstado());
        System.out.println("Metodo: " + this.validaTipoUsuario(this.getNombreUsuario(), this.getContrasena()));

        if ("".equals(this.getNombreUsuario()) || "".equals(this.getContrasena()) || "".equals(this.getEstado())) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos inválidos", "El usuario no es correcto"));
        } else {
            serviciousuario = new ServicioUsuario();
            boolean existe = serviciousuario.existeUsuario(nombreUsuario, contrasena);
            if (existe) {
                //Redireccionar
                this.listaUsuarios = serviciousuario.retornaUsuarios();
                this.redireccionar("/faces/principal.xhtml");
            } else {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticación", "Credenciales incorrectos"));
            }
        }
    }
    
    public boolean validaTipoUsuario(String nombre_usuario, String contra)
    {
       boolean retorno;
       retorno = serviciousuario.obtieneTipoUsuario(nombre_usuario, contra);
       return retorno;
    }

    public List<UsuarioTO> listarUsuarios() {
        try {
            serviciousuario = new ServicioUsuario();
            this.listaUsuarios = serviciousuario.retornaUsuarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error debido a: " + ex);
        }
        return listaUsuarios;
    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }
    
    public void EliminarUsuario(UsuarioTO usuario) {
        try {
            serviciousuario = new ServicioUsuario();
            serviciousuario.EliminarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Eliminado Correctamente"));
            PrimeFaces.current().executeScript("PF('deleteUserDialog').hide()");
            PrimeFaces.current().ajax().update("form-usuario:dt-usuarios");
            this.listaUsuarios = serviciousuario.retornaUsuarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void newUser() {
        try {
            serviciousuario = new ServicioUsuario();
            UsuarioTO usuario = new UsuarioTO();
            usuario.setNombre_Usuario(this.getNombreUsuario());
            usuario.setContrasena(this.getContrasena());
            usuario.setTipo_Usuario(this.getTipoUsuario());
            usuario.setEstado(this.getEstado());
            usuario.setEmail(this.getEmail());
            usuario.setNombreCompleto(this.nombreCompleto);
            serviciousuario.InsertarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Creado"));
            PrimeFaces.current().executeScript("PF('newUserDialog').hide()");
            PrimeFaces.current().ajax().update("form-usuario:messages", "form-usuario:dt-usuarios");
            this.listaUsuarios = serviciousuario.retornaUsuarios();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void CambiarContrasena()
    {
        try
        {
            System.out.println(this.getNombreUsuario() +" " + this.getContrasena());
            serviciousuario = new ServicioUsuario();
            boolean bandera = serviciousuario.ValidaUsuarioCambioPass(this.getNombreUsuario());
            System.out.println(bandera);
            if(bandera)
            {
                serviciousuario.cambiarContrasenna(this.getNombreUsuario(), this.getContrasena());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambio realizado con éxito. Por favor ingrese nuevamente"));
                this.redireccionar("/faces/index.xhtml");
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verificación", "Nombre de usuario inválido"));
            }
        } 
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void IngresaUsuarioRegistro()
    {
        try
        {
            System.out.println(this.nombreUsuario + " " + this.contrasena + " " + this.email);
            serviciousuario = new ServicioUsuario();
            //Valida que el nombre de usuario ingresado no exista:
            boolean bandera = serviciousuario.ValidaUsuarioCambioPass(this.nombreUsuario);
            if(bandera)
            {
                //Si existe
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verificación", "El nombre de usuario digitado ya se encuentra en uso, por favor digite otro."));
            }
            else
            {
                //No existe
                serviciousuario.InsertaUsuarioRegistro(this.nombreUsuario, this.contrasena, this.email, this.nombreCompleto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡Usuario registrado exitosamente!"));
                this.redireccionar("/faces/index.xhtml");
            }
        }
        catch (Exception ex)
        {
            
        }
    }

    public void openNew() {
        this.esNuevo = true;
        this.selectedUsuario = new UsuarioTO();
    }

    public void updateUser(UsuarioTO user) {
        try {
            serviciousuario = new ServicioUsuario();
            serviciousuario.actualizar(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Acualizado Correctamente"));
            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
            PrimeFaces.current().ajax().update("form-usuario:messages", "form-usuario:dt-usuarios");
            this.listaUsuarios = serviciousuario.retornaUsuarios();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public boolean validarUsuario() {
        boolean result;
        
        if(this.getTipoUsuario().equals("admin"))
        {
            result = true;
        }
        else
        {
            result = false;
        }
        
        return result;
    }

    public UsuarioTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioTO usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<UsuarioTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ServicioUsuario getServiciousuario() {
        return serviciousuario;
    }

    public void setServiciousuario(ServicioUsuario serviciousuario) {
        this.serviciousuario = serviciousuario;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
}
