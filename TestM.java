import org.junit.*;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class TestM {

    	/* add your test code here */
	private M model;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	@Before public void runBeforeEachTest(){
		model = new M();
		System.setOut(new PrintStream(outContent));	 
	}
	@After public void runAfterEachTest(){
		model = null;
		System.setOut(null);
	}
	@Test public void testm_node1(){
		String s_empty = new String("");
		model.m(s_empty,0);
		assertEquals("zero",outContent.toString());
	}
	@Test public void testm_node2(){
		String s_1 = new String("a");
		model.m(s_1,0);
		assertEquals("a",outContent.toString());
	}
	@Test public void testm_node3(){
		String s_2 = new String("aa");
		model.m(s_2,0);
		assertEquals("b",outContent.toString());	
	}
	@Test public void testm_edge1(){
		String s_3 = new String("");
		model.m(s_3,0);
		assertEquals("zero",outContent.toString());
	}
	@Test public void testm_edge2(){
		String s_4 = new String("a");
		model.m(s_4,1);
		assertEquals("a",outContent.toString());
	}
	@Test public void testm_edge3(){
		String s_6 = new String("aa");
		model.m(s_6,1);
		assertEquals("b",outContent.toString());
	}
	@Test public void testm_edge4(){
		String s_6 = new String("aaa");
		model.m(s_6,1);
		assertEquals("b",outContent.toString());
	}
				
    	
}

class M {
	public static void main(String [] argv){
		M obj = new M();
		if (argv.length > 0)
			obj.m(argv[0], argv.length);
	}
	
	public void m(String arg, int i) {
		int q = 1;
		A o = null;
		Impossible nothing = new Impossible();
		if (i == 0)
			q = 4;
		q++;
		switch (arg.length()) {
			case 0: q /= 2; break;
			case 1: o = new A(); new B(); q = 25; break;
			case 2: o = new A(); q = q * 100;
			default: o = new B(); break; 
		}
		if (arg.length() > 0) {
			o.m();
		} else {
			System.out.println("zero");
		}
		nothing.happened();
	}
}

class A {
	public void m() { 
		System.out.println("a");
	}
}

class B extends A {
	public void m() { 
		System.out.println("b");
	}
}

class Impossible{
	public void happened() {
		// "2b||!2b?", whatever the answer nothing happens here
	}
}
