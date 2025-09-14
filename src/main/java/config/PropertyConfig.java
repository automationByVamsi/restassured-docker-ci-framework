package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface PropertyConfig extends Config {


    @Key("baseurl")
    String baseUrl();

    @Key("username")
    String username();

    @Key("password")
    String password();

    @Key("connection.timeout")
    Integer connectionTimeout();

    @Key("socket.timeout")
    Integer socketTimeout();
}
