package com.curso.config;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

@Component
@Order(1)
public class Conexion implements CommandLineRunner {
    private final DataSource dataSource;

    public Conexion(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Comprobando la conexion con PostgreSQL...");

        try(Connection conexion = dataSource.getConnection()){
            DatabaseMetaData info = conexion.getMetaData();
            System.out.println("CONEXION CORRECTA");
            System.out.println("  Base de datos: " + info.getDatabaseProductName()
                    + " " + info.getDatabaseProductVersion());
            System.out.println("  Usuario:       " + info.getUserName());
        } catch(Exception e){
            System.out.println("NO SE HA PODIDO CONECTAR");
            System.out.println("  Motivo: " + e.getMessage());
        }

    }
}
