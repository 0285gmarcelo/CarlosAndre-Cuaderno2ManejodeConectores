package com.ejercicio3.model;

import java.sql.Date;

public class Venta {
    private int id;
    private int idCliente;
    private int idVideojuego;
    private int cantidad;
    private double total;
    private Date fecha;

    public Venta(int id, int idCliente, int idVideojuego,
                 int cantidad, double total, Date fecha) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVideojuego = idVideojuego;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public int getIdCliente() { return idCliente; }
    public int getIdVideojuego() { return idVideojuego; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }
    public Date getFecha() { return fecha; }

    @Override
    public String toString() {
        return id + " - Cliente: " + idCliente
                + " - Videojuego: " + idVideojuego
                + " - Cantidad: " + cantidad
                + " - Total: " + total
                + " - Fecha: " + fecha;
    }
}
