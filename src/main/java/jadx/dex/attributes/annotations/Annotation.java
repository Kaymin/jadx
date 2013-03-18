package jadx.dex.attributes.annotations;

import jadx.dex.instructions.args.ArgType;

import java.util.Map;

public class Annotation {

	public static enum Visibility {
		BUILD, RUNTIME, SYSTEM
	}

	private final Visibility visibility;
	private final ArgType atype;
	private final Map<String, Object> values;

	public Annotation(Visibility visibility, ArgType type, Map<String, Object> values) {
		this.visibility = visibility;
		this.atype = type;
		this.values = values;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public ArgType getType() {
		return atype;
	}

	public String getAnnotationClass() {
		return atype.getObject();
	}

	public Map<String, Object> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "Annotation[" + visibility + ", " + atype + ", " + values + "]";
	}

}
