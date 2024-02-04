/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiejemplo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TICS
 */
public interface calculadora extends Remote {

    public int div(int a, int b) throws RemoteException;

    public int mul(int a, int b) throws RemoteException;

    public int sub(int a, int b) throws RemoteException;

    public int add(int a, int b) throws RemoteException;

    public List<Map<String, Object>> consulta() throws RemoteException;

    public void insertarDatos(String nombres, String apellidos, String correo, String direccion) throws RemoteException;

    public void eliminar(int id_persona) throws RemoteException;

    public void actualizar(int id_persona, String nombres, String apellidos, String correo, String direccion) throws RemoteException;

}
