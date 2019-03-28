package org.portal.model.leagues;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.portal.MainLayout;
import org.portal.backend.DataService;
import org.portal.backend.data.model.League;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@SpringComponent
@Route(value = "Tennis_Leagues", layout = MainLayout.class)
public class LeaguesView extends HorizontalLayout
        implements HasUrlParameter<String> {
    private LeaguesLogic viewLogic = new LeaguesLogic();

    @Autowired
    DataService dataService;



    public static final String VIEW_NAME = "Tennis";
    private LeaguesGrid grid;

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter String parameter) {
        Collection<League> leagues = dataService.getAllLeagues();
        grid.setDataProvider(new LeaguesDataProvider(leagues));
    }

    public LeaguesView() {
        setSizeFull();
        grid = new LeaguesGrid();
        grid.asSingleSelect().addValueChangeListener(
                event -> viewLogic.rowSelected(event.getValue()));
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);

        add(barAndGridLayout);
        viewLogic.init();
    }

}
