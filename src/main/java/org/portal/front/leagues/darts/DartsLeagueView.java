package org.portal.front.leagues.darts;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;


@Route(value = "Darts_Leagues", layout = MainLayout.class)
public class DartsLeagueView extends LeaguesView {
    public static final String VIEW_NAME = "Darts";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return   new DartsLeaguesLogic();
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
        return Constants.DARTS_ID;
    }
}
