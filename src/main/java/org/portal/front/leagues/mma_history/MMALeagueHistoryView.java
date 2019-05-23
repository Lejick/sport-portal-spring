package org.portal.front.leagues.mma_history;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.SearchForm;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;

@Route(value = "MMA_Leagues_History", layout = MainLayout.class)
public class MMALeagueHistoryView extends LeaguesView {
    public static final String VIEW_NAME = "MMA(History)";
    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return new MMALeaguesHistoryLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeaguesHistory(getSportId()));
    }

    @Override
    protected void initSearchForm() {
    }

    @Override
    public int getSportId() {
        return Constants.MMA_ID;
    }
}
