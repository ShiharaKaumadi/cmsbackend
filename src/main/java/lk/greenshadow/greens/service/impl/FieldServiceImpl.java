package lk.greenshadow.greens.service.impl;

import lk.greenshadow.greens.dto.impl.FieldDTO;
import lk.greenshadow.greens.entity.FieldEntity;
import lk.greenshadow.greens.entity.StaffEntity;
import lk.greenshadow.greens.repo.CropRepo;
import lk.greenshadow.greens.repo.FieldRepo;
import lk.greenshadow.greens.repo.StaffRepo;
import lk.greenshadow.greens.service.FieldService;
import lk.greenshadow.greens.util.AppUtil;
import lk.greenshadow.greens.util.Mappings;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class FieldServiceImpl implements FieldService {
    private CropRepo cropRepo;
    private StaffRepo staffRepo;
    private FieldRepo fieldRepo;
    private ModelMapper modelMapper;
    private Mappings fieldMapping;
    @Override
    public FieldDTO save(FieldDTO dto) {
        dto.setFieldId(AppUtil.generateFieldId());
        return fieldMapping.toFieldDTO(fieldRepo.save(fieldMapping.toFieldEntity(dto)));
    }

    @Override
    public FieldDTO update(String id, FieldDTO dto) {
        FieldEntity existingField = fieldRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + id));

        // Update basic field properties
        existingField.setName(dto.getName());
        existingField.setSize(dto.getSize());
        existingField.setLocation(dto.getLocation());
        // Update staff members if provided
        if (dto.getStaffIds() != null && !dto.getStaffIds().isEmpty()) {
            List<StaffEntity> staffEntities = staffRepo.findAllById(dto.getStaffIds());
            if (staffEntities.size() != dto.getStaffIds().size()) {
                throw new IllegalArgumentException("One or more staff IDs are invalid.");
            }
            existingField.setStaffMembers(new HashSet<>(staffEntities));
        } else {
            existingField.getStaffMembers().clear();  // Clear existing staff if no IDs are provided
        }

        // Handle images if provided
        if (dto.getImage1() != null) {
            existingField.setImage1(dto.getImage1());
        }

        if (dto.getImage2() != null) {
            existingField.setImage2(dto.getImage2());
        }

        // Save the updated field entity
        return fieldMapping.toFieldDTO(fieldRepo.save(existingField));


    }

    @Override
    public void delete(String id) {
        fieldRepo.deleteById(id);

    }

    @Override
    public FieldDTO findById(String id) {
        Optional<FieldEntity> byId = fieldRepo.findById(id);
        if (byId.isPresent()) {
            return fieldMapping.toFieldDTO(byId.get());
        }
        return null;

    }

    @Override
    public List<FieldDTO> findAll() {
       return fieldMapping.asFieldDTOList(fieldRepo.findAll());
    }
}
