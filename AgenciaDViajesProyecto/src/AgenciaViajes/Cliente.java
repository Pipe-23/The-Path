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

public class Cliente {
    private int id;
    private String cedula;
    private String nombre;
    private int edad;
    private String estadoCivil;

    public Cliente() {
    }

    public Cliente(int id, String cedula, String nombre, int edad, String estadoCivil) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
    }
    
    //métodos de la clase
 
    //metodos encapsulados 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    
          
}
