package com.mart.tienda.modelos;

import java.time.LocalDate;

import com.mart.tienda.auxi.CamposComida;
import com.mart.tienda.auxi.Envase;
import com.mart.tienda.interfaces.Comestible;

public class Envasado extends Producto implements Comestible {

    private Envase envase;
    private CamposComida camposComida;

    // sin argumentos datos por defecto
    public Envasado() {
        super();
        this.envase = null;
        this.camposComida = new CamposComida();
    }

    // datos del producto sin descuento ni campos de comida ni envase
    public Envasado(String nombre, String desc, int stock, float costoUnitario,
            float precioUnitario, boolean disponible) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible);
        this.envase = null;
        this.camposComida = new CamposComida();

    }

    // datos del producto con descuento, sin campos de comida ni envase
    public Envasado(String nombre, String desc, int stock, float costoUnitario, float precioUnitario,

            boolean disponible, float descuento) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible, descuento);
        this.envase = null;
        this.camposComida = new CamposComida();

    }

    // datos del producto con descuento, campos de comida y envase

    public Envasado(String nombre, String desc, int stock, float costoUnitario, float precioUnitario,
            boolean disponible, float descuento, Envase envase, CamposComida camposComida) {
        super(nombre, desc, stock, costoUnitario, precioUnitario, disponible);
        this.envase = envase;
        this.camposComida = camposComida;

    }

    // getters y setters
    public String getId() {
        return "AB" + this.showId();
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public CamposComida getCamposComida() {
        return camposComida;
    }

    public void setCamposComida(CamposComida camposComida) {
        this.camposComida = camposComida;
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
        System.out.println("Envase: " + this.envase);
        this.camposComida.pstats();
    }

}
