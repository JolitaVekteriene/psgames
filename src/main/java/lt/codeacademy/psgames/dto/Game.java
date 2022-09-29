package lt.codeacademy.psgames.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.codeacademy.psgames.entity.GameEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private UUID id;
    @NotBlank
    @Size(min = 3, max = 40, message = "{validation.game.size}")
    private String name;
    @Size(max = 500)
    @NotBlank
    private String response;
    @NotBlank
    private String genre;
    @PositiveOrZero
    @NotNull
    private BigDecimal price;

    public static Game convert(GameEntity entity) {
        return new Game(entity.getId(),
                entity.getName(),
                entity.getResponse(),
                entity.getGenre(),
                entity.getPrice());
    }
}
