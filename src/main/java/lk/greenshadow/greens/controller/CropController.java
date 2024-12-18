package lk.greenshadow.greens.controller;

import lk.greenshadow.greens.dto.impl.CropDTO;
import lk.greenshadow.greens.entity.CropEntity;
import lk.greenshadow.greens.repo.CropRepo;
import lk.greenshadow.greens.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CropController {
    @Autowired
    CropRepo cropRepo;
    public ResponseUtil saveCrop(@ModelAttribute CropDTO cropDTO){
        return new ResponseUtil();
    }
}
