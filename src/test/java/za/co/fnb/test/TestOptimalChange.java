package za.co.fnb.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import za.co.fnb.model.OptimalChange;
import za.co.fnb.service.IDispenserService;
import za.co.fnb.service.impl.DispenserServiceImpl;

public class TestOptimalChange extends TestCase {

	IDispenserService dispenserService;

	protected void setUp() throws Exception {
		dispenserService = new DispenserServiceImpl();
	}

	public void testChange() {
		Map<String, Integer> map = dispenserService.getOptimalChange(new BigDecimal("24.50"));
		List<OptimalChange> changeList = new ArrayList<OptimalChange>();
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			OptimalChange optimalChange = new OptimalChange();
			optimalChange.setCount(entry.getValue());
			optimalChange.setDenominator(entry.getKey());
			changeList.add(optimalChange);
		}
		
		assertTrue(changeList.size() == 3);
	}

	protected void tearDown() throws Exception {
		dispenserService = null;
	}

}
