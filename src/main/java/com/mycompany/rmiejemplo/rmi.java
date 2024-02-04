/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.rmiejemplo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TICS
 */
public class rmi extends UnicastRemoteObject implements calculadora {

    public rmi() throws RemoteException {
        int a, b;
    }

    @Override
    public int div(int a, int b) throws RemoteException {
        return a / b;
    }

    @Override
    public int mul(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public int sub(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    //Consulta a la base de datos
    public List<Map<String, Object>> consulta() throws RemoteException {
        List<Map<String, Object>> listaResultados = new ArrayList<>();

        // Abrir la conexión a la BD 
        String cadenaConexion = String.format("jdbc:postgresql://localhost:%s/%s", 5432, "PracticaCrud");
        String query = "Select id_persona, nombres, apellidos, correro, direccion from persona";

        try {
            Connection conexion = DriverManager.getConnection(cadenaConexion, "postgres", "123456");
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    fila.put(columnName, value);
                }
                listaResultados.add(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(rmi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaResultados;
    }

    //para insertar
    @Override
    public void insertarDatos(String nombres, String apellidos, String correo, String direccion) throws RemoteException {
        // Abrir la conexión a la BD 
        String cadenaConexion = String.format("jdbc:postgresql://localhost:%s/%s", 5432, "PracticaCrud");

        try (Connection conexion = DriverManager.getConnection(cadenaConexion, "postgres", "123456")) {
                conexion.setAutoCommit(false);
            // Llamada al procedimiento almacenado de inserción
            String procedimientoInsercion = "CALL ingresar_persona(?, ?,?,?)";
            try (CallableStatement cs = conexion.prepareCall(procedimientoInsercion)) {
                // Configuración de parámetros para el procedimiento almacenado de inserción
                cs.setString(1, nombres);
                cs.setString(2, apellidos);
                cs.setString(3,correo);
                cs.setString(4, direccion);
                // Ejecución del procedimiento almacenado de inserción
                cs.execute();
                conexion.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(rmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para eliminar  --> eliminar
    @Override
    public void eliminar(int id_persona) throws RemoteException {
        // Abrir la conexión a la BD 
        String cadenaConexion = String.format("jdbc:postgresql://localhost:%s/%s", 5432, "PracticaCrud");

        try (Connection conexion = DriverManager.getConnection(cadenaConexion, "postgres", "123456")) {
                conexion.setAutoCommit(false);
            // Llamada al procedimiento almacenado de inserción
            String procedimientoInsercion = "CALL eliminar_persona(?)";
            try (CallableStatement cs = conexion.prepareCall(procedimientoInsercion)) {
                // Configuración de parámetros para el procedimiento almacenado de inserción
                cs.setInt(1, id_persona);
                // Ejecución del procedimiento almacenado de delete
                cs.execute();
                conexion.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(rmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //metodo para actualziar 
    @Override
    public void actualizar(int id_persona,String nombres, String apellidos, String correo, String direccion) throws RemoteException {
        // Abrir la conexión a la BD 
        String cadenaConexion = String.format("jdbc:postgresql://localhost:%s/%s", 5432, "PracticaCrud");

        try (Connection conexion = DriverManager.getConnection(cadenaConexion, "postgres", "123456")) {
                conexion.setAutoCommit(false);
            // Llamada al procedimiento almacenado de inserción
            String procedimientoInsercion = "CALL actualizar_persona(?,?,?,?,?)";
            try (CallableStatement cs = conexion.prepareCall(procedimientoInsercion)) {
                // Configuración de parámetros para el procedimiento almacenado de inserción
                cs.setInt(1, id_persona);
                cs.setString(2, nombres);
                cs.setString(3, apellidos);
                cs.setString(4,correo);
                cs.setString(5, direccion);
                // Ejecución del procedimiento almacenado de inserción
                cs.execute();
                conexion.commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(rmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
