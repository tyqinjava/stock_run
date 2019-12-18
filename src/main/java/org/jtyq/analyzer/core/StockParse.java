package org.jtyq.analyzer.core;

import com.alibaba.fastjson.JSON;
import org.jtyq.analyzer.bean.Stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public interface StockParse {

    public void parse(String json);
}
