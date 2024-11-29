package lk.greenshadow.greens.service.impl;

import lk.greenshadow.greens.dto.impl.LogsDTO;
import lk.greenshadow.greens.entity.CropEntity;
import lk.greenshadow.greens.entity.FieldEntity;
import lk.greenshadow.greens.entity.LogEntity;
import lk.greenshadow.greens.entity.StaffEntity;
import lk.greenshadow.greens.repo.CropRepo;
import lk.greenshadow.greens.repo.FieldRepo;
import lk.greenshadow.greens.repo.LogRepo;
import lk.greenshadow.greens.repo.StaffRepo;
import lk.greenshadow.greens.service.LogService;
import lk.greenshadow.greens.util.AppUtil;
import lk.greenshadow.greens.util.Mappings;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class LogServiceImpl implements LogService {
    private ModelMapper modelMapper;
    private CropRepo cropRepo;
    private Mappings mappings;
    private LogRepo logRepo;
    private StaffRepo staffRepo;
    private FieldRepo fieldRepo;
    @Override
    public LogsDTO save(LogsDTO dto) {
        dto.setLogId(AppUtil.generateLogId());
        LogEntity logEntity = mappings.toLogEntity(dto);
        // Retrieve and set associated staff entities
        if (dto.getStaffIds() != null && !dto.getStaffIds().isEmpty()) {
            Set<StaffEntity> staffEntities = new HashSet<>(staffRepo.findAllById(dto.getStaffIds()));
            if (staffEntities.size() != dto.getStaffIds().size()) {
                throw new IllegalArgumentException("One or more staff IDs are invalid.");
            }
            logEntity.setStaffLogs(staffEntities);
        }
        // Retrieve and set associated field entities
        if (dto.getFieldIds() != null && !dto.getFieldIds().isEmpty()) {
            Set<FieldEntity> fieldEntities = new HashSet<>(fieldRepo.findAllById(dto.getFieldIds()));
            if (fieldEntities.size() != dto.getFieldIds().size()) {
                throw new IllegalArgumentException("One or more field IDs are invalid.");
            }
            logEntity.setFieldLogs(fieldEntities);
        }
        // Retrieve and set associated crop entities
        if (dto.getCropIds() != null && !dto.getCropIds().isEmpty()) {
            Set<CropEntity> cropEntities = new HashSet<>(cropRepo.findAllById(dto.getCropIds()));
            if (cropEntities.size() != dto.getCropIds().size()) {
                throw new IllegalArgumentException("One or more crop IDs are invalid.");
            }
            logEntity.setCropLogs(cropEntities);
        }
        // Save the log entity and map back to DTO
        LogEntity savedLog = logRepo.save(logEntity);
        return mappings.toLogsDTO(savedLog);
    }

    @Override
    public LogsDTO update(String id, LogsDTO dto) {
        LogEntity existingLog = logRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Log not found with ID: " + id));

        // Update basic fields
        existingLog.setLogDetails(dto.getLogDetails());
        existingLog.setDate((Date) dto.getDate());

        // Handle image update if provided
        if (dto.getImage2() != null) {
            existingLog.setImage2(dto.getImage2());
        }

        // Update associated staff
        if (dto.getStaffIds() != null && !dto.getStaffIds().isEmpty()) {
            List<StaffEntity> staffEntities = staffRepo.findAllById(dto.getStaffIds());
            if (staffEntities.size() != dto.getStaffIds().size()) {
                throw new IllegalArgumentException("One or more staff IDs are invalid.");
            }
            existingLog.setStaffLogs(new HashSet<>(staffEntities));
        }

        // Update associated fields
        if (dto.getFieldIds() != null && !dto.getFieldIds().isEmpty()) {
            List<FieldEntity> fieldEntities = fieldRepo.findAllById(dto.getFieldIds());
            if (fieldEntities.size() != dto.getFieldIds().size()) {
                throw new IllegalArgumentException("One or more field IDs are invalid.");
            }
            existingLog.setFieldLogs(new HashSet<>(fieldEntities));
        }

        // Update associated crops
        if (dto.getCropIds() != null && !dto.getCropIds().isEmpty()) {
            List<CropEntity> cropEntities = cropRepo.findAllById(dto.getCropIds());
            if (cropEntities.size() != dto.getCropIds().size()) {
                throw new IllegalArgumentException("One or more crop IDs are invalid.");
            }
            existingLog.setCropLogs(new HashSet<>(cropEntities));
        }

        // Save the updated log entity and return the updated DTO
        return mappings.toLogsDTO(logRepo.save(existingLog));
    }

    @Override
    public void delete(String id) {
        fieldRepo.deleteById(id);
    }

    @Override
    public LogsDTO findById(String id) {
        Optional<LogEntity> byId = logRepo.findById(id);
        if (byId.isPresent()){
            return mappings.toLogsDTO(byId.get());
        }
        return null;
    }

    @Override
    public List<LogsDTO> findAll() {
        return mappings.asLogDTOList(logRepo.findAll());
    }
}
