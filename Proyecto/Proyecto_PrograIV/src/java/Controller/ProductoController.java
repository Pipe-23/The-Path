package Controller;

import Model.ProductoTO;
import Service.ServicioProducto;
import Controller.FacturaController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.swing.JOptionPane;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Anthony Fernández
 */
@ManagedBean(name = "productoController")
@SessionScoped
public class ProductoController implements Serializable {

    private int id;
    private String NombreProdcuto;
    private String Categoria;
    private double Precio;
    private Integer Inventario;
    private double Total;
    private String Imagen;
    private Integer Cantidad;
    private List<ProductoTO> listaProductos = new ArrayList<ProductoTO>(); //Carrito
    private List<ProductoTO> listaProductosGeneral = new ArrayList<ProductoTO>();
    private ProductoTO selectedProducto = new ProductoTO();
    private ServicioProducto servicioProducto;
    public double MontoCarrito = 0.0;
    private String path = "C:\\Users\\Anthony Fernández\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto_PrograIV";
    
    private UploadedFile file;

    public ProductoController() {
        servicioProducto = new ServicioProducto();
        this.setListaProductosGeneral(servicioProducto.retornaProductos());
    }
    
    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {

        }
    }

    public void InsertarEnCarrito(ProductoTO producto) {
        try {
            servicioProducto = new ServicioProducto();
            ProductoTO productoCarrito = new ProductoTO();
            productoCarrito.setIdProducto(producto.getIdProducto());
            productoCarrito.setNombre_Producto(producto.getNombre_Producto());
            productoCarrito.setCategoria(producto.getCategoria());
            productoCarrito.setPrecio(producto.getPrecio());
            productoCarrito.setCantidad(producto.getCantidad());
            MontoCarrito = MontoCarrito + (productoCarrito.getPrecio() * producto.getCantidad());

            this.listaProductos.add(productoCarrito);
            this.listaProductos = this.getListaProductos();
            PrimeFaces.current().ajax().update("form-carrito:totalCarrito");
            PrimeFaces.current().ajax().update("form-carrito:products");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("¡Producto agregado al carrito!"));
            PrimeFaces.current().executeScript("PF('carritoProductDialog').hide()");
            PrimeFaces.current().ajax().update("form-listado:messages");
            //Update a Inventario
            servicioProducto.ReduceInventario(producto);
            PrimeFaces.current().ajax().update("form-listado:dt-products");
            this.listaProductosGeneral = servicioProducto.retornaProductos();
            PrimeFaces.current().ajax().update("form-carrito:products");
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error debido a: " + ex.toString());
            System.out.println("Error debido a: " + ex.toString());
        }
    }
    
        public void EliminarDeCarrito(ProductoTO producto) {
        try {
            servicioProducto = new ServicioProducto();
            MontoCarrito = MontoCarrito - (producto.getPrecio() * producto.getCantidad());
            
            this.listaProductos.remove(producto);
            this.listaProductos = this.getListaProductos();
            PrimeFaces.current().ajax().update("form-carrito:totalCarrito");
            PrimeFaces.current().ajax().update("form-carrito:products");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto eliminado del carrito"));
            PrimeFaces.current().executeScript("PF('carritoProductDialog').hide()");
            PrimeFaces.current().ajax().update("form-listado:messages");
            //Update a Inventario
            servicioProducto.AumentaInvetario(producto);
            PrimeFaces.current().ajax().update("form-listado:dt-products");
            this.listaProductosGeneral = servicioProducto.retornaProductos();
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en EliminarDeCarrito() : " + ex.toString());
        }
    }

    public List<ProductoTO> listarProductos() {
        try {
            servicioProducto = new ServicioProducto();
            this.setListaProductosGeneral(servicioProducto.retornaProductos());
        } catch (Exception ex) {
            System.out.println("Error debido a: " + ex.toString());
            JOptionPane.showMessageDialog(null, ex);
        }
        return listaProductosGeneral;
    }

    public void InsertarProducto() {
        try {
            System.out.println(this.getNombreProdcuto() + this.getCategoria() + this.getPrecio() + this.getInventario());
            servicioProducto = new ServicioProducto();
            this.listaProductosGeneral.add(this.selectedProducto);
            servicioProducto.InsertarProducto(this.getNombreProdcuto(), this.getCategoria(), this.getPrecio(), this.getInventario(), servicioProducto.urlImg() + file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Insertado Correctamente"));
            PrimeFaces.current().executeScript("PF('newProductDialog').hide()");
            PrimeFaces.current().ajax().update("form-listado:messages", "form-listado:dt-products");
            this.listaProductosGeneral = servicioProducto.retornaProductos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }
    
    public void ActualizarProducto(ProductoTO producto) {
        try {
            servicioProducto = new ServicioProducto();
            servicioProducto.ActualizarProducto(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Actualizado Correctamente"));
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form-listado:messages", "form-listado:dt-products");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void EliminarProducto(ProductoTO producto) {
        try {
            servicioProducto = new ServicioProducto();
            servicioProducto.EliminarProducto(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Producto Eliminado Correctamente"));
            PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
            PrimeFaces.current().ajax().update("form-listado:messages","form-listado:dt-products");
            this.listaProductosGeneral = servicioProducto.retornaProductos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    public void upload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        String fileName = uploadedFile.getFileName();
        String contentType = uploadedFile.getContentType();
        //Save it... Now
        try {
            copiaArchivo(file.getInputStream(), event.getFile().getFileName());
        } catch (Exception e) {
            e.getMessage();
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }

    public void copiaArchivo(InputStream in, String fileName) {
        servicioProducto = new ServicioProducto();
        try {
            OutputStream out = new FileOutputStream(new File(servicioProducto.urlImg() + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1);
            {
                out.write(bytes, 0, read);
            }
            this.selectedProducto.setImage(servicioProducto.urlImg() + fileName);
            this.listaProductosGeneral = servicioProducto.retornaProductos();
            in.close();
            out.flush();
            out.close();
            JOptionPane.showMessageDialog(null, "New file uploaded: " + (servicioProducto.urlImg() + fileName));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getLocalizedMessage());
        }
    }
    
    public void CompraFinalizada()
    {
        try
        {
            //this.listaProductos.clear();
            
            MontoCarrito = 0;
            PrimeFaces.current().ajax().update("form-carrito:totalCarrito");
            
            PrimeFaces.current().resetInputs("form-carrito:products");
            PrimeFaces.current().ajax().update("form-carrito:products");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error en CompraFinalizada(): " + ex.toString());
        }
    }
    
    public void RedireccionarInicio()
    {
        this.redireccionar("/faces/principal.xhtml");
    }
    
    
    public void NewProducto() {
        this.selectedProducto = new ProductoTO();
    }

    public List<ProductoTO> getListaProductosGeneral() {
        return listaProductosGeneral;
    }

    public void setListaProductosGeneral(List<ProductoTO> listaProductosGeneral) {
        this.listaProductosGeneral = listaProductosGeneral;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Integer getInventario() {
        return Inventario;
    }

    public void setInventario(Integer Inventario) {
        this.Inventario = Inventario;
    }

    public ServicioProducto getServicioProducto() {
        return servicioProducto;
    }

    public void setServicioProducto(ServicioProducto servicioProducto) {
        this.servicioProducto = servicioProducto;
    }

    public List<ProductoTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public ProductoTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoTO selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    public String getNombreProdcuto() {
        return NombreProdcuto;
    }

    public void setNombreProdcuto(String NombreProdcuto) {
        this.NombreProdcuto = NombreProdcuto;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public double getMontoCarrito() {
        return MontoCarrito;
    }

    public void setMontoCarrito(double MontoCarrito) {
        this.MontoCarrito = MontoCarrito;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer Cantidad) {
        this.Cantidad = Cantidad;
    }
}
