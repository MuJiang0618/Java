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
		// ��д���Դ���
		Scanner s = new Scanner(System.in);
		while(s.hasNext()) {
			String a = s.nextLine();
			if(a.equals("quit")) {
				System.out.println("����");
				break;
			}
			System.out.println("��ȡ���ַ����ǣ�"+a);
		}
		
		Assert.assertEquals(0, 0);
		return;
	}
}