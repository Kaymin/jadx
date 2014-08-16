package jadx.tests.internal.trycatch;

import jadx.api.InternalJadxTest;
import jadx.core.dex.nodes.ClassNode;

import java.io.File;

import org.junit.Test;

import static jadx.tests.utils.JadxMatchers.containsOne;
import static org.junit.Assert.assertThat;

public class TestInlineInCatch extends InternalJadxTest {

	public static class TestCls {
		private File dir;

		public int test() {
			File output = null;
			try {
				output = File.createTempFile("f", "a", dir);
				return 0;
			} catch (Exception e) {
				if (output != null) {
					output.delete();
				}
				return 2;
			}
		}
	}

	@Test
	public void test() {
		ClassNode cls = getClassNode(TestCls.class);
		String code = cls.getCode().toString();
		System.out.println(code);

		assertThat(code, containsOne("File output = null;"));
		assertThat(code, containsOne("output = File.createTempFile(\"f\", \"a\", "));
		assertThat(code, containsOne("return 0;"));
		assertThat(code, containsOne("} catch (Exception e) {"));
		assertThat(code, containsOne("if (output != null) {"));
		assertThat(code, containsOne("output.delete();"));
		assertThat(code, containsOne("return 2;"));
	}
}