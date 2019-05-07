package cn.pipipan.eisproject.brokergatewayddd.domain;

import cn.pipipan.eisproject.brokergatewayddd.util.DTOConvert;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LimitOrderDTO {
    static class Convert implements DTOConvert<LimitOrderDTO, LimitOrder> {

        @Override
        public LimitOrder convertFrom(LimitOrderDTO limitOrderDTO) {
            LimitOrder limitOrder = new LimitOrder();
            BeanUtils.copyProperties(limitOrder, limitOrderDTO);
            return limitOrder;
        }
    }

    public LimitOrder convertToLimitOrder(){
        Convert convert = new Convert();
        return convert.convertFrom(this);
    }
    @Id
    private String id;
    private String futureId;
    private int count;
    private int unitPrice;
    private LimitOrder.Side side;

    public String getId() {
        return id;
    }

    public String getFutureId() {
        return futureId;
    }

    public void setFutureId(String futureId) {
        this.futureId = futureId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LimitOrder.Side getSide() {
        return side;
    }

    public void setSide(LimitOrder.Side side) {
        this.side = side;
    }

    public void setId(String id) {
        this.id = id;
    }
}
