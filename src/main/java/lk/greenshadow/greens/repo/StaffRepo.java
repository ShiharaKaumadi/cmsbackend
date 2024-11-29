package lk.greenshadow.greens.repo;

import lk.greenshadow.greens.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<StaffEntity,String> {
}
