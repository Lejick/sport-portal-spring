package org.portal.front.leagues.soccer;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;
import org.portal.front.leagues.tennis_history.TennisLeaguesHistoryLogic;

@Route(value = "Soccer_Leagues", layout = MainLayout.class)
public class SoccerLeagueView extends LeaguesView {
    public static final String VIEW_NAME = "Soccer";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return new SoccerLeaguesLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeagues(getSportId()));
    }

    @Override
    protected void initSearchForm() {

    }

    @Override
    public int getSportId() {
        return Constants.SOCCER_ID;
    }
}
