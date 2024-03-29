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
@Table(name = "box")
public class BoxEntity {
	@Id
	@GeneratedValue
	@Column(name = "box_id")
	private Long id;

	@Column(nullable = false)
	private Double price;

	private String title;

	private String texture;

	private String description;

	private String currency;

	private String status;

	private String vault;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		BoxEntity boxEntity = (BoxEntity) o;
		return id != null && Objects.equals(id, boxEntity.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
