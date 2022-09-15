package me.func.ebisu.network;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SetPackCaseRelationPackage extends OutPackage {

	private final long packId;
	private final long boxId;
	private final long count;

}
