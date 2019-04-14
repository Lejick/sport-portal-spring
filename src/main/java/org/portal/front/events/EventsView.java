package org.portal.front.events;

import org.portal.ContextProvider;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.portal.authentication.AccessControl;
import org.portal.authentication.AccessControlFactory;
import org.portal.authentication.CurrentUser;
import org.portal.back.DataService;
import org.portal.back.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class EventsView extends HorizontalLayout
        implements HasUrlParameter<String> {
    protected EventsLogic viewLogic = new EventsLogic(this);
    protected EventsGrid grid;
    protected MoneyLineForm moneyLineForm;
    protected TotalForm totalForm;
    protected SpreadForm spreadForm;
    protected StatisticForm statisticForm;
    protected DataService ds = ContextProvider.getBean(DataService.class);
    final AccessControl accessControl = AccessControlFactory.getInstance()
            .createAccessControl();

    private final Logger LOGGER = LoggerFactory.getLogger(EventsView.class);

    public void showOdds(Event eventModelTennis) {
        moneyLineForm.setVisible(true);
        moneyLineForm.showOdds(eventModelTennis);
        statisticForm.setVisible(true);
        statisticForm.showStat(eventModelTennis);

        spreadForm.setVisible(false);
        spreadForm.showOdds(eventModelTennis);
        totalForm.setVisible(false);
        totalForm.showOdds(eventModelTennis);

    }

    public EventsView() {
        if (accessControl.isUserSignedIn()) {
            LOGGER.info("Loggin user: " + CurrentUser.get());
        }
        setSizeFull();
        grid = new EventsGrid();
        grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));
        moneyLineForm = new MoneyLineForm(this);
        totalForm = new TotalForm(this);
        spreadForm = new SpreadForm(this);
        statisticForm = new StatisticForm(this);

        VerticalLayout barAndGridLayout = new VerticalLayout();
        HorizontalLayout statLayout = new HorizontalLayout();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
      //  barAndGridLayout.setSizeFull();
       // barAndGridLayout.expand(grid);
        barAndGridLayout.add(statisticForm);
        add(barAndGridLayout);
        add(totalForm, moneyLineForm,spreadForm);
        viewLogic.init();
    }

    public MoneyLineForm getMoneyLineForm() {
        return moneyLineForm;
    }

    public SpreadForm getSpreadForm() {
        return spreadForm;
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
