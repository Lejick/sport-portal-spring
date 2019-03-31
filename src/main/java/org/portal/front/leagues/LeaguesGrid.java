package org.portal.front.leagues;

import org.portal.back.model.League;
import com.vaadin.flow.component.grid.Grid;

/**
 * Grid of products, handling the visual presentation and filtering of a set of
 * items. This version uses an in-memory data source that is suitable for small
 * data sets.
 */
public class LeaguesGrid extends Grid<League> {

    public LeaguesGrid() {

        setSizeFull();
        addColumn(League::getName)
                .setHeader("Name")
                .setFlexGrow(20)
                .setSortable(true);

        addColumn(League::getStartsFormat)
                .setHeader("Start Date(MSK)")
                .setFlexGrow(20)
                .setSortable(true);

    }

}
