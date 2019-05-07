package cn.pipipan.eisproject.brokergatewayddd.util;

public interface DTOConvert<S, D> {
    D convertFrom(S s);
}
