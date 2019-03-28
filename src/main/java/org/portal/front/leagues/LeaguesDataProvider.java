package org.portal.front.leagues;

import org.portal.back.model.League;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.Collection;


public class LeaguesDataProvider extends ListDataProvider<League> {
    public LeaguesDataProvider(Collection<League> items) {
        super(items);
    }

}
