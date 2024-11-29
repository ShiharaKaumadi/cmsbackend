package lk.greenshadow.greens.controller;

import lk.greenshadow.greens.repo.CropRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CropController {
    @Autowired
    CropRepo cropRepo;
}
