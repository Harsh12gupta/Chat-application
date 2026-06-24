package com.ca.apigateway.filter;

import com.ca.apigateway.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class JwtValidationGatewayFilterFactory extends AbstractGatewayFilterFactory<JwtValidationGatewayFilterFactory.Config> {
    //study from docs for more ref about cloud gateways
    private final JwtUtil jwtUtil;
    public JwtValidationGatewayFilterFactory(JwtUtil jwtUtil){
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {

    };

    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) ->{
            try{
                String authHeaders = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                if(authHeaders==null || !authHeaders.startsWith("Bearer "))
                {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
                String token = authHeaders.substring(7);
                String id = jwtUtil.extractUserId(token);
                var modifiedRequest =  exchange.getRequest().mutate().header("X-User-Id",id).build();
                var modifiedExchange = exchange.mutate().request(modifiedRequest).build();
                return chain.filter(modifiedExchange);
            }
            catch (Exception ex)
            {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }
}
