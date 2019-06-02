package org.portal.front.leagues.darts_history;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.forms.SearchForm;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;

@Route(value = "Darts_Leagues_History", layout = MainLayout.class)
public class DartsLeagueHistoryView extends LeaguesView {
    public static final String VIEW_NAME = "Darts(History)";
    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return new DartsLeaguesHistoryLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeaguesHistory(getSportId()));
    }

    @Override
    protected void initSearchForm() {
        searchForm = new SearchForm(this);
        searchForm.setVisible(true);
        barAndGridLayout.add(searchForm);
    }

    @Override
    public int getSportId() {
        return Constants.DARTS_ID;
    }

}
