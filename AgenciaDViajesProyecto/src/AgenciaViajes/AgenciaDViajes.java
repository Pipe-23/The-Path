/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgenciaViajes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Desa
 */
public class AgenciaDViajes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<ventaViajes> ventas = new ArrayList();
        ArrayList<String> actividades = new ArrayList();
        ventaViajes venta;
        Cliente cliente;
        int menu = 0;
        int CV = 0; //CV = Consecutivo de Venta
        int npP=0, npE=0, npT=0; //# Paquete Vendido
        
        do {
            do {
                menu = Integer.parseInt(JOptionPane.showInputDialog("Digite una de las siguientes opciones:\n"
                        + "1. Realizar venta paquete\n"
                        + "2. Mostrar los datos de las ventas\n"
                        + "3. Cancelación de paquete y devolucion de dinero\n"
                        + "4. Ventas totales\n"
                        + "5. Salir"));
            } while (menu < 1 || menu > 5);

            switch (menu) {
                case 1: //Realizar venta paquete
                    venta = new ventaViajes(); //instancia limpia del objeto venta
                    int valido=0;
                    String ced = "";
                    String edad = "";
                    cliente = new Cliente();
                    Paquete pt;
                    
                    //se contruye objeto Cliente
                    cliente.setId((int) (Math.random() * 100));
                    do {
                        valido=0;
                        ced = JOptionPane.showInputDialog("Digite la cédula del cliente");
                        for (int i = 0; i < ced.length(); i++) {
                            if (!Character.isDigit(ced.charAt(i))) {
                                JOptionPane.showMessageDialog(null, "entro");
                                valido++;
                            }
                        }
                    } while (valido>0);
                    cliente.setCedula(ced);
                    cliente.setNombre(JOptionPane.showInputDialog("Digite el nombre del cliente"));
                    do {
                        valido=0;
                        edad = JOptionPane.showInputDialog("Digite la edad del cliente");
                        for (int i = 0; i < edad.length(); i++) {
                            if (!Character.isDigit(edad.charAt(i))) {
                                valido++;
                            }
                        }
                    } while (valido>0);
                    cliente.setEdad(Integer.parseInt(edad));
                    boolean isText;
                     String txt = "";
                    do{
                        isText=true;
                         txt = JOptionPane.showInputDialog("Digite el estado civil del cliente");
                        if (txt.equalsIgnoreCase("soltero")){
                            isText=false;
                        }else if(txt.equalsIgnoreCase("divorciado")){
                             isText=false;
                        }else if(txt.equalsIgnoreCase("casado")){
                             isText=false;
                        }else{
                            JOptionPane.showMessageDialog(null, "El estado civil ingresado: " + txt + " no coincide con los estados Casado, Soltero, Divorciado");
                            isText=false;
                        }
                    }while(isText == true);
                    cliente.setEstadoCivil(txt);

                    char tipoDpaquete = ' ';
                    do {
                        tipoDpaquete = JOptionPane.showInputDialog("¿Que tipo de paquete de viaje desea comprar?\n"
                                + "P para Premium\n"
                                + "E para Economico\n"
                                + "T para Turistico").toUpperCase().charAt(0);
                    } while (tipoDpaquete != 'P' && tipoDpaquete != 'E' && tipoDpaquete != 'T');

                    if (tipoDpaquete == 'P') { //tipo de paquete comprado por el cliente
                        pt = new pPremium();
                        CV++;
                        npP++;
                        String destino;
                        boolean descuento;
                        boolean serhab;
                        boolean marca = false;
                        
                        //se limpia el arreglo de actividades para un nuevo paquete
                        actividades.clear();
                        
                        //se contruye objeto Paquete
                        pt.setNumPaquete(npP);
                        pt.setC(cliente);
                        char d;
                        do{
                            d = JOptionPane.showInputDialog("¿Cual destino desea para su paquete de viajes?\n"
                                + "A para Argentina\n"
                                + "E para España\n"
                                + "S para Suiza\n"
                                + "U para Estados Unidos\n"
                                + "I para Inglaterra\n"        
                                + "T para Turquia").toUpperCase().charAt(0);
                        }while(d!='A'&&d!='E'&&d!='S'&&d!='u'&&d!='i'&&d!='T');
                      
                        if (d == 'A'){
                            destino = "Argentina";
                        }else if(d == 'E'){
                            destino = "España";
                        }else if(d == 'S'){
                            destino = "Suiza";
                        }else if(d == 'U'){
                            destino = "Estados Unidos";
                        }else if(d == 'I'){
                            destino = "Inglaterra";
                        }else{
                            destino = "Turquia";
                        }
                        
                        pt.setDestino(destino);
                        
                        int pg = JOptionPane.showConfirmDialog(null, "Tiene descuento para aplicar a la compra del paquete?");
                        if(pg == 0){
                            descuento = true;
                        }else{
                            descuento = false;
                        }
                     
                        pt.setDescuento(descuento);
                        if(descuento){
                            pt.setCantDescuento(Integer.parseInt(JOptionPane.showInputDialog("Digite el monto de descuento a aplicar")));
                        }else {
                            pt.setCantDescuento(0);
                        }
                        
                        do{
                           int anho = Integer.parseInt(JOptionPane.showInputDialog(null, "Para que periodo o año hara valido el paquete"));
                           if(anho <= 2030 && anho >= 2020){
                               pt.setFecValida(Integer.toString(anho));
                               marca = true;
                           }
                        }while(marca==false);
                        ((pPremium)pt).setTipoSuite(JOptionPane.showInputDialog("¿Que tipo de suite quiere?\n"
                                + "Presidencial\n"
                                + "Ejecutiva\n"      
                                + "Matrimonial"));
                        
                        
                        pg = JOptionPane.showConfirmDialog(null, "Desea servicio a la habitacion?");
                        if(pg == 0){
                            serhab = true;
                        }else{
                            serhab = false;
                        }
                        
                        ((pPremium)pt).setSerHabitacion(serhab);
                        ((pPremium)pt).setTipoComida((JOptionPane.showInputDialog("¿Que tipo de comida para sus vacaciones?\n"
                                + "A la carta\n"
                                + "Buffete\n"      
                                + "Comidas Rapidas del Hotel")));
                        
                        int cont = 0;
                        do{
                            String act = JOptionPane.showInputDialog("¿Que tipo de actividades desea realizar en sus vacaciones?\n"
                                + "Canopy\n"
                                + "Tour motorizado a la Playa\n" 
                                + "Parapente\n"
                                + "Surfing\n"    
                                + "Buceo");
                            if(cont==0){
                                actividades.add(act);
                                cont=cont+1;
                            }else if (cont==1){
                                if(actividades.get(cont-1).equals(act)){
                                    JOptionPane.showMessageDialog(null, "La actividad seleccionada "+act+" ya ha sido registrada al paquete de viajes");
                                }else{
                                    actividades.add(act);
                                    cont=cont+1;
                                }
                            }else if (cont==2){
                                if(actividades.get(0).equals(act)||actividades.get(1).equals(act)){
                                    JOptionPane.showMessageDialog(null, "La actividad seleccionada "+act+" ya ha sido registrada al paquete de viajes");
                                }else{
                                    actividades.add(act);
                                    cont=cont+1;
                                }      
                            }else {
                                if(actividades.get(0).equals(act)||actividades.get(1).equals(act)||actividades.get(2).equals(act)){
                                    JOptionPane.showMessageDialog(null, "La actividad seleccionada "+act+" ya ha sido registrada al paquete de viajes");
                                }else{
                                    actividades.add(act);
                                    cont=cont+1;
                                }
                            }
                            
                        }while(actividades.size()<4);
                        
                        ((pPremium)pt).setActividades(actividades);
                        
                        
                        //se contruye objeto Venta
                        venta.setNumVenta(CV);
                        venta.setPaquete(pt);
                        venta.setMonto(0);
                        venta.setFecha(LocalDate.now().toString());
                        
                    } else if (tipoDpaquete == 'E') {
                        pt = new pEconomico();
                        CV++;
                        npE++;
                        String destino;
                        boolean descuento;
                        boolean acceso;
                        boolean marca = false;
                        pt.setNumPaquete(npE);
                        pt.setC(cliente);
                        char d = JOptionPane.showInputDialog("¿Cual destino desea para su paquete de viajes?\n"
                                + "A para Argentina\n"
                                + "E para España\n"
                                + "S para Suiza\n"
                                + "U para Estados Unidos\n"
                                + "I para Inglaterra\n"        
                                + "T para Turquia").toUpperCase().charAt(0);
                        
                        if (d == 'A'){
                            destino = "Argentina";
                        }else if(d == 'E'){
                            destino = "España";
                        }else if(d == 'S'){
                            destino = "Suiza";
                        }else if(d == 'U'){
                            destino = "Estados Unidos";
                        }else if(d == 'I'){
                            destino = "Inglaterra";
                        }else{
                            destino = "Turquia";
                        }
                        
                        pt.setDestino(destino);
                        
                        int pg = JOptionPane.showConfirmDialog(null, "Tiene descuento para aplicar a la compra del paquete?");
                        if(pg == 0){
                            descuento = true;
                        }else{
                            descuento = false;
                        }
                        
                        pt.setDescuento(descuento);
                        if(descuento){
                            pt.setCantDescuento(Integer.parseInt(JOptionPane.showInputDialog("Digite el monto de descuento a aplicar")));
                        }else {
                            pt.setCantDescuento(0);
                        }
                        
                        do{
                           int anho = Integer.parseInt(JOptionPane.showInputDialog(null, "Para que periodo o año hara valido el paquete"));
                           if(anho <= 2030 && anho >= 2020){
                               pt.setFecValida(Integer.toString(anho));
                               marca = true;
                           }
                        }while(marca==false);
                        
                        ((pEconomico)pt).setHabitaciones((JOptionPane.showInputDialog("¿Que tipo de habitacion quiere?\n"
                                + "Individual\n"
                                + "Habitacion doble\n"      
                                + "Habitacion King/Matrimonial")));
                        
                        int acc = JOptionPane.showConfirmDialog(null, "Desea incluir el acceso al restaurante?");
                        if(acc == 0){
                            acceso = true;
                        }else{
                            acceso = false;
                        }
                        
                        ((pEconomico)pt).setAccesoRestaurante(acceso);
                        char a = JOptionPane.showInputDialog("¿Quiere incluir el programa de actividades del hotel o su propio programa?\n"
                                + "H para Actividades Hotel\n"        
                                + "P para Actividades Personales").toUpperCase().charAt(0);
                        
                        ((pEconomico)pt).setTipoActividad(a);
                        
                        //se contruye objeto Venta
                        venta.setNumVenta(CV);
                        venta.setPaquete(pt);
                        venta.setMonto(0);
                        venta.setFecha(LocalDate.now().toString());
                        
                        
                    } else {
                        pt = new pTuristico();
                        CV++;
                        npT++;
                        String destino;
                        boolean descuento;
                        boolean transporte;
                        boolean marca = false;
                        pt.setNumPaquete(npT);
                        pt.setC(cliente);
                        char d = JOptionPane.showInputDialog("¿Cual destino desea para su paquete de viajes?\n"
                                + "A para Argentina\n"
                                + "E para España\n"
                                + "S para Suiza\n"
                                + "U para Estados Unidos\n"
                                + "I para Inglaterra\n"        
                                + "T para Turquia").toUpperCase().charAt(0);
                        
                        if (d == 'A'){
                            destino = "Argentina";
                        }else if(d == 'E'){
                            destino = "España";
                        }else if(d == 'S'){
                            destino = "Suiza";
                        }else if(d == 'U'){
                            destino = "Estados Unidos";
                        }else if(d == 'I'){
                            destino = "Inglaterra";
                        }else{
                            destino = "Turquia";
                        }
                        
                        pt.setDestino(destino);
                        
                        int pg = JOptionPane.showConfirmDialog(null, "Tiene descuento para aplicar a la compra del paquete?");
                        if(pg == 0){
                            descuento = true;
                        }else{
                            descuento = false;
                        }
                        
                        pt.setDescuento(descuento);
                        if(descuento){
                            pt.setCantDescuento(Integer.parseInt(JOptionPane.showInputDialog("Digite el monto de descuento a aplicar")));
                        }else {
                            pt.setCantDescuento(0);
                        }
                        
                        do{
                           int anho = Integer.parseInt(JOptionPane.showInputDialog(null, "Para que periodo o año hara valido el paquete"));
                           if(anho <= 2030 && anho >= 2020){
                               pt.setFecValida(Integer.toString(anho));
                               marca = true;
                           }
                        }while(marca==false);
                        
                        int st = JOptionPane.showConfirmDialog(null, "Requiere servicio de transportes?");
                        if(st == 0){
                            transporte = true;
                        }else{
                            transporte = false;
                        }
                        
                        ((pTuristico) pt).setTipoComida(JOptionPane.showInputDialog("¿Cual es el tipo de comida que desea para su paquete?\n"
                                + "Desayuno\n"
                                + "Almuerzo\n"
                                + "Cena"));
                        
                        int cont = 0;
                        do{
                            String act = JOptionPane.showInputDialog("¿Seleccione 2 actividades que dese realizar en sus vacaciones?\n"
                                + "Caminata a las Cataratas\n"
                                + "Esnorquel\n"    
                                + "Tour y Desgustacion en el Vinedo");
                            if(cont==0){
                                actividades.add(act);
                                cont=cont+1;
                            }else{
                                if(actividades.get(cont-1).equals(act)){
                                    JOptionPane.showMessageDialog(null, "La actividad seleccionada "+act+" ya ha sido registrada al paquete de viajes");
                                }else{
                                    actividades.add(act);
                                }
                            }
                        }while(actividades.size()<2);
                        
                        ((pTuristico) pt).setActividades(actividades);
                        
                        ((pTuristico) pt).setServicioTransporte(transporte);
                        ((pTuristico) pt).setTipoHospedaje(JOptionPane.showInputDialog("¿Cual es el tipo de hospedaje que desea para el  viaje?\n"
                                + "Hotel\n"
                                + "Cabaña"));
                        
                        //se contruye objeto Venta
                        venta.setNumVenta(CV);
                        venta.setPaquete(pt);
                        venta.setMonto(0);
                        venta.setFecha(LocalDate.now().toString());
                    }
                        

                    //agregar el objeto alquiler al ArrayList
                    ventas.add(venta);
                    break;
                case 2: //Mostrar los datos de las ventas
                    if (ventas.size() > 0) {
                        for (int i=0; i<ventas.size(); i++) {
                            JOptionPane.showMessageDialog(null,
                                    ventas.get(i).toString(),
                                    "DETALLE DE VENTAS",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,
                                    "No hay ventas de paquetes efectuadas",
                                    "SIN DETALLE DE LA VENTA",
                                    JOptionPane.WARNING_MESSAGE);
                    }
                    
                    break;
                case 3: //Cancelación de paquete y devolucion de dinero
                    
                    break;
                case 4: //Ventas totales
                    if (ventas.size() > 0) {
                        int cantpP = 0, cantpE = 0, cantpT = 0, montoT = 0;
                        for (int i = 0; i < ventas.size(); i++) {
                            if (ventas.get(i).getPaquete() instanceof pPremium) {
                                cantpP++;
                                montoT += ventas.get(i).getMonto();
                            } else if (ventas.get(i).getPaquete() instanceof pPremium) {
                                cantpE++;
                                montoT += ventas.get(i).getMonto();
                            } else {
                                cantpT++;
                                montoT += ventas.get(i).getMonto();
                            }
                        }
                        JOptionPane.showMessageDialog(null,
                                "Se vendieron \n"
                                + "Paquetes Premium: " + cantpP + "\n"
                                + "Paquetes Economicos: " + cantpE + "\n"
                                + "Paquetes Turistico: " + cantpT + "\n"
                                + "Dinero obtenido en ventas: " + montoT,
                                "DETALLE DE VENTAS TOTALES",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        JOptionPane.showMessageDialog(null,
                                    "No se ha efectuado ninguna venta",
                                    "SIN DETALLE DE LA VENTA",
                                    JOptionPane.WARNING_MESSAGE);
                    }

                    break;
                case 5: //Salir
                    JOptionPane.showMessageDialog(null, "Cerrando programa...");
                    System.exit(0); //finalizar el programa
                    break;
            }
        } while (menu != 5);
    }
}
