package org.portal;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.portal.authentication.AccessControl;
import org.portal.authentication.AccessControlFactory;
import org.portal.authentication.LoginScreen;
import org.portal.front.leagues.box.BoxLeagueView;
import org.portal.front.leagues.box_history.BoxLeagueHistoryView;
import org.portal.front.leagues.mma.MMALeagueView;
import org.portal.front.leagues.mma_history.MMALeagueHistoryView;
import org.portal.front.leagues.soccer.SoccerLeagueView;
import org.portal.front.leagues.soccer_history.SoccerLeagueHistoryView;
import org.portal.front.leagues.tennis_history.TennisLeagueHistoryView;
import org.portal.front.leagues.tennis.TennisLeagueView;
import org.portal.front.leagues.darts.DartsLeagueView;
import org.portal.front.leagues.darts_history.DartsLeagueHistoryView;

@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class)
@PWA(name = "Stat Portal", shortName = "Stat")
public class MainLayout extends FlexLayout implements RouterLayout {
    private Menu menu;

    final AccessControl accessControl = AccessControlFactory.getInstance()
            .createAccessControl();

    public MainLayout() {

        setSizeFull();
        setClassName("main-layout");

        menu = new Menu();
        menu.setAlignItems(Alignment.BASELINE);
        menu.addView(TennisLeagueView.class, TennisLeagueView.VIEW_NAME);
        if(accessControl.isUserSignedIn()) {
            menu.addView(TennisLeagueHistoryView.class, TennisLeagueHistoryView.VIEW_NAME);
        }
        menu.addView(SoccerLeagueView.class, SoccerLeagueView.VIEW_NAME);
        if(accessControl.isUserSignedIn()) {
            menu.addView(SoccerLeagueHistoryView.class, SoccerLeagueHistoryView.VIEW_NAME);
        }
        menu.addView(MMALeagueView.class, MMALeagueView.VIEW_NAME);
        if(accessControl.isUserSignedIn()) {
            menu.addView(MMALeagueHistoryView.class, MMALeagueHistoryView.VIEW_NAME);
        }
        menu.addView(BoxLeagueView.class, BoxLeagueView.VIEW_NAME);
        if(accessControl.isUserSignedIn()) {
            menu.addView(BoxLeagueHistoryView.class, BoxLeagueHistoryView.VIEW_NAME);
        }
        menu.addView(DartsLeagueView.class, DartsLeagueView.VIEW_NAME);
        if(accessControl.isUserSignedIn()) {
            menu.addView(DartsLeagueHistoryView.class, DartsLeagueHistoryView.VIEW_NAME);
        }

        menu.addView(LoginScreen.class, "Login");
        if (!accessControl.isUserSignedIn()) {
            menu.add(new Label("DEMO!!! PLEASE CREATE ACCOUNT!!!"));
        }
        add(menu);
    }
}
