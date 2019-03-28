package org.portal.model.events;

import org.portal.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.portal.backend.DataService;
import org.portal.backend.data.model.Event;
import org.portal.backend.data.model.League;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Route(value = "Tennis_Events", layout = MainLayout.class)
@Component
public class EventsView extends HorizontalLayout
        implements HasUrlParameter<String> {
    private EventsLogic viewLogic = new EventsLogic(this);
    private EventsDataProvider dataProvider;
    private EventsGrid grid;
    private MoneyLineForm moneyLineForm;
    private TotalForm totalForm;

    @Autowired
    DataService dataService;

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

    }

    public void showOdds(Event eventModelTennis) {
        moneyLineForm.setVisible(true);
        moneyLineForm.showOdds(eventModelTennis);
        totalForm.setVisible(true);
        totalForm.showOdds(eventModelTennis);

    }

    public EventsView() {
        setSizeFull();
        grid = new EventsGrid();
        //   grid.setDataProvider(new EventsDataProvider());
        grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));
        moneyLineForm = new MoneyLineForm(this);
        totalForm = new TotalForm(this);
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.setSizeFull();
        barAndGridLayout.expand(grid);
        add(barAndGridLayout);
        add(totalForm, moneyLineForm);
        viewLogic.init();
    }

    public MoneyLineForm getMoneyLineForm() {
        return moneyLineForm;
    }

    public void setMoneyLineForm(MoneyLineForm moneyLineForm) {
        this.moneyLineForm = moneyLineForm;
    }

    public TotalForm getTotalForm() {
        return totalForm;
    }

    public void setTotalForm(TotalForm totalForm) {
        this.totalForm = totalForm;
    }
}
