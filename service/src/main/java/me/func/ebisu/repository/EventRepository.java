package me.func.ebisu.repository;

import me.func.ebisu.entity.BoxEntity;
import me.func.ebisu.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

	Set<EventEntity> getByBoxAndPlayerUuid(BoxEntity boxEntity, UUID player);

}
