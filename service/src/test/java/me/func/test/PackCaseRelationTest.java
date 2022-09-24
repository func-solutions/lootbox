package me.func.test;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import me.func.ebisu.Application;
import me.func.ebisu.repository.BoxRepository;
import me.func.ebisu.repository.PackCaseRelationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("me.func.test")
@Slf4j
@SpringBootTest(classes = {Application.class, MockedMicroService.class, PackCaseRelationRepository.class, BoxRepository.class})
public class PackCaseRelationTest {

	@Autowired
	private BoxRepository boxRepository;
	@Autowired
	private PackCaseRelationRepository packCaseRelationRepository;

	@Test
	public void testRandomRelation() {

		val box = boxRepository.findById(1L).orElseThrow();
		val drop = packCaseRelationRepository.randomRalation(box).orElseThrow();

		Assertions.assertDoesNotThrow(drop::getPack);
		Assertions.assertDoesNotThrow(drop::getBox);
		Assertions.assertDoesNotThrow(drop::getAmount);
	}

}
