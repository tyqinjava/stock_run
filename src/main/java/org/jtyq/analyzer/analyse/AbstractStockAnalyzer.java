//package org.jtyq.analyzer.analyse;
//
//import org.jtyq.analyzer.bean.StockListEvent;
//import org.jtyq.analyzer.bean.StockListStatus;
//import org.jtyq.analyzer.core.StockListHandler;
//
//public abstract class AbstractStockAnalyzer implements StockAnalyzer {
//
//    protected StockListHandler stockListHandler;
//
//    public AbstractStockAnalyzer(){
//    }
//
//    public StockListHandler getStockListHandler() {
//        return stockListHandler;
//    }
//
//    public void setStockListHandler(StockListHandler stockListHandler) {
//        this.stockListHandler = stockListHandler;
//    }
//
//    public void analyze(){
//        while(true){
//            if(stockListHandler.getStockListStatus() == StockListStatus.INIT) {
//                StockListEvent stockListEvent = new StockListEvent(stockListHandler.getStockList());
//                initStockList(stockListEvent);
//                stockListHandler.setStockListStatus(StockListStatus.DEAL_WITH);
//            }else if(stockListHandler.getStockListStatus() ==  StockListStatus.PRE_UPDATE){
//                StockListEvent stockListEvent = new StockListEvent(stockListHandler.getStockList());
//                preStockListUpdate(stockListEvent);
//                stockListHandler.setStockListStatus(StockListStatus.DEAL_WITH);
//            }else if(stockListHandler.getStockListStatus() == StockListStatus.AFTER_UPDATE){
//                StockListEvent stockListEvent = new StockListEvent(stockListHandler.getStockList());
//                afterStockListUpdate(stockListEvent);
//                stockListHandler.setStockListStatus(StockListStatus.DEAL_WITH);
//            }
//            try {
//                //cpu不至于占用过高
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
