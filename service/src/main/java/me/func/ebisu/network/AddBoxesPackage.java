package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.BoxEntity;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddBoxesPackage extends OutPackage {

	private final List<BoxEntity> boxes;

}
