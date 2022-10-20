package me.func.ebisu.repository;

import me.func.ebisu.entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<BoxEntity, Long> {

}
