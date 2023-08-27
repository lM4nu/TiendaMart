package com.mart.tienda.interfaces;

import java.time.LocalDate;

public interface Comestible {

    LocalDate getfVcto();

    void setfVcto(LocalDate fVcto);

    float getCalorias();

    void setCalorias(float calorias);

    float getKCalorias();

    void setKCalorias(float kcalorias);

}
