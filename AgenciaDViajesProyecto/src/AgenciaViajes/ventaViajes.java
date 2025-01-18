/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenciaViajes;

/**
 *
 * @author Desa
 */

public class ventaViajes {
    private int numVenta;
    private String fecha;
    private Paquete paquete;
    private double monto;

    public ventaViajes() {
    }

    public ventaViajes(int numVenta, String fecha, Paquete paquete) {
        this.numVenta = numVenta;
        this.fecha = fecha;
        this.paquete = paquete;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVenta) {
        this.numVenta = numVenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
    @Override
    public String toString(){
        String salida = "Venta Viajes\n";
        String yesno = "";
        
        if (this.getPaquete() instanceof pPremium){
            salida += "Venta # " + this.getNumVenta() + "\n" + 
                "Fecha Venta " + this.getFecha() + "\n" + 
                "Cliente: " + this.getPaquete().getC().getNombre() + " - " + "\n" + 
                "Fecha de Valide: " + this.getPaquete().getFecValida() + "\n" + 
                "Tipo de paquete: " + this.getPaquete().getClass().getSimpleName()+ "\n" +
                "Destino: " + this.getPaquete().getDestino() + "\n" +
                "Monto aplicar de descuento: " + this.getPaquete().getCantDescuento()+ "\n" +
                "Monto Cancelado" + this.getMonto() + "\n" + 
                "Tipo Suite: " + ((pPremium)this.getPaquete()).getTipoSuite() + "\n" +
                "Tipo Comida: " + ((pPremium)this.getPaquete()).getTipoComida() + "\n" +
                "Actividades: " + ((pPremium)this.getPaquete()).getActividades();
        }else if(this.getPaquete() instanceof pEconomico){
            if(((pEconomico)this.getPaquete()).isAccesoRestaurante()){
                yesno = "Sin Ingreso";
            }else {
                yesno = "Ingreso habilitado";
            }
            salida += "Venta # " + this.getNumVenta() + "\n" + 
                "Fecha Venta " + this.getFecha() + "\n" + 
                "Cliente: " + this.getPaquete().getC().getNombre() + " - " + "\n" + 
                "Fecha de Valide: " + this.getPaquete().getFecValida() + "\n" + 
                "Tipo de paquete: " + this.getPaquete().getClass().getSimpleName()+ "\n" +
                "Destino: " + this.getPaquete().getDestino() + "\n" +
                "Monto aplicar de descuento: " + this.getPaquete().getCantDescuento()+ "\n" +
                "Monto Cancelado" + this.getMonto() + "\n" + 
                "Tipo Habitacion: " + ((pEconomico)this.getPaquete()).getHabitaciones() + "\n" +
                "Ingreso al restaurante: " + yesno + "\n" +
                "Programa de actividades: " + ((pEconomico)this.getPaquete()).getTipoActividad();
        }else {
            if(((pTuristico)this.getPaquete()).isServicioTransporte()){
                yesno = "Sin servicio";
            }else {
                yesno = "Servicio habilitado";
            }
            salida += "Venta # " + this.getNumVenta() + "\n" + 
                "Fecha Venta " + this.getFecha() + "\n" + 
                "Cliente: " + this.getPaquete().getC().getNombre() + " - " + "\n" + 
                "Fecha de Valide: " + this.getPaquete().getFecValida() + "\n" + 
                "Tipo de paquete: " + this.getPaquete().getClass().getSimpleName()+ "\n" +
                "Destino: " + this.getPaquete().getDestino() + "\n" +
                "Monto aplicar de descuento: " + this.getPaquete().getCantDescuento()+ "\n" +
                "Monto Cancelado" + this.getMonto() + "\n" + 
                "Tipo Comida: " + ((pTuristico)this.getPaquete()).getTipoComida() + "\n" +
                "Actividades: " + ((pTuristico)this.getPaquete()).getActividades() + "\n" +
                "Servicio de Transporte: " + yesno + "\n" +
                "Tipo Hospedaje: " + ((pTuristico)this.getPaquete()).getTipoHospedaje();
        }
        
        return salida;
    }
    
}
