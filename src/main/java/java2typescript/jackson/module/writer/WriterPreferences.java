package java2typescript.jackson.module.writer;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import java2typescript.jackson.module.grammar.base.AbstractType;

public class WriterPreferences {
	private String indentationStep = "    ";
	private int indentationLevel = 0;

	private List<CustomAbstractTypeWriter> customWriters = new ArrayList<CustomAbstractTypeWriter>();
	private boolean useEnumPattern;
	private boolean enumAsStringLiteralType = false;
	private boolean constantsForStringLiteralTypeEnums = false;
	/** sort types and vars in output */
	private boolean sort;
	private List<Class<?>> providedTypes = new ArrayList<>();


	public boolean isStringLiteralTypeForEnums() {
		return enumAsStringLiteralType;
	}

	public void useStringLiteralTypeForEnums(boolean withConstants) {
		addWriter(new EnumTypeToStringLiteralTypeWriter());
		this.enumAsStringLiteralType = true;
		this.constantsForStringLiteralTypeEnums = withConstants;
	}

	public void useStringLiteralTypeForEnums() {
		useStringLiteralTypeForEnums(false);
	}

	public boolean isConstantsForStringLiteralTypeEnums() {
		return this.constantsForStringLiteralTypeEnums;
	}
	
	public void useEnumPattern() {
		addWriter(new EnumTypeToEnumPatternWriter());
		useEnumPattern = true;
	}

	public boolean isUseEnumPattern() {
		return useEnumPattern;
	}
	
	public void sort() {
		sort = true;
	}
	
	public boolean isSort() {
		return sort;
	}

	public void addWriter(CustomAbstractTypeWriter writer) {
		this.customWriters.add(writer);
	}

	public List<CustomAbstractTypeWriter> getCustomWriters() {
		return customWriters;
	}

	public boolean hasCustomWriter(AbstractType type) {
		return getCustomWriter(type) != null;
	}

	public void writeDef(AbstractType type, Writer writer) throws IOException {
		getCustomWriter(type).writeDef(type, writer, this);
	}

	public CustomAbstractTypeWriter getCustomWriter(AbstractType type) {
		for (CustomAbstractTypeWriter writer : customWriters) {
			if (writer.accepts(type, this)) {
				return writer;
			}
		}
		return null;
	}

	/**
	 * Can be used to exclude given types from generated output
	 */
	public void setProvidedTypes(List<Class<?>> providedTypes) {
		this.providedTypes = providedTypes;
	}

	public List<Class<?>> getProvidedTypes() {
		return providedTypes;
	}

	public String getIndentation() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i++ < indentationLevel) {
			sb.append(indentationStep);
		}
		return sb.toString();
	}

	public void setIndentationStep(String indentation) {
		this.indentationStep = indentation;
	}

	public void increaseIndentation() {
		indentationLevel++;
	}

	public void decreaseIndention() {
		indentationLevel--;
	}

}
