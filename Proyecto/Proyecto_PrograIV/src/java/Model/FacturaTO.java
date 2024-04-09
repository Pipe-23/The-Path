package Model;
import Model.ProductoTO;
import Model.UsuarioTO;
/**
 *
 * @author luisfelipecastro
 */
public class FacturaTO {
    
   private int IdFactura;
   private String nombreCompleto;
   private double IVA,subTotal,Total;
   private int cantidad;
   
   

    public FacturaTO() {
    }

    public FacturaTO(int IdFactura, String nombreCompleto, int cantidad,double IVA, double subTotal, double Total) {
        this.IdFactura = IdFactura;
        this.nombreCompleto = nombreCompleto;
        this.IVA = IVA;
        this.subTotal = subTotal;
        this.Total = Total;
        this.cantidad = cantidad;
    }
    
    public int getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(int IdFactura) {
        this.IdFactura = IdFactura;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}