package com.fh.shop.common;

import java.io.Serializable;
import java.util.List;

public class DataTableResult implements Serializable {

    private Long draw;

    private Long recordsTotal;

    private Long recordsFiltered;

    private List data;

    public DataTableResult(){

    }

    public DataTableResult(Long draw, Long recordsTotal, Long recordsFiltered, List data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public Long getDraw() {
        return draw;
    }

    public void setDraw(Long draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
