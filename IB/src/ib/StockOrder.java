/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

import java.util.ArrayList;
import java.util.List;
import com.ib.client.VolumeCondition;
import com.ib.client.TimeCondition;
import com.ib.client.PercentChangeCondition;
import com.ib.client.MarginCondition;
import com.ib.client.ExecutionCondition;
import com.ib.client.PriceCondition;
import com.ib.client.Order;
import com.ib.client.OrderComboLeg;
import com.ib.client.OrderCondition;
import com.ib.client.OrderConditionType;
import com.ib.client.OrderType;
import com.ib.client.TagValue;

/**
 *
 * @author austinschaaf
 */
public class StockOrder {

    public static Order MarketOrder(String action, double quantity) {
        //! [market]
        Order order = new Order();
        order.action(action);
        order.orderType("MKT");
        order.totalQuantity(quantity);
        //! [market]
        return order;
    }
}
