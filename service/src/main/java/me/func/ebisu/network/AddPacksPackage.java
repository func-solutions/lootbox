package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.func.ebisu.entity.PackEntity;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class AddPacksPackage extends OutPackage {

	private final List<PackEntity> packs;

}
