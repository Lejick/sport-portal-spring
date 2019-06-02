package org.portal.front.leagues;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.portal.front.events.forms.SearchForm;

public abstract class LeaguesView extends HorizontalLayout
        implements HasUrlParameter<String> {
    protected LeaguesGrid grid;
    protected SearchForm searchForm;
    protected VerticalLayout barAndGridLayout = new VerticalLayout();

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    }

    public LeaguesView() {
        setSizeFull();
        grid = new LeaguesGrid();
        grid.setDataProvider(getDataProvider());
        grid.asSingleSelect().addValueChangeListener(
                event -> getLeaguesLogic().rowSelected(event.getValue()));
        initSearchForm();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setSizeFull();
        add(barAndGridLayout);
        getLeaguesLogic().init();
    }

    protected abstract LeaguesLogic getLeaguesLogic();

    protected abstract LeaguesDataProvider getDataProvider();

    protected abstract void initSearchForm();

    public LeaguesGrid getGrid() {
        return grid;
    }

    public abstract int getSportId();

}
