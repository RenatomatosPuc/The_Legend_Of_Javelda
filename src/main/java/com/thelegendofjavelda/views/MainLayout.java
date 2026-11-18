package com.thelegendofjavelda.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;

public class MainLayout extends AppLayout {
    public MainLayout() {
        addClassName("main-layout");
        SideNav menu = new SideNav();
        menu.addItem(new SideNavItem("Menu", "/"));
        menu.addItem(new SideNavItem("Jornada", "/jornada"));
        menu.addItem(new SideNavItem("Guilda", "/guilda"));
        menu.addItem(new SideNavItem("Equipe", "/equipe"));
        menu.addItem(new SideNavItem("Inventário", "/inventario"));
        menu.addItem(new SideNavItem("Loja", "/loja"));
        addToDrawer(menu);
    }

}
