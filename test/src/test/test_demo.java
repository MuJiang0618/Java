package test;
import org.junit.Test;
import junit.framework.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test_demo {
	@Test
	public void demo() {
		// ±àĞ´²âÊÔ´úÂë
		Scanner s = new Scanner(System.in);
		while(s.hasNext()) {
			String a = s.nextLine();
			if(a.equals("quit")) {
				System.out.println("½áÊø");
				break;
			}
			System.out.println("¶ÁÈ¡µÄ×Ö·û´®ÊÇ£º"+a);
		}
		
		Assert.assertEquals(0, 0);
		return;
	}
}