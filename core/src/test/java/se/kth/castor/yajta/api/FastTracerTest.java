package se.kth.castor.yajta.api;

import com.google.common.collect.BiMap;
import se.kth.castor.offline.InstrumentationBuilder;
import se.kth.castor.yajta.api.loggerimplem.TestFastLogger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FastTracerTest {

	@Test
	public void testProbesInsertion() throws MalformedTrackingClassException {

		TestFastLogger.traceBranch = false;
		List<TestFastLogger.Log> logs = new ArrayList<>();
		InstrumentationBuilder builder = TestLoggerUtils.instrumentAndRun(
				"classes-fast",
				"fr.inria.helloworldf.App",
				"main",
				logs);

		//Every method is indeed logged (in and out)
		assertEquals(38, logs.size());
		//Every method logged in is also logged out
		assertEquals(logs.stream().filter(l -> l.type == TestFastLogger.LOGTYPE.IN).count(),
				logs.stream().filter(l -> l.type == TestFastLogger.LOGTYPE.OUT).count()
		);

		BiMap<Integer, String> dico = TestFastLogger.getInstance().getDictionary().inverse();

		//First method logged is "main", "se.kth.castor.helloworld.App", "main(java.lang.String[])"
		//assertEquals("fr.inria.helloworldf.App.main(java.lang.String[])", logs.get(0).getElementName(dico));
		assertEquals("fr.inria.helloworldf.App.main([Ljava/lang/String;)V", logs.get(0).getElementName(dico));
		builder.close();
	}



	@Test
	public void testBranchProbesInsertion() throws MalformedTrackingClassException {

		TestFastLogger.traceBranch = true;
		List<TestFastLogger.Log> logs = new ArrayList<>();
		InstrumentationBuilder builder = TestLoggerUtils.instrumentAndRun(
				"classes-fast-branch",
				"fr.inria.hellobranchf.AppBranch",
				"main",
				logs);

		BiMap<Integer, String> dico = TestFastLogger.getInstance().getDictionary().inverse();


		//contract: Every method and each branch is indeed logged (in and out)
		//assertTrue(logs.size() == 97);
		assertTrue(logs.size() == 160);

		//contract: Every method logged in is also logged out
		assertEquals(
				logs.stream().filter(l -> l.type == TestFastLogger.LOGTYPE.IN).count(),
				logs.stream().filter(l -> l.type == TestFastLogger.LOGTYPE.OUT).count()
		);

		//contract: every branch entered is logged
		assertTrue(logs.stream().filter(l -> l.type == TestFastLogger.LOGTYPE.IN && l.isBranch(dico)).count() == 63);

		builder.close();
	}
}
