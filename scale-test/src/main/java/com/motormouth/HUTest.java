/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motormouth;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import java.io.IOException;

/**
 *
 * @author daren
 */
public class HUTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        HUTest test = new HUTest();
        test.doTest();
    }

    private static String pageUrl = "http://localhost:8088/item-directory/";
    public void doTest() throws IOException {
        
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
        WebClientOptions options = webClient.getOptions();
	  	options.setCssEnabled(true);
        CookieManager cm = new CookieManager();
        cm.setCookiesEnabled(true);
        cm.clearCookies();
        webClient.setCookieManager(cm);
        JavaScriptEngine jse = new JavaScriptEngine(webClient);
        webClient.setJavaScriptEngine(jse);
        webClient.setJavaScriptTimeout(0);
	  	options.setJavaScriptEnabled(true);
        options.setThrowExceptionOnScriptError(true);
        options.setThrowExceptionOnFailingStatusCode(true);
	  	options.setPopupBlockerEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		HtmlPage page = (HtmlPage)webClient.getPage(pageUrl);
        webClient.waitForBackgroundJavaScript(5000);
        HtmlAnchor link = page.getElementById("button", true);
        HtmlPage ajaxPage = link.click();
        webClient.waitForBackgroundJavaScript(5000);
        System.out.println(ajaxPage.asText());
    
    }
}
