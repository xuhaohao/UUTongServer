package com.sincsmart.uutong.utils;

import org.junit.Test;
import com.sincsmart.uutong.utils.PsdUtil;
public class PsdUtilTest {

	@Test
	public void test() {
		String result = PsdUtil.getPassMD5("0");
		System.out.print(result);
	}

}
