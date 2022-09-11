package me.func.ebisu.repository;

import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.PackCaseRelationEntity;
import me.func.ebisu.entity.PackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackCaseRelationRepository extends JpaRepository<PackCaseRelationEntity, Long> {

	Optional<PackCaseRelationEntity> findByBoxAndPack(BoxEntity box, PackEntity pack);

}
