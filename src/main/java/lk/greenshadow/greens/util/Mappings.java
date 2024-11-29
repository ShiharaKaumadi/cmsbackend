package lk.greenshadow.greens.util;

import lk.greenshadow.greens.dto.impl.*;
import lk.greenshadow.greens.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mappings {
    @Autowired
    private ModelMapper modelMapper;
    public CropEntity toCropEntity(CropDTO cropDTO){
        return modelMapper.map(cropDTO, CropEntity.class);
    }
    public CropDTO toCropDTO(CropEntity cropEntity){
        return modelMapper.map(cropEntity,CropDTO.class);
    }
    public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO){
        return modelMapper.map(equipmentDTO,EquipmentEntity.class);
    }
    public EquipmentDTO toEquipmentDTO(EquipmentEntity equipmentEntity){
        return modelMapper.map(equipmentEntity,EquipmentDTO.class);
    }
    public FieldEntity toFieldEntity(FieldDTO fieldDTO){
        return modelMapper.map(fieldDTO,FieldEntity.class);
    }
    public FieldDTO toFieldDTO(FieldEntity fieldEntity){
        return modelMapper.map(fieldEntity,FieldDTO.class);
    }
    public LogEntity toLogEntity(LogsDTO logDTO){
        return modelMapper.map(logDTO,LogEntity.class);
    }
    public LogsDTO toLogsDTO(LogEntity logEntity){
        return modelMapper.map(logEntity,LogsDTO.class);
    }
    public StaffEntity toStaffEntity(StaffDTO staffDTO){
        return modelMapper.map(staffDTO, StaffEntity.class);
    }
    public StaffDTO toStaffDTO(StaffEntity staffEntity){
        return modelMapper.map(staffEntity,StaffDTO.class);
    }
    public UserEntity toUserEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, UserEntity.class);
    }
    public UserDTO toUserDTO(UserEntity userEntity){
        return modelMapper.map(userEntity,UserDTO.class);
    }
    public VehicleEntity toVehicleEntity(VehicleDTO vehicleDTO){
        return modelMapper.map(vehicleDTO,VehicleEntity.class);
    }
    public VehicleDTO toVehicleDTO(VehicleEntity vehicleEntity){
        return modelMapper.map(vehicleEntity,VehicleDTO.class);
    }



}
