package com.ceinsys.repo;

import com.ceinsys.dto.AssetDto;
import com.ceinsys.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository {
    @Query(value="SELECT * FROM hamt WHERE category = 'asset' ", nativeQuery=true)
    List<Asset> getAllAsset();

    @Query(value="SELECT * FROM hamt WHERE category='component' ",nativeQuery=true)
    List<Asset> getAllComponent();

    @Query(value="SELECT * FROM hamt")
    List<Asset> getAll();

    void delete(Asset asset);

    Asset editAsset(Asset asset);

    Optional<Asset> findByName(String name, String category, String value);


}
