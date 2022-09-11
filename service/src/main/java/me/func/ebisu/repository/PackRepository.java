package me.func.ebisu.repository;

import me.func.ebisu.entity.PackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackRepository extends JpaRepository<PackEntity, Long> {
}
