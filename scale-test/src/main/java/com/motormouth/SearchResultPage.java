package com.motormouth;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author daren
 */
public class SearchResultPage {
    HtmlPage page_ = null;


    public SearchResultPage(HtmlPage searchResultPage) throws IOException{
        page_ = searchResultPage;
        System.out.println(page_.asXml());
    }

    public void getPrice() throws IOException {
        HtmlForm form = page_.getFormByName("form1");
//        List<Node> table = (List<Node>) form.getByXPath("//table[@id='PriceSearchResults1_DatalistPrices']");
//        List<Node> table = (List<Node>) form.getByXPath("//table[@id]");
//        form.getByXPath("/table/tbody/tr/td[1]");
//        List<Node> table = (List<Node>)form.getByXPath("/table/tbody/tr/td/table/tbody/tr/td/table/div/p");
//        List<Node> table = (List<Node>)form.getByXPath("table/tbody/tr/td[2]/table[last()]/tbody/tr");
//        List<Node> table = (List<Node>)form.getByXPath("table/tbody/tr/td[2]/table[2]/tbody/tr/td[2]/div/p/table/tbody/tr/td/table/tbody");
        List<Node> table = (List<Node>)form.getByXPath("table/tbody/tr/td[2]/table[2]/tbody/tr/td[2]/div");
        System.out.println(table.size());
//        HtmlTable table = (HtmlTable) form.getElementById("PriceSearchResults1_DatalistPrices");
//        List<HtmlTableBody> tableBodyList = table.getBodies();
//        Iterator<HtmlElement> iter = tableBodyList.get(0).getRows().get(0).getCell(0).getAllHtmlChildElements().iterator();
//        HtmlTable childTable = (HtmlTable) iter.next();
//        List<HtmlTableRow> priceRowList = childTable.getBodies().get(0).getRows();
//        HtmlTableRow priceRow = priceRowList.get(12);
//        List<HtmlTableCell> priceDataList = priceRow.getCells();
//        HtmlTableCell addressCell = priceDataList.get(2);
//        System.out.println(addressCell.asText());

    }

    public void printAllNodes() {
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
