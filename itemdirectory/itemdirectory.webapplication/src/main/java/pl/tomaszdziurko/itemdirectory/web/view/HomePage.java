package pl.tomaszdziurko.itemdirectory.web.view;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.basic.Label;
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
		add(new Label("helloLabel", "HomePage"));
		add(new Label("currentTime", new Date().toString()));
		add(new Label("numberOfUsers", userService.size() +""));
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
