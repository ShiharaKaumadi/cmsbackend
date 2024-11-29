package lk.greenshadow.greens.service.impl;

import lk.greenshadow.greens.dto.impl.CropDTO;
import lk.greenshadow.greens.entity.CropEntity;
import lk.greenshadow.greens.entity.FieldEntity;
import lk.greenshadow.greens.repo.CropRepo;
import lk.greenshadow.greens.repo.FieldRepo;
import lk.greenshadow.greens.service.CropService;
import lk.greenshadow.greens.util.AppUtil;
import lk.greenshadow.greens.util.Mappings;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class CropServiceImpl implements CropService {
    private CropRepo cropRepo;
    private FieldRepo fieldRepo;
    private ModelMapper modelMapper;
    private Mappings cropMapping;
    @Override
    public CropDTO save(CropDTO dto) {
        dto.setId(AppUtil.generateCropId());
        return cropMapping.toCropDTO(cropRepo.save(cropMapping.toCropEntity(dto)))
        return null;
    }

    @Override
    public CropDTO update(String id, CropDTO dto) {
        CropEntity existingCrop = cropRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Crop not found with ID: " + id));

        // Update basic crop properties
        existingCrop.setCommonName(dto.getCommonName());
        existingCrop.setSpecificName(dto.getSpecificName());
        existingCrop.setCategory(dto.getCategory());
        existingCrop.setSeason(dto.getSeason());

        // Set the field if provided in the DTO
        if (dto.getFieldId() != null) {
            FieldEntity field = fieldRepo.findById(dto.getFieldId())
                    .orElseThrow(() -> new IllegalArgumentException("Field not found with ID: " + dto.getFieldId()));
            existingCrop.setField(field);
        }

        // Handle images if provided
        if (dto.getImage1() != null) {
            existingCrop.setImage1(dto.getImage1());
        }

        // Save the updated crop entity
        return cropMapping.toCropDTO(cropRepo.save(existingCrop));
    }

    @Override
    public void delete(String id) {
        cropRepo.deleteById(id);

    }

    @Override
    public CropDTO findById(String id) {
        Optional<CropEntity> byId = cropRepo.findById(id);
        if (byId.isPresent()){
            return cropMapping.toCropDTO(byId.get());
        }
        return null;
    }

    @Override
    public List<CropDTO> findAll() {
        return null;
    }
}
