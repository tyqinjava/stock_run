package org.jtyq.analyzer.observer;

import org.jtyq.analyzer.bean.Stock;
import org.jtyq.analyzer.core.StockListHandler;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class GUIShowObserver implements Observer {

    private static final String format1= "股票代码：%s;股票名:%s; 当前价格:%s;涨额: %s;涨幅: %s\n";

    private static final String format2= "%s;%s;%s;%s;%s\n";

    private JFrame jFrame;
    private JLabel jLabel;

    public GUIShowObserver(){
        jFrame = new JFrame();
        jFrame.setBounds(0,900,260,260);
        jLabel = new JLabel();
        Font font = new Font("宋体",Font.PLAIN,14);
        jLabel.setFont(font);
        JPanel panel = new JPanel();
        panel.add(jLabel);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(100, 100, 100, 300);
        panel.setPreferredSize(new Dimension(scrollPane.getWidth() - 50, scrollPane.getHeight()*2));
        jFrame.add(scrollPane);
        panel.revalidate();
        jFrame.setVisible(true);
    }

    @Override
    public void update(Observable o, Object arg) {
        StockListHandler stockListHandler = (StockListHandler) o;
        List<Stock> stockList = stockListHandler.getStockList();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<body>");
        for(Stock stock:stockList) {
            sb.append("<span>");
            sb.append(String.format(format2,stock.getCode(),stock.getName(),stock.getCurrentPrice(),stock.getIncrement(),stock.getGain()));
            sb.append("</span>");
            sb.append("<br>");
        }
        sb.append("</body>");
        sb.append("</html>");
        jLabel.setText(sb.toString());
    }

}
