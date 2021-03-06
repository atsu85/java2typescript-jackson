package java2typescript.jackson.module;

import java.io.IOException;

import java2typescript.jackson.module.grammar.Module;
import java2typescript.jackson.module.util.ExpectedOutputChecker;
import java2typescript.jackson.module.util.TestUtil;
import java2typescript.jackson.module.writer.ExternalModuleFormatWriter;
import org.junit.Test;

public class ClassWithoutFieldsTest {

	static class ClassWithoutFields {
	}

	@Test
	public void classWithoutFields() throws IOException {
		// Arrange
		Module module = TestUtil.createTestModule(null, ClassWithoutFields.class);

		ExpectedOutputChecker.writeAndCheckOutputFromFile(module, new ExternalModuleFormatWriter());
	}

	@Test
	public void referencesClassWithoutFields() throws IOException {
		// Arrange
		@SuppressWarnings("unused")
		class RererencesClassWithoutFields {
			public ClassWithoutFields classWithoutFields;
			public Object javaLangObject;
		}
		Module module = TestUtil.createTestModule(null, RererencesClassWithoutFields.class);

		ExpectedOutputChecker.writeAndCheckOutputFromFile(module, new ExternalModuleFormatWriter());
	}

	@Test
	public void classWithOnlyMethod() throws IOException {
		// Arrange
		class ClassWithOnlyMethod {
			@SuppressWarnings("unused")
			public void onlyMethod() {
			}
		}
		Module module = TestUtil.createTestModule(null, ClassWithOnlyMethod.class);

		ExpectedOutputChecker.writeAndCheckOutputFromFile(module, new ExternalModuleFormatWriter());
	}
}
