package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.Stock;

public interface StockParse {

    Stock parse(String json);
}
