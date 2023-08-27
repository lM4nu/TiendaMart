package com.mart.tienda.modelos;

import java.time.LocalDate;

import com.mart.tienda.auxi.CamposBebida;
import com.mart.tienda.auxi.CamposComida;
import com.mart.tienda.interfaces.Comestible;

public class Bebida extends Producto implements Comestible {

    private CamposComida camposComida;
    private CamposBebida camposBebida;

    // sin argumentos datos por defecto
    public Bebida() {
        super();
        this.camposComida = new CamposComida();
        this.camposBebida = new CamposBebida();
    }

    // datos del producto sin descuento ni campos de bebida y comida
    public Bebida(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario,
            boolean disponible) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible);
        this.camposBebida = new CamposBebida();
        this.camposComida = new CamposComida();
    }

    // datos del producto con descuento, sin campos de bebida y comida
    public Bebida(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario,
            boolean disponible, float descuento) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible, descuento);
        this.camposBebida = new CamposBebida();
        this.camposComida = new CamposComida();
    }

    // datos del producto con descuento y campos de bebida y comida
    public Bebida(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario,
            boolean disponible, float descuento, CamposBebida camposBebida, CamposComida camposComida) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible, descuento);
        this.camposBebida = camposBebida;
        this.camposComida = camposComida;
    }

    // getters y setters
    public String getId() {
        return "AC" + this.showId();
    }

    public CamposComida getCamposComida() {
        return camposComida;
    }

    public void setCamposComida(CamposComida camposComida) {
        this.camposComida = camposComida;
    }

    public CamposBebida getCamposBebida() {
        return camposBebida;
    }

    public void setCamposBebida(CamposBebida camposBebida) {
        this.camposBebida = camposBebida;
    }

    @Override
    public float getPrecioUnitario() {
        if (this.getCamposComida().isImportado()) {
            return this.getPrecioDescuento() * 1.1f;
        }
        return this.getPrecioDescuento();
    }

    // Comestible interface
    public void setfVcto(LocalDate fVcto) {
        this.camposComida.setfVcto(fVcto);
    }

    public LocalDate getfVcto() {
        return this.camposComida.getfVcto();
    }

    public float getCalorias() {
        return camposComida.getCalorias();
    }

    public void setCalorias(float calorias) {
        this.camposComida.setCalorias(calorias);
    }

    public float getKCalorias() {
        return this.camposComida.getCalorias() / 1000f;
    }

    public void setKCalorias(float kcalorias) {
        if (kcalorias >= 0) {
            this.camposComida.setCalorias(kcalorias * 1000);
        }
    }

    public void stats() {
        System.out.println("Id: " + this.getId());
        this.pstats();
        this.camposComida.pstats();
        this.camposBebida.pstats();
    }

}
