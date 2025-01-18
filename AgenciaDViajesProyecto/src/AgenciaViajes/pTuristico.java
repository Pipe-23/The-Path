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

public class pTuristico extends Paquete{
    private String tipoComida;
    private ArrayList <String> actividades = new ArrayList();
    private boolean servicioTransporte;
    private String tipoHospedaje;

    public pTuristico() {
        super();
    }

    public pTuristico(String tipoComida, ArrayList actividades, boolean servicioTransporte, String tipoHospedaje, int numPaquete, Cliente c, String destino, boolean descuento, int cantDescuento, String fecValida) {
        super(numPaquete, c, destino, descuento, cantDescuento, fecValida);
        this.tipoComida = tipoComida;
        this.actividades = actividades;
        this.servicioTransporte = servicioTransporte;
        this.tipoHospedaje = tipoHospedaje;
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

    public boolean isServicioTransporte() {
        return servicioTransporte;
    }

    public void setServicioTransporte(boolean servicioTransporte) {
        this.servicioTransporte = servicioTransporte;
    }

    public String getTipoHospedaje() {
        return tipoHospedaje;
    }

    public void setTipoHospedaje(String tipoHospedaje) {
        this.tipoHospedaje = tipoHospedaje;
    }
    
    
}
