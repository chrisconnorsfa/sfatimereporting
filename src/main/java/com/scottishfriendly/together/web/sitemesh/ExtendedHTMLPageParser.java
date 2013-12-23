package com.scottishfriendly.together.web.sitemesh;

import com.opensymphony.module.sitemesh.html.State;
import com.opensymphony.module.sitemesh.html.rules.PageBuilder;
import com.opensymphony.module.sitemesh.html.util.CharArray;
import com.opensymphony.module.sitemesh.parser.HTMLPageParser;

public class ExtendedHTMLPageParser extends HTMLPageParser {

	@Override
	protected void addUserDefinedRules(State html, PageBuilder page) {		
		super.addUserDefinedRules(html, page);		
		CharArray script = new CharArray(64);		
		html.addRule(new JavascriptBlockExtractingRule(page, script));
	}
}
