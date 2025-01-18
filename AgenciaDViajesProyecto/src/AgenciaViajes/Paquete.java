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

public abstract class Paquete {
    protected int numPaquete;
    protected Cliente c;
    protected String destino;
    protected boolean descuento;
    protected int cantDescuento;
    protected String fecValida;

    public Paquete() {
    }

    public Paquete(int numPaquete, Cliente c, String destino, boolean descuento, int cantDescuento, String fecValida) {
        this.numPaquete = numPaquete;
        this.c = c;
        this.destino = destino;
        this.descuento = descuento;
        this.cantDescuento = cantDescuento;
        this.fecValida = fecValida;
    }

        //metodos de la clase
    protected double totalDescuento(){
        double mtDescuento = this.getCantDescuento() / 1000;
        return  mtDescuento;
    }
    
    public int getNumPaquete() {
        return numPaquete;
    }

    public void setNumPaquete(int numPaquete) {
        this.numPaquete = numPaquete;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public void setDescuento(boolean descuento) {
        this.descuento = descuento;
    }

    public int getCantDescuento() {
        return cantDescuento;
    }

    public void setCantDescuento(int cantDescuento) {
        this.cantDescuento = cantDescuento;
    }

    public String getFecValida() {
        return fecValida;
    }

    public void setFecValida(String fecValida) {
        this.fecValida = fecValida;
    }
    
    
    
    
    
}
