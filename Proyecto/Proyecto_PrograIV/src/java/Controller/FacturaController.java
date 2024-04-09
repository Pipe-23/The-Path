package Controller;

import Model.FacturaTO;
import Model.UsuarioTO;
import Model.ProductoTO;
import Service.Correo;
import Service.ServicioFactura;
import Controller.ProductoController;
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
 * @author luisfelipecastro
 */
@ManagedBean(name = "facturaController")
@SessionScoped
public class FacturaController implements Serializable {

    private String nombreCompleto;
    private double iva,subTotal,total;
    private int cantidad;
    private ServicioFactura servicioFactura;
    private ProductoController productoController;
    private Correo correo;
    private List<FacturaTO> listaFacturas = new ArrayList<FacturaTO>();
    private FacturaTO selectedUsuario = new FacturaTO();

    public FacturaController() {
    }

    public FacturaController(String nombreCompleto, double iva, double subTotal, double total, int cantidad, ServicioFactura servicioFactura) {
        this.nombreCompleto = nombreCompleto;
        this.iva = iva;
        this.subTotal = subTotal;
        this.total = total;
        this.cantidad = cantidad;
        this.servicioFactura = servicioFactura;
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
    
    public void FinalizarCompra(List<ProductoTO> listaCarrito, String nombreUsuario)
    {
        try
        {
            if(listaCarrito.size() > 0)
            {
                String destinatario = "";
                String asunto = "";
                String mensaje = "";
                double acum = 0; 
                double iva = 0;
                double total = 0;

                System.out.println("Entró a Finalizar Compra");

                servicioFactura = new ServicioFactura();
                productoController = new ProductoController();

                String nombreCompleto = servicioFactura.RetornaNombreCompleto(nombreUsuario);
                destinatario = servicioFactura.RetornaCorreoUsuario(nombreUsuario);

                //Primer Paso
                servicioFactura.insertarFactura(nombreCompleto);

                //Segundo Paso: Productos por factura
                servicioFactura.InsertaProductosXFactura(listaCarrito);

                

                //Mandar Correo
                asunto = "Facturación compra realizada";
                mensaje = "Estimado(a): " + nombreCompleto + " muchas gracias por su compra. A continuación le brindamos los detalles de su compra: " +"\n" + "\n";
                //mensaje = "Cantidad      Detalle                  Precio"

                for (int i = 0; i < listaCarrito.size(); i++)
                {
                    mensaje = mensaje + listaCarrito.get(i).getCantidad() + "     " + listaCarrito.get(i).getNombre_Producto() + "     " + listaCarrito.get(i).getPrecio() + "\n";
                    acum = acum + listaCarrito.get(i).getPrecio();
                }
                
                iva = (acum * 0.13);
                total = (acum + iva); 

                mensaje = mensaje + "Subtotal: " + acum + "\n";
                mensaje = mensaje + "IVA: " + iva + "\n";
                mensaje = mensaje + "Total: " + total + "\n";
                mensaje = mensaje + "¡Muchas gracias por su preferencia!";

                System.out.println(mensaje);

                EnviarCorreo(destinatario, asunto, mensaje);
                
                listaCarrito.clear();
                
                productoController.setMontoCarrito(0.0);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡Compra realizada con éxito! Por favor verifique la factura en su correo electrónico"));
                PrimeFaces.current().ajax().update("form-toolbar:message-carrito", "form-carrito:products", "form-carrito:totalCarrito");
            }
            else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No existen productos registrados en el carrito. Por favor verifique"));
                PrimeFaces.current().ajax().update("form-toolbar:message-carrito", "form-carrito:products");
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error en FinalizarCompra()" + ex.toString());
        }
    }
    
    public void EnviarCorreo(String Destinatario, String Asunto, String Menasaje)
    {
        try
        {
            correo = new Correo();
            System.out.println("Entra a EnviarCorreo");
            correo.enviarCorreo(Destinatario, Asunto, Menasaje);
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Error en EnviarCorreo(): " + ex.toString());
        }
    }
    
    public boolean ValidaProductosEnCarrito(List<ProductoTO> listaCarrito)
    {
        boolean bandera = true;
        try
        {
            if(listaCarrito.isEmpty())
            {
                bandera = false;
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error en ValidaProductosEnCarrito()" + ex.toString());
        }
        
        return bandera;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ServicioFactura getServicioFactura() {
        return servicioFactura;
    }

    public void setServicioFactura(ServicioFactura servicioFactura) {
        this.servicioFactura = servicioFactura;
    }

    public List<FacturaTO> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<FacturaTO> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public FacturaTO getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(FacturaTO selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }
}
