package com.web.oauth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class ClientResources {

    @NestedConfigurationProperty    //해당필드가 단일값이 아닌 중복으로 바인딩된다고 표시되는 어노테이션
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
    //클라이언트의 하위 키/값을 매핑해주는 객체

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();
    //리소스값을 매핑하는데 사용하지만 여기서는 회원 정보를 얻는 UserInfoUri값을 받는데 사용

    public AuthorizationCodeResourceDetails getClient(){
        return client;
    }

    public ResourceServerProperties getResource(){
        return resource;
    }
}
