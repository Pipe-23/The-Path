/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import Model.UsuarioTO;
import Controller.ProductoController;
import Service.ServicioUsuario;
import javax.swing.JOptionPane;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Anthony Fernández Chacón
 */
@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String nombreUsuario;
    private String contrasena;
    private ServicioUsuario servicioUsuario;
    private List<UsuarioTO> listaUsuarios = new ArrayList<UsuarioTO>();
    private UsuarioTO selectedUsuario = new UsuarioTO();
    private boolean esNuevo = false;
    
    public LoginController() {
    }

    public LoginController(String nombreUsuario, String contrasena, ServicioUsuario servicioUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.servicioUsuario = servicioUsuario;
    }

    public void ingresar() {

        try {
            System.out.println("EL VALOR INGRESADO PARA EL USER ES: " + this.getNombreUsuario());
            System.out.println("EL VALOR INGRESADO PARA EL PASS ES: " + this.getContrasena());

            if (this.getNombreUsuario() == null || "".equals(this.getContrasena())) {
                //ERROR
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campos inválidos", "El usuario no es correcto"));
            } else {
                servicioUsuario = new ServicioUsuario();
                boolean existe = servicioUsuario.existeUsuario(this.nombreUsuario, this.contrasena);
                if (existe) {
                    //Redireccionar
                    
                    //this.listaUsuarios = servicioUsuario.listarUsuarios();
                    this.redireccionar("/faces/principal.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticación", "Credenciales incorrectos"));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error debido a: " + ex.toString());
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }
    
    public void Back() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        this.redireccionar("/faces/index.xhtml");
    }
    
    public void openNew() {
        this.esNuevo = true;
        this.selectedUsuario = new UsuarioTO();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public List<UsuarioTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public UsuarioTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(UsuarioTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }
    
    
}
