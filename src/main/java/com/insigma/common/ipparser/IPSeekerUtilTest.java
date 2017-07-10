package com.insigma.common.ipparser;

import org.junit.Test;
import org.springframework.util.Assert;

public class IPSeekerUtilTest extends Assert {
	
	IPSeekerUtil util=new IPSeekerUtil();
	
	@SuppressWarnings("unused")
	@Test
	public void test() {
		String country=util.getIpCountry(null);
	}

}
