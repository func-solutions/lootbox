package me.func.ebisu.entity;

import lombok.*;
import me.func.ebisu.entity.button.ButtonMirror;
import me.func.protocol.menu.Button;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * @author func 05.09.2022
 * @project cases
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "event")
public class EventEntity implements ButtonMirror {
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

	@Override
	public Button asButton() {
		return Button.builder()
				.title("Запись номер " + id)
				.description("Игрок выбил " + pack.getRare().name())
				.texture(box.getTexture())
				.build();
	}
}
