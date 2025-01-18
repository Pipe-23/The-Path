/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenciaViajes;

import java.util.ArrayList;

/**
 *
 * @author Desa
 */

public class pPremium extends Paquete{
    private String tipoSuite;
    private boolean serHabitacion;
    private String tipoComida;
    private ArrayList <String> actividades = new ArrayList();

    public pPremium() {
        super();
    }

    public pPremium(String tipoSuite, boolean serHabitacion, String tipoComida, ArrayList actividades, int numPaquete, Cliente c, String destino, boolean descuento, int cantDescuento, String fecValida) {
        super(numPaquete, c, destino, descuento, cantDescuento, fecValida);
        this.tipoSuite = tipoSuite;
        this.serHabitacion = serHabitacion;
        this.tipoComida = tipoComida;
        this.actividades = actividades;
    }

    public String getTipoSuite() {
        return tipoSuite;
    }

    public void setTipoSuite(String tipoSuite) {
        this.tipoSuite = tipoSuite;
    }

    public boolean isSerHabitacion() {
        return serHabitacion;
    }

    public void setSerHabitacion(boolean serHabitacion) {
        this.serHabitacion = serHabitacion;
    }

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public ArrayList<String> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<String> actividades) {
        this.actividades = actividades;
    }

    
    
    
}
