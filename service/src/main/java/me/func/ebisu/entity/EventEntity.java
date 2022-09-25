package me.func.ebisu.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "event")
public class EventEntity {
	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private Long id;

	@Basic
	private Timestamp timestamp;

	@OneToOne(targetEntity = PackEntity.class)
	private PackEntity pack;

	@OneToOne(targetEntity = BoxEntity.class)
	private BoxEntity box;

	private UUID playerUuid;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		EventEntity that = (EventEntity) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	public EventEntity(Timestamp timestamp, PackEntity pack, BoxEntity box, UUID playerUuid) {
		this.timestamp = timestamp;
		this.pack = pack;
		this.box = box;
		this.playerUuid = playerUuid;
	}
}
