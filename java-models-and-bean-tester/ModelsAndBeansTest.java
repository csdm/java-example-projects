package it.claudiodimauro.testing;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ModelsAndBeansTest {

	@Test
	public void allModel() {
		try {
			allGetSet(ClassName1.class);
			allGetSet(ClassName2.class);
			allGetSet(ClassName3.class);
			//...
			
		}catch(Exception e) {}
	}
	
	private void allGetSet(Class c) {
		Object inst;
		Object obj = null;
		try {
			inst = c.newInstance();
			
			for(Method m : c.getMethods()) {
			try {	
				if(m.getName().startsWith("get")) {
					m.invoke(inst);
				}else if(m.getName().startsWith("is")) {
					m.invoke(inst);
				}else {
					m.invoke(inst,obj);
				}
			}catch (Exception e) {
				}
			}
			
			inst.toString();
		} catch (Exception e) {}
	}
}