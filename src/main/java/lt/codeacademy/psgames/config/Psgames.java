package lt.codeacademy.psgames.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Setter
@Getter
@Configuration
@PropertySource("classpath:psgames.properties")
public class Psgames {
    @Value("${psgames.name}")
    private String name;
    @Value("${psgames.phone}")
    private String phone;
    @Value("${psgames.email}")
    private String email;
    @Value("${psgames.copy.rights}")
    private String copyRight;
    @Value("#{'${psgames.addresses}'.split(';')}")
    private List<String> addresses;
}