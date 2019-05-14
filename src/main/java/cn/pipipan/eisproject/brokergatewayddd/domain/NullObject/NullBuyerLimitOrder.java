package cn.pipipan.eisproject.brokergatewayddd.domain.NullObject;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrder;

public class NullBuyerLimitOrder extends LimitOrder {
    public int getUnitPrice(){
        return Integer.MIN_VALUE;
    }
}
