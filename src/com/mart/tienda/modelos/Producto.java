package com.mart.tienda.modelos;

import com.mart.tienda.aux.Generador;
import com.mart.tienda.interfaces.Descuento;

public class Producto implements Descuento {
    private final String id;
    private String nombre;
    private String desc;
    private int stock;
    private float precioUnitario;
    private float costoUnitario;
    private boolean disponible;
    private float descuento;

    // sin argumentos datos por defecto
    public Producto() {
        this.id = Generador.generarId(3);
        this.nombre = "Nombre indefinido";
        this.desc = "Desc indefinido";
        this.stock = 0;
        this.costoUnitario = 0;
        this.precioUnitario = 0;
        this.disponible = false;
        this.descuento = 0;
    }

    // datos del producto sin descuento
    public Producto(String nombre, String desc, int stock, float costoUnitario, float precioUnitario,
            boolean disponible) {
        this.id = Generador.generarId(3);
        this.nombre = nombre;
        this.desc = desc;
        this.stock = stock;
        this.costoUnitario = costoUnitario;
        this.precioUnitario = precioUnitario;
        this.disponible = disponible;
        this.descuento = 0;
    }

    // datos del producto con descuento
    public Producto(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario, boolean disponible, float descuento) {
        this.id = Generador.generarId(3);
        this.nombre = nombre;
        this.desc = desc;
        this.stock = stock;
        this.costoUnitario = costoUnitario;
        this.precioUnitario = precioUnitario;
        this.disponible = disponible;
        this.descuento = descuento;
    }

    // getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String showId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public float getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(float costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Descuento interface
    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        if (descuento >= 0 && descuento <= 1) {
            this.descuento = descuento;
        }
    }

    public float getPrecioDescuento() {
        // Si el descuento a aplicar genera pérdidas por el producto (su valor final de
        // venta es inferior al valor por el que se compró) éste no se aplicará
        if (this.precioUnitario * (1 - this.descuento) <= this.costoUnitario) {
            return this.precioUnitario;
        }
        return this.precioUnitario * (1 - this.descuento);
    }

    // logear atributos
    public void pstats() {
        System.out.printf(
                "Nombre: %s%nDesc: %s%nStock: %s%nPrecioUnitario: %s%nCostoUnitario: %s%nDisponible: %s%nDescuento: %s%n",
                this.nombre, this.desc, this.stock, this.getPrecioDescuento(), this.costoUnitario, this.disponible,

                this.descuento);
    }

}
