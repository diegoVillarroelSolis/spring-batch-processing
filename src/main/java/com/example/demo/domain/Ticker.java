package com.example.demo.domain;

import java.math.BigDecimal;

public class Ticker {
    private long id;
    private BigDecimal price;
    private String ticker;

    public long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
