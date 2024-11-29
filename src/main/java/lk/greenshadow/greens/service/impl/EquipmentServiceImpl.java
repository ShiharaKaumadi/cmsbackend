package lk.greenshadow.greens.service.impl;

import jakarta.transaction.Transactional;
import lk.greenshadow.greens.dto.impl.EquipmentDTO;
import lk.greenshadow.greens.entity.EquipmentEntity;
import lk.greenshadow.greens.entity.FieldEntity;
import lk.greenshadow.greens.entity.StaffEntity;
import lk.greenshadow.greens.repo.EquipmentRepo;
import lk.greenshadow.greens.repo.FieldRepo;
import lk.greenshadow.greens.repo.StaffRepo;
import lk.greenshadow.greens.service.EquipmentService;
import lk.greenshadow.greens.util.AppUtil;
import lk.greenshadow.greens.util.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
    private StaffRepo staffRepo;
    private FieldRepo fieldRepo;
    private EquipmentRepo equipmentRepo;
    private ModelMapper modelMapper;
    private Mappings mappings;
    @Override
    public EquipmentDTO save(EquipmentDTO dto) {
        dto.setEquipmentId(AppUtil.generateEquipmentId());
        return mappings.toEquipmentDTO(equipmentRepo.save(mappings.toEquipmentEntity(dto)));
    }

    @Override
    public EquipmentDTO update(String id, EquipmentDTO dto) {
        EquipmentEntity equipment = equipmentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found with ID: " + id));

        equipment.setType(dto.getType());
        equipment.setName(dto.getName());
        equipment.setStatus(dto.getStatus());

        if (dto.getFieldId() != null) {
            FieldEntity field = fieldRepo.findById(dto.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + dto.getFieldId()));
            equipment.setField(field);
        }

        if (dto.getStaffId() != null) {
            StaffEntity staff = staffRepo.findById(dto.getStaffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found with ID: " + dto.getStaffId()));
            equipment.setStaff(staff);
        }

        return mappings.toEquipmentDTO(equipmentRepo.save(equipment));
    }

    @Override
    public void delete(String id) {
        equipmentRepo.deleteById(id);

    }

    @Override
    public EquipmentDTO findById(String id) {
        Optional<EquipmentEntity> byId = equipmentRepo.findById(id);
        if (byId.isPresent()){
            return mappings.toEquipmentDTO(byId.get());
        }
        return null;
    }

    @Override
    public List<EquipmentDTO> findAll() {
        return mappings.asEquipmentDTOList(equipmentRepo.findAll());
    }
}
