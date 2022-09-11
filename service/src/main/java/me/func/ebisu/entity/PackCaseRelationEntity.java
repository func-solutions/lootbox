package me.func.ebisu.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "pack_case_relation")
public class PackCaseRelationEntity {

	@OneToOne(targetEntity = PackEntity.class)
	private PackEntity pack;

	@OneToOne(targetEntity = BoxEntity.class)
	private BoxEntity box;

	private Long amount;

	public PackCaseRelationEntity(PackEntity pack, BoxEntity box, Long amount) {
		this.pack = pack;
		this.box = box;
		this.amount = amount;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		PackCaseRelationEntity that = (PackCaseRelationEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
