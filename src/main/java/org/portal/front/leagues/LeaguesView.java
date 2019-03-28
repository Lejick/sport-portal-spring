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

@Route(value = "Tennis_Leagues", layout = MainLayout.class)
public class LeaguesView extends HorizontalLayout
        implements HasUrlParameter<String> {
    private LeaguesLogic viewLogic = new LeaguesLogic();
   private DataService ds = ContextProvider.getBean(DataService.class);
    public static final String VIEW_NAME = "Tennis";
    private LeaguesGrid grid;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
    }

    public LeaguesView() {

        LeaguesDataProvider dataProvider = new LeaguesDataProvider(ds.getAllLeagues());
        setSizeFull();
        grid = new LeaguesGrid();
        grid.setDataProvider(dataProvider);
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
