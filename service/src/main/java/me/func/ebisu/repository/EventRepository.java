package me.func.ebisu.repository;

import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.EventEntity;
import me.func.ebisu.entity.PackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

	Set<EventEntity> getByBoxAndPlayerUuid(BoxEntity boxEntity, UUID player);

}
