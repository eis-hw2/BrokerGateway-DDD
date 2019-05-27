package cn.pipipan.eisproject.brokergatewayddd.util;

import cn.pipipan.eisproject.brokergatewayddd.repository.TraderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    TraderRepository traderRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (traderRepository.findTraderByTraderNameEqualsAndPasswordEquals(username, password) == null) {
            throw new UsernameNotFoundException("密码错误");
        }
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
