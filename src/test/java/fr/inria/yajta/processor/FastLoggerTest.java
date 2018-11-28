package fr.inria.yajta.processor;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FastLoggerTest {

	@Test
	public void testLog() {
		FastLogger logger = FastLogger.getInstance();
		logger.setLogFile(new File("tmp.json"));


		logger.stepIn(0, logger.register("cl", "m"));
		logger.stepIn(0, logger.register("cl", "m11"));
		logger.stepIn(0, logger.register("cl", "m21"));
		logger.stepOut(0);
		logger.stepOut(0);

		logger.stepIn(1, logger.register("cl", "m"));
		logger.stepIn(1, logger.register("cl", "m11"));
		logger.stepIn(1, logger.register("cl", "m21"));
		logger.stepOut(1);
		logger.stepOut(1);


		logger.stepIn(1, logger.register("cl", "m21"));
		logger.stepIn(1, logger.register("cl", "m22"));
		logger.stepOut(1);
		logger.stepOut(1);
		logger.stepOut(1);

		logger.stepIn(0, logger.register("cl", "m21"));
		logger.stepIn(0, logger.register("cl", "m22"));
		logger.stepOut(0);
		logger.stepOut(0);
		logger.stepOut(0);
		logger.flush();
		System.out.println("Done");
	}

}