package org.portal;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.portal.front.leagues.soccer.SoccerLeagueView;
import org.portal.front.leagues.soccer_history.SoccerLeagueHistoryView;
import org.portal.front.leagues.tennis_history.TennisLeagueHistoryView;
import org.portal.front.leagues.tennis.TennisLeagueView;

@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class)
@PWA(name = "Stat Portal", shortName = "Stat")
public class MainLayout extends FlexLayout implements RouterLayout {
    private Menu menu;

    public MainLayout() {

        setSizeFull();
        setClassName("main-layout");

        menu = new Menu();
        menu.setAlignItems(Alignment.BASELINE);
        menu.addView(TennisLeagueView.class, TennisLeagueView.VIEW_NAME);
        menu.addView(TennisLeagueHistoryView.class, TennisLeagueHistoryView.VIEW_NAME);
        menu.addView(SoccerLeagueView.class, SoccerLeagueView.VIEW_NAME);
        menu.addView(SoccerLeagueHistoryView.class, SoccerLeagueHistoryView.VIEW_NAME);

        add(menu);
    }
}
