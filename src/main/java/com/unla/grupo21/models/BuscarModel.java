package com.unla.grupo21.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class BuscarModel {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private boolean desde = true;
    private List<LugarModel> lugaresModel;
    private int lugarModel;

    public BuscarModel() {
    }

    public BuscarModel(LocalDate startDate, LocalDate endDate, boolean desde, List<LugarModel> lugaresModel, int lugarModel) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.desde = desde;
        this.lugaresModel = lugaresModel;
        this.lugarModel = lugarModel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isDesde() {
        return desde;
    }

    public void setDesde(boolean desde) {
        this.desde = desde;
    }

    public List<LugarModel> getLugaresModel() {
        return lugaresModel;
    }

    public void setLugaresModel(List<LugarModel> lugaresModel) {
        this.lugaresModel = lugaresModel;
    }

    public int getLugarModel() {
        return lugarModel;
    }

    public void setLugarModel(int lugarModel) {
        this.lugarModel = lugarModel;
    }
}
