package pl.tomaszdziurko.itemdirectory.web.view;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import pl.tomaszdziurko.itemdirectory.service.users.SerialUtil;

import pl.tomaszdziurko.itemdirectory.service.users.UserService;
import pl.tomaszdziurko.itemdirectory.web.BasePage;

public class HomePage extends BasePage {
	
	@SpringBean
	private UserService userService;

	public HomePage() {
		initGui();
	}

	private void initGui() {
        Label label = new Label("helloLabel", "HomePage");
        final Model<Component> swapOut = new Model();
        swapOut.setObject(label);
        
        label.setOutputMarkupId(true);
        label.setMarkupId("label");
		add(swapOut.getObject());
		add(new Label("currentTime", new Date().toString()));
		add(new Label("numberOfUsers", userService.size() +""));
        AjaxLink ajaxLink = new AjaxLink("button") {
                                @Override
                                public void onClick(AjaxRequestTarget target) {
                                    Label nlabel = new Label("helloLabel", "clicked");
                                    swapOut.getObject().replaceWith(nlabel);
                                    swapOut.setObject(nlabel);
                                    nlabel.setOutputMarkupId(true);
                                    target.addComponent(swapOut.getObject());
                                }
                            };
        ajaxLink.setMarkupId("button");
        add(ajaxLink);
	}

    @Override
    protected void onDetach() {
        super.onDetach();
        try {
            SerialUtil.doSerialTestOut(this);
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
