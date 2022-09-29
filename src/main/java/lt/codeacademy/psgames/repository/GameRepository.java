package lt.codeacademy.psgames.repository;

import lt.codeacademy.psgames.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<GameEntity, UUID> {
    List<GameEntity> findAllByGenre(String genre);

    @Query("SELECT p FROM GameEntity p WHERE p.genre = :genre AND p.price > :price")
    List<GameEntity> getGamesByGenreAndPrice(@Param("genre") String genre, @Param("price")BigDecimal price);
}