package org.portal.front.leagues;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.tennis.TennisLeaguesLogic;

@Route(value = "Tennis_Leagues", layout = MainLayout.class)
public class TennisLeagueView extends LeaguesView {
    public static final String VIEW_NAME = "Tennis";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
       return   new TennisLeaguesLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeagues(Constants.TENNIS_ID));
    }
}
