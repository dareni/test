package com.motormouth;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlBody;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.HtmlUnitContextFactory;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.gargoylesoftware.htmlunit.javascript.configuration.JavaScriptConfiguration;
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


    public MdiFramePage(String pageUrl) throws IOException{
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
		page_ = (HtmlPage)webClient.getPage(pageUrl);
        webClient.waitForBackgroundJavaScript(5000);
    }

    public void doLogin() throws IOException {
//        HtmlForm form = page_.getFormByName("loginform1");

//        HtmlButton loginButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/wicket:panel/form/a");
//        DomElement obj = page_.getFirstByXPath("/html/body");
//          HtmlBody body = page_.getFirstByXPath("/html/body");

        HtmlAnchor loginButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/*[local-name()='panel']/form/a");
//          System.out.println(table.getTextContent());


//        System.out.println(page_.asXml());
//        HtmlButton loginButton = (HtmlButton) page_.getElementById("signin2");
        HtmlTextInput username = page_.getElementByName("username");
        username.setValueAttribute("a");
        HtmlPasswordInput password = page_.getElementByName("password");
        password.setValueAttribute("a");
        /*
        HtmlSelect cityList = form.getSelectByName("MapSearch1:DropDownListCity");
        cityList.setSelectedAttribute("Gold Coast", true);
        HtmlCheckBoxInput robinaCheckBox = form.getInputByName("MapSearch1:CheckBoxListSubregions:3");
        robinaCheckBox.setChecked(true);
        HtmlInput searchButton = form.getInputByName("MapSearch1:ButtonSearchBySuburb");
        HtmlPage priceResult = searchButton.click();
        return priceResult;
         */
        page_ = (HtmlPage) loginButton.click();
//        page_ = (HtmlPage) page_.refresh();
        webClient.waitForBackgroundJavaScript(5000);

//        loginButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/*[local-name()='panel']/form/a");
//        page_ = (HtmlPage) loginButton.click();
    }

/*
    public DAEMembersLounge goMembersLoungePage(String memberNo, String password) throws IOException{
		HtmlForm form = page.getFormByName(PageTags.formTagName);
	    HtmlTextInput memberNoTextInput = (HtmlTextInput) form.getInputByName(PageTags.memberNoTagName);
	    memberNoTextInput.setValueAttribute(memberNo);
	    HtmlTextInput passwordTextInput = (HtmlTextInput) form.getInputByName(PageTags.passwordTagName);
	    passwordTextInput.setValueAttribute(password);
	    HtmlAnchor loginButton = (HtmlAnchor)form.getElementById(PageTags.loginButtonTagId);
	    DAEMembersLounge membersLoungePage = new DAEMembersLounge((HtmlPage)loginButton.click());
        return membersLoungePage;
	}

    public  DAESearchBonusRental goSearchBonusRental() throws IOException {
        HtmlDivision bonusRentalDiv = (HtmlDivision) page.getHtmlElementById(PageTags.searchBonusRentalButtonTagName);
        HtmlAnchor bonusRentalButton = (HtmlAnchor) bonusRentalDiv.getChildElements().iterator().next();
        DAESearchBonusRental searchBonusRentalPage = new DAESearchBonusRental((HtmlPage) bonusRentalButton.click());
        return searchBonusRentalPage;

    }

    public  DAEHelpDesk goHelpDeskPage() throws IOException{
        HtmlForm form = page.getFormByName(PageTags.formTagName);
        HtmlDivision footerNavDiv = (HtmlDivision) page.getHtmlElementById(PageTags.footerNavigationTagId);
        HtmlUnorderedList footerSubNavUl = (HtmlUnorderedList) footerNavDiv.getChildElements().iterator().next();
        HtmlElement footerSubNavLi = (HtmlElement) footerSubNavUl.getChildElements().iterator().next();
        footerSubNavLi = (HtmlElement) footerSubNavUl.getChildElements().iterator().next();
	DAEHelpDesk helpDeskPage = new DAEHelpDesk((HtmlPage) footerSubNavLi.mouseDown());
        return helpDeskPage;
    }
 *
 */

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

    void checkLogin() {
//        HtmlAnchor userButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/*[local-name()='panel']/div/div/*[local-name()='panel']/a");
//      HtmlAnchor userButton = page_.getFirstByXPath("/html/body/div/div/table/tbody/tr/td[3]/div/wicket:panel/div/div/wicket:panel/a");
//        System.out.println("text:"+page_.asXml());
//        System.out.println("text:"+userButton.asText());
    }
    

}
