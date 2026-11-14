package com;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Image;
import com.vaadin.server.ThemeResource;

public class MainPage extends MainPageDesigner {

    MainPage(VerticalLayout verticalLayout , MyUI myUI){
        this.addStyleName("mainPageVerticalLayout");
        
//        Image coverImage = new Image(
//                "",
//                new ThemeResource("images/mainPageCover.jpg")
//            );
//        coverImage.setWidth("100%");
//        this.addComponent(coverImage);

        logInButton.addClickListener(e -> {
            String username = userNameTextField.getValue();
            String password = passwordTextField.getValue();

            if(username.equalsIgnoreCase("admin") &&
                    password.equalsIgnoreCase("admin")) {
                verticalLayout.removeAllComponents();

                Label title = new Label("Menu");
                title.addStyleName(ValoTheme.MENU_TITLE);

                Button view1 = new Button("Upload Files", event -> myUI.getNavigator().navigateTo("view1"));
                view1.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
                view1.setId("menuButton");

                Button view2 = new Button("Test Cases", event -> myUI.getNavigator().navigateTo("view2"));
                view2.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
                view2.setId("menuButton");

                Button view3 = new Button("Code Comparision", event -> myUI.getNavigator().navigateTo("view3"));
                view3.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
                view3.setId("menuButton");

                CssLayout menu = new CssLayout(title, view1, view2, view3);
                menu.addStyleName(ValoTheme.MENU_ROOT);

                CssLayout viewContainer = new CssLayout();

                HorizontalLayout mainLayout = new HorizontalLayout(menu, viewContainer);
                mainLayout.setHeight(65,Unit.INCH);

                myUI.setContent(mainLayout);

                Navigator navigator = new Navigator(myUI, viewContainer);
                navigator.addView("",  View1.class);
                navigator.addView("view1", View1.class);
                navigator.addView("view2", View2.class);
                navigator.addView("view3", View3.class);


            }
            else{
                Notification.show("Username or Password not correct");
            }
        });
    }
}

