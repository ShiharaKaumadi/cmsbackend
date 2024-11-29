package lk.greenshadow.greens.repo;

import lk.greenshadow.greens.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<VehicleEntity,String> {
}
