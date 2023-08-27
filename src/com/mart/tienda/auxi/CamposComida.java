package com.mart.tienda.auxi;

import java.time.LocalDate;

public class CamposComida {
    private boolean importado;
    private float calorias;
    private LocalDate fVcto;

    public CamposComida() {
        this.importado = false;
        this.calorias = 0;
        this.fVcto = LocalDate.now().plusYears(3);
    }

    public CamposComida(boolean importado, float calorias, LocalDate fVcto) {
        this.importado = importado;
        if (calorias >= 0) {
            this.calorias = calorias;
        } else {
            this.calorias = 0;
        }
        if (fVcto.isAfter(LocalDate.now())) {
            this.fVcto = fVcto;
        } else {
            this.fVcto = LocalDate.now().plusYears(3);
        }
    }

    public CamposComida(boolean importado, float calorias) {
        this.importado = importado;
        if (calorias >= 0) {
            this.calorias = calorias;
        } else {
            this.calorias = 0;
        }
        this.fVcto = LocalDate.now().plusYears(3);
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        if (calorias >= 0) {
            this.calorias = calorias;
        }
    }

    public LocalDate getfVcto() {
        return fVcto;
    }

    public void setfVcto(LocalDate fVcto) {
        if (fVcto.isAfter(LocalDate.now())) {
            this.fVcto = fVcto;
        } else {
            System.out.println("La fecha es invalida, es anterior a la fecha actual");
        }
    }

    public void pstats() {
        System.out.printf(
                "Importado: %s%nCalorias: %s%nVencimiento: %s%n",
                this.importado, this.calorias, this.fVcto);

    }
}
