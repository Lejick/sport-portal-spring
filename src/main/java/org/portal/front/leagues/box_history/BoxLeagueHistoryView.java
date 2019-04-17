package org.portal.front.leagues.box_history;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;

@Route(value = "Box_Leagues_History", layout = MainLayout.class)
public class BoxLeagueHistoryView extends LeaguesView {
    public static final String VIEW_NAME = "Boxing(History)";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return new BoxLeaguesHistoryLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeaguesHistory(Constants.BOXING_ID));
    }
}
