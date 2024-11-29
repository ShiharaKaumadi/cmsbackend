package lk.greenshadow.greens.repo;

import lk.greenshadow.greens.entity.CropEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepo extends JpaRepository<CropEntity,String> {
}
