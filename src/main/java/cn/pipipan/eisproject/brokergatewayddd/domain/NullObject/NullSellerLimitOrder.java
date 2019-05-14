package cn.pipipan.eisproject.brokergatewayddd.domain.NullObject;

import cn.pipipan.eisproject.brokergatewayddd.domain.LimitOrder;

public class NullSellerLimitOrder extends LimitOrder {
    @Override
    public int getUnitPrice(){
        return Integer.MAX_VALUE;
    }
}
