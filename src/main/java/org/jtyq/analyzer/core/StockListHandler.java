package org.jtyq.analyzer.core;

import org.jtyq.analyzer.bean.PropertiesValue;
import org.jtyq.analyzer.bean.Stock;

import java.util.*;

public class StockListHandler extends Observable {

    private PropertiesValue propertiesValue;
    private List<Stock> stockList;



    public StockListHandler(PropertiesValue propertiesValue){
        this.propertiesValue = propertiesValue;
        stockList = new ArrayList<Stock>();
        init();
    }

    private void init() {
       Enumeration enumeration =  propertiesValue.getStockKeys();
       while (enumeration.hasMoreElements()){
           String key = (String) enumeration.nextElement();
           Stock  stock = new Stock();
           stock.setCode(key);
           stock.setName(propertiesValue.getStockName(key));
           this.stockList.add(stock);
       }
        Collections.sort(this.stockList);
//       this.stockListStatus = StockListStatus.INIT;
    }

    public List<Stock> getStockList(){
        return this.stockList;
    }

    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
}
