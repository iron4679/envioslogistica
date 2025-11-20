package com.grupo7.tpi.envioslogistica.dto;

public class CotizacionResponse {
    private int costo;
    private int etaDias;
    
    public CotizacionResponse() {
        // constructor vacio
    }

    public CotizacionResponse(int costo, int etaDias) {
        this.costo = costo;
        this.etaDias = etaDias;
    }

    public int getCosto() {
        return costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }
    public int getEtaDias() {
        return etaDias;
    }
    public void setEtaDias(int etaDias) {
        this.etaDias = etaDias;
    }
}