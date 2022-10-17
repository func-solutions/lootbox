package me.func.ebisu.repository;

import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.PackCaseRelationEntity;
import me.func.ebisu.entity.PackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PackCaseRelationRepository extends JpaRepository<PackCaseRelationEntity, Long> {

	Optional<PackCaseRelationEntity> findByBoxAndPack(BoxEntity box, PackEntity pack);

	@Query(value = "SELECT * FROM pack_case_relation WHERE amount > 0 and box_box_id = ?1 and (?2 is NULL or pack_pack_id not in ?2) ORDER BY random() LIMIT 1", nativeQuery = true)
	Optional<PackCaseRelationEntity> findRandomRelation(BoxEntity boxEntity, Set<PackEntity> ignore);

}
