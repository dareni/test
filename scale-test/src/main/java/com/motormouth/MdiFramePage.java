package com.motormouth;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author daren
 */
public class MdiFramePage {
    HtmlPage page_ = null;
    final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);

    
    public MdiFramePage() throws IOException, InterruptedException{
//        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "fatal");
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
    }

    public void getHomePage(String pageUrl) throws IOException {
		page_ = (HtmlPage)webClient.getPage(pageUrl);
        webClient.waitForBackgroundJavaScript(5000);
    }

    public void doLogin() throws IOException {
// HtmlUnit fails on wicket:panel tags.        
//        HtmlAnchor loginButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/*[local-name()='panel']/form/a");
        HtmlAnchor loginButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/form/a");

        HtmlTextInput username = page_.getElementByName("username");
        username.setValueAttribute("a");
        HtmlPasswordInput password = page_.getElementByName("password");
        password.setValueAttribute("a");
//        System.out.println(page_.asXml());
        page_ = (HtmlPage) loginButton.click();
        webClient.waitForBackgroundJavaScript(10000);
    }

    void checkLogin() {
//        HtmlAnchor userButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/*[local-name()='panel']/div/div/*[local-name()='panel']/a");
//      HtmlAnchor userButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/wicket:panel/div/div/wicket:panel/a");
//        System.out.println("text:"+page_.asText());
//        System.out.println("text:"+userButton.asText());
    }

    public void PrintAllNodes() {
        NodeList iter = page_.getChildNodes();

        for (int i = 0; i < iter.getLength(); i++) {
            Node node = iter.item(i);
            System.out.println("localname: " + node.getLocalName());
            System.out.println("nodename: " + node.getNodeName());
            System.out.println("nodevalue: " + node.getNodeValue());
            System.out.println("nodevalue: " + node.getTextContent());
        }
    }

    public String getTitleText(){
        return page_.getTitleText();
    }

}
