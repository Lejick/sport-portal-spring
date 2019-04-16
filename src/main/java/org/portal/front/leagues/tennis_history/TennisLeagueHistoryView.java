package org.portal.front.leagues.tennis_history;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;

@Route(value = "Tennis_Leagues_History", layout = MainLayout.class)
public class TennisLeagueHistoryView extends LeaguesView {
    public static final String VIEW_NAME = "Tennis(History)";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return new TennisLeaguesHistoryLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeaguesHistory(Constants.TENNIS_ID));
    }
}
