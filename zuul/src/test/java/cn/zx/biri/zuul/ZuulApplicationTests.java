package cn.zx.biri.zuul;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.ref.WeakReference;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZuulApplicationTests {


	@Test
	public void contextLoads() {
		String q = "JSESSION=ZWM3ZWVjZWQtNTIzOS00NzRmLWE1NjEtOGFjYzM4Y2M0N2Fm";
		String q1 = ";SESSION=ZWM3ZWVjZWQtNTIzOS00NzRmLWE1NjEtOGFjYzM4Y2M0N2Fm";
		String q2 = "SESSION=YTgyODRjOTItZGQzMy00YjExLWE3MDYtMGM1Nzc1YTA4ODY5";
		System.out.println(q.matches("SESSION=.{48}"));
		System.out.println(q.replaceAll("\\bSESSION=.{48}","15"));
		System.out.println(q1.replaceAll("\\bSESSION=.{48}","15"));
		System.out.println(q2.replaceAll("\\bSESSION=.{48}","15"));
	}



	}



