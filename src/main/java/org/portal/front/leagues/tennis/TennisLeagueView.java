package org.portal.front.leagues.tennis;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;

@Route(value = "Tennis_Leagues", layout = MainLayout.class)
public class TennisLeagueView extends LeaguesView {
    public static final String VIEW_NAME = "Tennis";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
       return   new TennisLeaguesLogic();
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
        return Constants.TENNIS_ID;
    }
}
