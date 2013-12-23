package com.scottishfriendly.together.web.sitemesh;

import java.io.IOException;
import javax.servlet.jsp.JspException;

import com.opensymphony.module.sitemesh.HTMLPage;
import com.opensymphony.module.sitemesh.taglib.AbstractTag;

@SuppressWarnings("serial")
public class ScriptsTag extends AbstractTag {

    @Override
	public final int doEndTag() throws JspException {
		HTMLPage htmlPage = (HTMLPage) getPage();
		try {
			if (htmlPage.isPropertySet(JavascriptBlockExtractingRule.SCRIPTS_PROPERTY)) {
				String scripts = htmlPage.getProperty(JavascriptBlockExtractingRule.SCRIPTS_PROPERTY);
				
                if (scripts == null) {
					scripts = "";
				}
				
                getOut().write(scripts);
			}
		} catch (IOException e) {
			throw new JspException("Error writing scripts element: "
					+ e.toString(), e);
		}
        
		return EVAL_PAGE;
	}
}
