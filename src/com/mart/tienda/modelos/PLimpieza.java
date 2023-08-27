package com.mart.tienda.modelos;

import com.mart.tienda.aux.Aplicado;

public class PLimpieza extends Producto {

    private Aplicado tipoAplicado;

    // sin argumentos datos por defecto
    public PLimpieza() {
        super();
        this.tipoAplicado = null;
    }

    // datos del producto sin descuento ni aplicado
    public PLimpieza(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario,
            boolean disponible) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible);
        this.tipoAplicado = null;
    }

    // datos del producto con descuento, sin aplicado
    public PLimpieza(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario,
            boolean disponible, float descuento) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible, descuento);
        this.tipoAplicado = null;
    }

    // datos del producto con descuento y aplicado
    public PLimpieza(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario,
            boolean disponible, float descuento, Aplicado aplicado) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible, descuento);
        this.tipoAplicado = aplicado;
    }

    // getters y setters
    public String getId() {
        return "AZ" + this.showId();
    }

    public Aplicado getTipoAplicado() {
        return tipoAplicado;
    }

    public void setTipoAplicado(Aplicado tipoAplicado) {
        this.tipoAplicado = tipoAplicado;
    }

    @Override
    public float getPrecioUnitario() {
        return this.getPrecioDescuento();
    }

    public void stats() {

        System.out.println("Id: " + this.getId());
        this.pstats();
        System.out.println("Aplicado: " + this.tipoAplicado);
    }

}
