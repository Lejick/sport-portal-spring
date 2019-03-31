package org.portal.front.events;

import org.portal.ContextProvider;
import org.portal.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.portal.back.DataService;
import org.portal.back.model.Event;

import java.util.Collection;

@Route(value = "Tennis_Events", layout = MainLayout.class)
public class EventsView extends HorizontalLayout
        implements HasUrlParameter<String> {
    private EventsLogic viewLogic = new EventsLogic(this);
    private EventsGrid grid;
    private MoneyLineForm moneyLineForm;
    private TotalForm totalForm;
    private DataService ds = ContextProvider.getBean(DataService.class);

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String leagueName) {
        if (leagueName != null) {
            Collection<Event> events = ds.getEvents(leagueName);
            grid.setDataProvider(new EventsDataProvider(events) {
            });
        }
    }

    public void showOdds(Event eventModelTennis) {
        moneyLineForm.setVisible(true);
        moneyLineForm.showOdds(eventModelTennis);
        totalForm.setVisible(false);
        totalForm.showOdds(eventModelTennis);

    }

    public EventsView() {
        setSizeFull();
        grid = new EventsGrid();
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
