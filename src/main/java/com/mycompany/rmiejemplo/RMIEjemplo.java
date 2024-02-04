/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.rmiejemplo;

import java.rmi.registry.Registry;
import javax.swing.JOptionPane;

/**
 *
 * @author TICS
 */
public class RMIEjemplo {

    public static void main(String[] args) {
        try {
            Registry r = java.rmi.registry.LocateRegistry.createRegistry(1099);
            r.rebind("Calculadora", new rmi());
            JOptionPane.showMessageDialog(null, "Conectado al Servidor");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No conectado al servidor");
        }
    }
}
