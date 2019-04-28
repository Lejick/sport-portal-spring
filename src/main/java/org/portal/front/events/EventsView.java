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
import org.portal.back.model.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class EventsView extends HorizontalLayout
        implements HasUrlParameter<String> {
    protected EventsLogic viewLogic = new EventsLogic(this);
    protected EventsGrid grid;
    protected MoneyLineForm moneyLineForm;
    protected TotalForm totalForm;
    protected SpreadForm spreadForm;
    protected LinksForm linksForm;
    protected AutoLinksForm autoLinksForm;
    protected NotesForm notesForm;
    protected boolean isHistory = false;
    protected DataService ds = ContextProvider.getBean(DataService.class);
    protected NoteRepository noteRepository = ContextProvider.getBean(NoteRepository.class);
    final AccessControl accessControl = AccessControlFactory.getInstance()
            .createAccessControl();

    private final Logger LOGGER = LoggerFactory.getLogger(EventsView.class);

    public void showOdds(Event event) {
        moneyLineForm.setVisible(true);
        moneyLineForm.showOdds(event);
        if (accessControl.isUserSignedIn()) {
            linksForm.setVisible(true);
            linksForm.showStat(event.getId());
            autoLinksForm.setVisible(false);
            autoLinksForm.showStat(event.getId());
            notesForm.setVisible(false);
            notesForm.showStat(event.getId());
        }

        spreadForm.setVisible(false);
        spreadForm.showOdds(event);
        totalForm.setVisible(false);
        totalForm.showOdds(event);

    }

    public EventsView() {
        setSizeFull();
        grid = new EventsGrid();
        grid.setHistory(isHistory);
        grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));
        moneyLineForm = new MoneyLineForm(this);
        totalForm = new TotalForm(this);
        spreadForm = new SpreadForm(this);
        linksForm = new LinksForm(noteRepository, this);
        autoLinksForm = new AutoLinksForm(noteRepository, this);
        notesForm = new NotesForm(noteRepository, this);
        VerticalLayout barAndGridLayout = new VerticalLayout();
        barAndGridLayout.add(grid);
        barAndGridLayout.setFlexGrow(1, grid);
        barAndGridLayout.add(linksForm, autoLinksForm, notesForm);
        add(barAndGridLayout);
        add(totalForm, moneyLineForm, spreadForm);
        viewLogic.init();
    }

    public MoneyLineForm getMoneyLineForm() {
        return moneyLineForm;
    }

    public SpreadForm getSpreadForm() {
        return spreadForm;
    }

    public TotalForm getTotalForm() {
        return totalForm;
    }

    public LinksForm getLinksForm() {
        return linksForm;
    }

    public AutoLinksForm getAutoLinksForm() {
        return autoLinksForm;
    }

    public NotesForm getNotesForm() {
        return notesForm;
    }
}
