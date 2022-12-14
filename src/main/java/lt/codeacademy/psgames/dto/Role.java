package lt.codeacademy.psgames.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lt.codeacademy.psgames.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Role implements GrantedAuthority {
    private static final String ROLE_PREFIX = "ROLE_";

    private UUID id;
    private String name;

    @Override
    public String getAuthority() {
        return ROLE_PREFIX + name;
    }

    public static Role convert(RoleEntity entity) {
        return new Role(entity.getId(), entity.getName());
    }
}