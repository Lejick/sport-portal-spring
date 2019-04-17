package org.portal.front.leagues.box;

import com.vaadin.flow.router.Route;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.pinnacle.Constants;
import org.portal.front.leagues.LeaguesDataProvider;
import org.portal.front.leagues.LeaguesLogic;
import org.portal.front.leagues.LeaguesView;

@Route(value = "Box_Leagues", layout = MainLayout.class)
public class BoxLeagueView extends LeaguesView {
    public static final String VIEW_NAME = "Boxing";

    @Override
    protected LeaguesLogic getLeaguesLogic() {
        return new BoxLeaguesLogic();
    }

    @Override
    protected LeaguesDataProvider getDataProvider() {
        return new LeaguesDataProvider(ContextProvider.getBean(DataService.class).getAllLeagues(Constants.BOXING_ID));
    }
}
