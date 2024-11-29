package lk.greenshadow.greens.repo;

import lk.greenshadow.greens.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<LogEntity, String> {
}
