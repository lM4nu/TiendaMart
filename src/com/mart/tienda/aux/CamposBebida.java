package com.mart.tienda.aux;

public class CamposBebida {
    private boolean alcoholica;
    private float gradoAlc;

    public CamposBebida() {
        this.alcoholica = false;
        this.gradoAlc = 0.00f;
    }

    public CamposBebida(boolean alcoholica, float gradoAlc) {
        if (alcoholica && gradoAlc > 0.00f) {
            this.alcoholica = true;
            this.gradoAlc = gradoAlc;
        } else {
            this.alcoholica = false;
            this.gradoAlc = 0.00f;
        }
    }

    public CamposBebida(float gradoAlc) {
        if (gradoAlc > 0.00f) {
            this.alcoholica = true;
            this.gradoAlc = gradoAlc;
        } else {
            this.alcoholica = false;
            this.gradoAlc = 0.00f;
        }
    }

    public boolean isAlcoholica() {
        return alcoholica;
    }

    public void setAlcoholica(boolean alcoholica) {
        this.alcoholica = alcoholica;
    }

    public float getGradoAlc() {
        return gradoAlc;
    }

    public String getGradoAlcString() {
        return gradoAlc * 100 + "%";
    }

    public void setGradoAlc(float gradoAlc) {
        if (gradoAlc >= 0.00f) {
            this.gradoAlc = gradoAlc;
        }
    }

    public void pstats() {
        System.out.printf(
                "Alcoholica: %s%nGrado de alcohol: %s%n",
                this.alcoholica, this.gradoAlc);
    }

}
