package za.co.fnb.service;

import java.math.BigDecimal;
import java.util.Map;

public interface IDispenserService {
	public Map<String, Integer> getOptimalChange(BigDecimal amount);
}
