package com.ceinsys.controller;

import com.ceinsys.Service.AssetService;
import com.ceinsys.model.Asset;
import com.ceinsys.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/asset")
public class AssetController {

   @Autowired
    private AssetRepository assetRepository;


}
