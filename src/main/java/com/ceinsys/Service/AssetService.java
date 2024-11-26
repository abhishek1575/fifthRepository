package com.ceinsys.Service;

import com.ceinsys.dto.AssetDto;
import com.ceinsys.model.Asset;
import com.ceinsys.repo.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public List<AssetDto> getAllAsset(){
        List<Asset> assetList = assetRepository.getAllAsset();
        List<AssetDto> assetDto1 = new ArrayList<>();


        for(Asset asset: assetList){
            AssetDto assetDto = new AssetDto();
            assetDto.setId(asset.getId());
            assetDto.setName(asset.getName());
            assetDto.setDescription(asset.getDescription());
            assetDto.setCategory(asset.getCategory());
            assetDto.setCategory(asset.getSubCategory());
            assetDto.setManufacturer(asset.getManufacturer());
            assetDto.setLocation(asset.getLocation());
            assetDto.setQuantity(asset.getQuantity());
            assetDto.setMpn(asset.getMpn());
            assetDto.setMpn(asset.getMpn());
            assetDto.setSap_no(asset.getSap_no());
            assetDto.setAsset_package(asset.getAsset_package());

            assetDto1.add(assetDto);
        }
        return assetDto1;
    }
    public List<AssetDto> getAllComponent(){
        List<Asset> component_list = assetRepository.getAllComponent();
        List<AssetDto> assetDtoList = new ArrayList<>();

        for(Asset asset: component_list){
            AssetDto assetDto = new AssetDto();
            assetDto.setId(asset.getId());
            assetDto.setName(asset.getName());
            assetDto.setDescription(asset.getDescription());
            assetDto.setCategory(asset.getCategory());
            assetDto.setCategory(asset.getSubCategory());
            assetDto.setManufacturer(asset.getManufacturer());
            assetDto.setLocation(asset.getLocation());
            assetDto.setQuantity(asset.getQuantity());
            assetDto.setMpn(asset.getMpn());
            assetDto.setMpn(asset.getMpn());
            assetDto.setSap_no(asset.getSap_no());
            assetDto.setAsset_package(asset.getAsset_package());

            assetDtoList.add(assetDto);
        }
        return  assetDtoList;
    }
    public List<AssetDto> getAll(){
        List<Asset> list = assetRepository.getAll();
        List<AssetDto> assetDtoList = new ArrayList<>();

        for(Asset asset:list){
            AssetDto assetDto = new AssetDto();
            assetDto.setId(asset.getId());
            assetDto.setName(asset.getName());
            assetDto.setDescription(asset.getDescription());
            assetDto.setCategory(asset.getCategory());
            assetDto.setCategory(asset.getSubCategory());
            assetDto.setManufacturer(asset.getManufacturer());
            assetDto.setLocation(asset.getLocation());
            assetDto.setQuantity(asset.getQuantity());
            assetDto.setMpn(asset.getMpn());
            assetDto.setMpn(asset.getMpn());
            assetDto.setSap_no(asset.getSap_no());
            assetDto.setAsset_package(asset.getAsset_package());

            assetDtoList.add(assetDto);
        }
        return assetDtoList;
    }

   public String findByName(Asset asset){
        Optional<Asset> optionalAsset = assetRepository
                .findByName(asset.getName(),asset.getCategory(),asset.getValue());
            if(optionalAsset.isPresent()){
                return "Asset is already present";
            }else{
                assetRepository.save(asset);
                return "asset saved successfully";
            }

    }

    public String deleteAsset(Asset asset){
        Optional<Asset> optionalAsset = assetRepository
                .findByName(asset.getName(),asset.getCategory(),asset.getValue());
        if(optionalAsset.isPresent()){
            assetRepository.delete(asset);
            return "User deleted successfully";

        }else{
            return "asset does not exist";
        }
    }


}
