package com.example.demo.dao;

import com.example.demo.domain.Ticker;

public interface TickerDao {
    Ticker findTickerBySymbol(String ticker);

    void saveTicker(Ticker newTicker);
}
