/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionsql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

public class ConexionSingleton {
    private static ConexionSingleton instancia = null;
    private static Connection conexion = null;

    private final static String urlConexion = "jdbc:mysql://127.0.0.1:3308/bdfullmotor?useSSL=false&serverTimezone=UTC";

    private ConexionSingleton() {
        try {
            String[] credenciales = leerCredenciales("C:\\Users\\user\\OneDrive\\Escritorio\\credenciales.txt");
            String usuario = credenciales[0];
            String contraseña = credenciales[1];

           
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(urlConexion, usuario, contraseña);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error al conectar con la base de datos: " + e.getMessage(), e);
        }
    }

    
    public static ConexionSingleton getInstancia() {
        if (instancia == null || conexion == null) {
            instancia = new ConexionSingleton();
        }
        return instancia;
    }

    
    public Connection getConexion() {
        return conexion;
    }

    // lee credenciales desde un archivo externo
    private String[] leerCredenciales(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String usuarioCifrado = br.readLine();
            String contraseñaCifrada = br.readLine();
            String usuario = descifrar(usuarioCifrado);
            String contraseña = (contraseñaCifrada == null || contraseñaCifrada.isEmpty())
                    ? ""
                    : descifrar(contraseñaCifrada);

            return new String[]{usuario, contraseña};
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo de configuración: " + e.getMessage(), e);
        }
    }

    // Descifrar las credenciales usando Base64
    private String descifrar(String datoCifrado) {
        try {
            return new String(Base64.getDecoder().decode(datoCifrado));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error al descifrar las credenciales: Dato no válido.", e);
        }
    }

    // Método para probar la conexión
    public void probarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                String consulta = "SELECT NOW() AS fechaActual";
                try (Statement stmt = conexion.createStatement();
                     ResultSet rs = stmt.executeQuery(consulta)) {

                    if (rs.next()) {
                        System.out.println("Conexión exitosa. Fecha actual del servidor: " + rs.getString("fechaActual"));
                    }
                }
            } else {
                System.out.println("La conexión no está activa.");
            }
        } catch (SQLException e) {
            System.err.println("Error al probar la conexión: " + e.getMessage());
        }
    }
}

