package me.func.ebisu.entity;

import lombok.*;
import me.func.ebisu.model.Rarity;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "pack")
public class PackEntity {
	@Id
	@GeneratedValue
	@Column(name = "pack_id")
	private Long id;
	private Rarity rare;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(targetEntity = RewardEntity.class)
	@ToString.Exclude
	private Set<RewardEntity> rewards;

	private Boolean unique;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		PackEntity that = (PackEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
