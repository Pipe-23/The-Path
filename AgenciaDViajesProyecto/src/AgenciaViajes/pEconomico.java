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

public class pEconomico extends Paquete{
    private String habitaciones;
    private boolean accesoRestaurante;
    private char tipoActividad;

    public pEconomico() {
        super();
    }

    public pEconomico(String habitaciones, boolean accesoRestaurante, char tipoActividad, int numPaquete, Cliente c, String destino, boolean descuento, int cantDescuento, String fecValida) {
        super(numPaquete, c, destino, descuento, cantDescuento, fecValida);
        this.habitaciones = habitaciones;
        this.accesoRestaurante = accesoRestaurante;
        this.tipoActividad = tipoActividad;
    }

    public String getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(String habitaciones) {
        this.habitaciones = habitaciones;
    }

    public boolean isAccesoRestaurante() {
        return accesoRestaurante;
    }

    public void setAccesoRestaurante(boolean accesoRestaurante) {
        this.accesoRestaurante = accesoRestaurante;
    }

    public char getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(char tipoActividad) {
        this.tipoActividad = tipoActividad;
    }
    
    
}
