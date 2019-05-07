/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author vieta
 */
public class Label extends SimpleTagSupport {

    String foreColor;
    String text;

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (foreColor != null) {
            out.write(String.format("<span style='color:%s'>%s</span>", foreColor, text));
        } else {
            out.write(String.format("<span>%s</span>", text));
        }
    }

    public void setTheColor(String color) {
        this.foreColor = color;
    }

    public void setWords(String text) {
        this.text = text;
    }

}
