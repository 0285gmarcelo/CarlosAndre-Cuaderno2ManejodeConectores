package com.ejercicio3.factory;

import com.ejercicio3.dao.*;
import com.ejercicio3.model.*;

public class DaoFactory {

    public static IDao<Videojuego, Integer> getVideojuegoDao() {
        return new VideojuegoDaoImpl();
    }

    public static IDao<Cliente, Integer> getClienteDao() {
        return new ClienteDaoImpl();
    }

    public static IDao<Venta, Integer> getVentaDao() {
        return new VentaDaoImpl();
    }
}
