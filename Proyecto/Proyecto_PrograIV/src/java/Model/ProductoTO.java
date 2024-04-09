package Model;
import java.sql.Blob;
/**
 *
 * @author Anthony Fernández
 */
public class ProductoTO {
    private Integer idProducto;
    private String  Nombre_Producto;
    private String Categoria;
    private double Precio;
    private Integer Inventario;
    private String Image;
    private Integer Cantidad;
    public ProductoTO(){
    }
    public ProductoTO(Integer idProducto, String Nombre_Producto, String Categoria, double Precio, 
            Integer Inventario, String Image, Integer Cantidad) {
        this.idProducto = idProducto;
        this.Nombre_Producto = Nombre_Producto;
        this.Categoria = Categoria;
        this.Precio = Precio;
        this.Inventario = Inventario;
        this.Image = Image;
        this.Cantidad = Cantidad;
    }
    public Integer getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombre_Producto() {
        return Nombre_Producto;
    }
    public void setNombre_Producto(String Nombre_Producto) {
        this.Nombre_Producto = Nombre_Producto;
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
    public Integer getInventario() {
        return Inventario;
    }
    public void setInventario(Integer Inventario) {
        this.Inventario = Inventario;
    }
    public String getImage() {
        return Image;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }

    public Integer getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Integer Cantidad) {
        this.Cantidad = Cantidad;
    }
    
}