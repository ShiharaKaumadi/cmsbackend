package lk.greenshadow.greens.service.impl;

import lk.greenshadow.greens.dto.impl.UserDTO;
import lk.greenshadow.greens.entity.CropEntity;
import lk.greenshadow.greens.entity.UserEntity;
import lk.greenshadow.greens.repo.UserRepo;
import lk.greenshadow.greens.service.UserService;
import lk.greenshadow.greens.util.AppUtil;
import lk.greenshadow.greens.util.Mappings;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private Mappings userMapping;
    @Override
    public UserDTO save(UserDTO dto) {
        dto.setId(AppUtil.generateUserId());
        return userMapping.toUserDTO(userRepo.save(userMapping.toUserEntity(dto)));
    }

    @Override
    public UserDTO update(String id, UserDTO dto) {
        return userMapping.toUserDTO(userRepo.save(userMapping.toUserEntity(dto)));
    }

    @Override
    public void delete(String id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDTO findById(String id) {
        Optional<UserEntity> byId = userRepo.findById(id);
        if (byId.isPresent()){
            return userMapping.toUserDTO(byId.get());
        }
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return userMapping.asUserDTOList(userRepo.findAll());
    }
}
