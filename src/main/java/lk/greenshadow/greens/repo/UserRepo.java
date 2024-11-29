package lk.greenshadow.greens.repo;

import lk.greenshadow.greens.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,String> {
}
