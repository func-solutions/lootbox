package me.func.test;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.Application;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.PackCaseRelationRepository;
import me.func.ebisu.repository.PackRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Set;

@ComponentScan("me.func.test")
@Slf4j
@SpringBootTest(classes = {Application.class, MockedMicroService.class, PackCaseRelationRepository.class, BoxRepository.class})
public class PackCaseRelationTest {

	@Autowired
	private BoxRepository boxRepository;
	@Autowired
	private PackRepository packRepository;
	@Autowired
	private PackCaseRelationRepository packCaseRelationRepository;

	@Test
	public void testRandomRelation() {

		val packFirst = packRepository.findById(1L).orElseThrow();
		val packSecond = packRepository.findById(2L).orElseThrow();
		val box = boxRepository.findById(1L).orElseThrow();

		val hasDrop = packCaseRelationRepository.findRandomRelation(box, Set.of(packSecond)).orElseThrow();
		val notNullDrop = packCaseRelationRepository.findRandomRelation(box, Set.of()).orElse(null);
		val nullDrop = packCaseRelationRepository.findRandomRelation(box, Set.of(packFirst)).orElse(null);

		Assertions.assertNotNull(notNullDrop);
		Assertions.assertNotNull(hasDrop);
		Assertions.assertNull(nullDrop);
	}

}
