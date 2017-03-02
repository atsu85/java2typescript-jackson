/*******************************************************************************
 * Copyright 2014 Florian Benz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package java2typescript.jackson.module;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java2typescript.jackson.module.grammar.Module;
import java2typescript.jackson.module.util.ExpectedOutputChecker;
import java2typescript.jackson.module.writer.InternalModuleFormatWriter;
import org.junit.Test;

public class StaticFieldExporterTest {
	@JsonTypeName("ChangedEnumName")
	enum Enum {
		VAL1, VAL2, VAL3
	}

	static class TestClass {
		public static final boolean MY_CONSTANT_BOOLEAN = true;

		public static final String MY_CONSTANT_STRING = "Test";

		public static final int MY_CONSTANT_INT = 10;

		public static final double MY_CONSTANT_DOUBLE = 42.12;

		public static final Enum MY_CONSTANT_ENUM = Enum.VAL1;

		public static final Enum[] MY_CONSTANT_ENUM_ARRAY = new Enum[] { Enum.VAL1 };

		public static final Enum[] MY_CONSTANT_ENUM_ARRAY_2 = new Enum[] { Enum.VAL1, Enum.VAL2 };

		public static final String[] MY_CONSTANT_ARRAY = new String[] { "Test" };

		public static final int[] MY_CONSTANT_INT_ARRAY = new int[] { 10, 12 };

		public static final double[] MY_CONSTANT_DOUBLE_ARRAY = new double[] { 42.12 };

		public static final boolean[] MY_CONSTANT_BOOLEAN_ARRAY = new boolean[] { true, false, true };

		public String doNotExportAsStatic;
	}

	@Test
	public void testExportingConstants() throws IOException, IllegalArgumentException {
		ArrayList<Class<?>> classesToConvert = new ArrayList<Class<?>>();
		classesToConvert.add(TestClass.class);

		Module module = new Module("mod");
		new StaticFieldExporter(module, null).export(classesToConvert);

		ExpectedOutputChecker.writeAndCheckOutputFromFile(module, new InternalModuleFormatWriter());
	}
}
