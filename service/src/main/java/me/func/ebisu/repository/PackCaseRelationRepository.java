package me.func.ebisu.repository;

import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.PackCaseRelationEntity;
import me.func.ebisu.entity.PackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackCaseRelationRepository extends JpaRepository<PackCaseRelationEntity, Long> {

	Optional<PackCaseRelationEntity> findByBoxAndPack(BoxEntity box, PackEntity pack);

	@Query(value = "SELECT * FROM pack_case_relation " +
			"WHERE amount > 0 and box_box_id = ?1 ORDER BY random() LIMIT 1", nativeQuery = true)
	Optional<PackCaseRelationEntity> randomRalation(BoxEntity boxEntity);

}
