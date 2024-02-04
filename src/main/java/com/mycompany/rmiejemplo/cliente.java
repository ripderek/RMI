/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiejemplo;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author TICS
 */
public class cliente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Registry miRegistro = LocateRegistry.getRegistry("localhost", 1099);
            calculadora c = (calculadora) Naming.lookup("//localhost/Calculadora");
                    while (true) {
                String menu = JOptionPane.showInputDialog("RMI Basico \n Ingresa los comandos \n"
                        + "Ingresa ls --> Listar \n"
                        + "Ingresa create --> Crear \n"
                        + "Ingresa delete --> Eliminar \n"
                        + "Ingresa update --> Actualizar \n");
                switch (menu) {
                    case "ls": {
                        Op1();
                        break;
                    }
                    case "create": {
                        Op2();
                        break;
                    }
                    case "delete": {
                        Op3();
                        break;
                    }
                      case "update": {
                        Op4();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El servidor no esta activo");
            System.out.println("Servidor no conectado" + e);
        }
    }

    //metodos para los casos 
    //ls
    public static void Op1() {

        try {
            Registry miRegistro = LocateRegistry.getRegistry("localhost", 1099);
            calculadora c = (calculadora) Naming.lookup("//localhost/Calculadora");
            List<Map<String, Object>> Resultado = c.consulta();
            JOptionPane.showMessageDialog(null, "Mira los resultados en la consola");

            //recorrer la lista para ver los resultados por consola 
            for (Map<String, Object> fila : Resultado) {
                System.out.println("------------------------------------------");

                for (Map.Entry<String, Object> entry : fila.entrySet()) {
                    String nombreColumna = entry.getKey();
                    Object valorColumna = entry.getValue();
                    System.out.println(nombreColumna + ": " + valorColumna);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Levanta primero el server");
            System.out.println("Servidor no conectado" + e);
        }
    }
    //create 
    public static void Op2(){
    try {
            Registry miRegistro = LocateRegistry.getRegistry("localhost", 1099);
            calculadora c = (calculadora) Naming.lookup("//localhost/Calculadora");
            String nombres =  JOptionPane.showInputDialog("Nombres");
            System.out.println(nombres);
            String apellidos =  JOptionPane.showInputDialog("Apellidos");
            String correo =  JOptionPane.showInputDialog("Correo");
            String direccion =  JOptionPane.showInputDialog("Direccion");
            c.insertarDatos(nombres, apellidos, correo, direccion);
            Op1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Levanta primero el server");
            System.out.println("Servidor no conectado" + e);
        }
    }
    //delete 
    public static void Op3(){
    try {
            Registry miRegistro = LocateRegistry.getRegistry("localhost", 1099);
            calculadora c = (calculadora) Naming.lookup("//localhost/Calculadora");
            int id_persona = Integer.parseInt(JOptionPane.showInputDialog("ID de la persona a eliminar"));
            c.eliminar(id_persona);
            Op1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Levanta primero el server");
            System.out.println("Servidor no conectado" + e);
        }
    }
    //update 
     public static void Op4(){
    try {
            Registry miRegistro = LocateRegistry.getRegistry("localhost", 1099);
            calculadora c = (calculadora) Naming.lookup("//localhost/Calculadora");
            int id_persona = Integer.parseInt(JOptionPane.showInputDialog("ID de la persona para actualizar"));
            String nombres =  JOptionPane.showInputDialog("Nombres");
            String apellidos =  JOptionPane.showInputDialog("Apellidos");
            String correo =  JOptionPane.showInputDialog("Correo");
            String direccion =  JOptionPane.showInputDialog("Direccion");
            c.actualizar(id_persona,nombres, apellidos, correo, direccion);
            Op1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Levanta primero el server");
            System.out.println("Servidor no conectado" + e);
        }
    }
}
