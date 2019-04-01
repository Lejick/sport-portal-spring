package org.portal.front.leagues;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.portal.ContextProvider;
import org.portal.MainLayout;
import org.portal.back.DataService;
import org.portal.back.model.League;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

public abstract class LeaguesView extends HorizontalLayout
        implements HasUrlParameter<String> {

    private LeaguesGrid grid;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    }

    public LeaguesView() {
        setSizeFull();
        grid = new LeaguesGrid();
        grid.setDataProvider(getDataProvider());
        grid.asSingleSelect().addValueChangeListener(
                event -> getLeaguesLogic().rowSelected(event.getValue()));
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
        getLeaguesLogic().init();
    }

    protected abstract LeaguesLogic getLeaguesLogic();

    protected abstract LeaguesDataProvider getDataProvider();

}
