package com.scottishfriendly.together.web.sitemesh;

import com.opensymphony.module.sitemesh.html.BlockExtractingRule;
import com.opensymphony.module.sitemesh.html.Tag;
import com.opensymphony.module.sitemesh.html.rules.PageBuilder;
import com.opensymphony.module.sitemesh.html.util.CharArray;

public class JavascriptBlockExtractingRule extends BlockExtractingRule {
    public static final String SCRIPTS_PROPERTY = "scripts";	
	private static final String CLASS_ATTR = "class";
	private static final String SCRIPTS_CLASS = "scripts";
	private CharArray script;
	private boolean scriptsSectionSeen = false;
	private PageBuilder page;

	public JavascriptBlockExtractingRule(PageBuilder page, CharArray script) {
		super(false, "section");
		this.page = page;
		this.script = script;
	}

	@Override
	protected CharArray createBuffer() {
		return script;
	}

	@Override
	public void process(Tag tag) {
		if (tag.getType() == Tag.OPEN && isScriptsSection(tag)) {
			super.process(tag);
			scriptsSectionSeen = true;
		} else if (tag.getType() == Tag.CLOSE && scriptsSectionSeen) {
			super.process(tag);
			page.addProperty(SCRIPTS_PROPERTY, script.toString());
			scriptsSectionSeen = false;
		}
	}
	
	private boolean isScriptsSection(Tag tag) {
		return SCRIPTS_CLASS.equals(tag.getAttributeValue(CLASS_ATTR, false));
	}
}
