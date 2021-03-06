/*******************************************************************************
 * Copyright 2013 Raphael Jolivet
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
package java2typescript.jackson.module.grammar;

import java.io.IOException;
import java.io.Writer;
import java2typescript.jackson.module.grammar.base.AbstractType;


public class MapType extends AbstractType {
	private AbstractType valueType;
	private AbstractType keyType;

	public MapType() {
	}

	@Override
	public void write(Writer writer) throws IOException {
		// TODO: only use this "key in <ENUM>" syntax when useStringLiteralTypeForEnums is enabled.
		if (keyType instanceof EnumType) {
			writer.write("{ [key in ");
			keyType.write(writer);
			writer.write(" ] ? : ");
		} else {
			writer.write("{ [key: ");
			keyType.write(writer);
			writer.write(" ]: ");
		}

		valueType.write(writer);
		writer.write(";}");
	}

	public AbstractType getValueType() {
		return valueType;
	}

	public void setValueType(AbstractType valueType) {
		this.valueType = valueType;
	}

	public AbstractType getKeyType() {
		return keyType;
	}

	public void setKeyType(AbstractType keyType) {
		this.keyType = keyType;
	}

}
