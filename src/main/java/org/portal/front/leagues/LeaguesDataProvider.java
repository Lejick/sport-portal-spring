package org.portal.model.leagues;

import org.portal.backend.data.model.League;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.Collection;


public class LeaguesDataProvider extends ListDataProvider<League> {
    public LeaguesDataProvider(Collection<League> items) {
        super(items);
    }

}
