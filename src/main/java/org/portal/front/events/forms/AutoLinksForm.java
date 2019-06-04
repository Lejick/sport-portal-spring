package org.portal.front.events.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.portal.authentication.CurrentUser;
import org.portal.back.model.Event;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.portal.back.pinnacle.Constants;
import org.portal.front.events.EventsView;

import java.util.Calendar;
import java.util.List;

public class AutoLinksForm extends Div {
    private VerticalLayout grid = new VerticalLayout();
    NoteRepository noteRepository;

    EventsView eventsView;

    public AutoLinksForm(NoteRepository noteRepository, EventsView eventsView) {
        this.eventsView = eventsView;
        setClassName("product-form");
        grid.setSizeUndefined();
        this.noteRepository = noteRepository;
        add(grid);
    }

    public void showStat(Event eventP) {
        grid.removeAll();
        Long eventId = eventP.getId();
        Button userLinkButton = new Button("User Links");
        userLinkButton.addClickListener(event -> changeToUserLinks());
        userLinkButton.setWidth("200");
        Button notesButton = new Button("Notes");
        notesButton.addClickListener(event -> changeToNotes());
        notesButton.setWidth("200");

        Button personalButton = new Button("Personal");
        personalButton.addClickListener(event -> changePersonal());
        notesButton.setWidth("200");

        HorizontalLayout buttonBar = new HorizontalLayout(userLinkButton, notesButton, personalButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByEventId(eventId);
        HorizontalLayout homeRow = new HorizontalLayout();
        HorizontalLayout awayRow = new HorizontalLayout();
        Label homeLabel = new Label(eventP.getHome());
        Label awayLabel = new Label(eventP.getAway());
        homeRow.add(homeLabel);
        awayRow.add(awayLabel);
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.AUTOLINK)) {
                String descr = note.getDescr();
                if (descr.contains(eventP.getHome())) {
                    if (descr.contains("Sherdog")) {
                        Anchor link = new Anchor(note.getLink(), "Sherdog");
                        homeRow.add(link);
                    }
                }
                if (descr.contains(eventP.getAway())) {
                    if (descr.contains("Sherdog")) {
                        Anchor link = new Anchor(note.getLink(), "Sherdog");
                        awayRow.add(link);
                    }
                }

                if (descr.contains(eventP.getHome())) {
                    if (descr.contains("TennisExplorer")) {
                        Anchor link = new Anchor(note.getLink(), "Tennis Explorer");
                        homeRow.add(link);
                    }
                }
                if (descr.contains(eventP.getAway())) {
                    if (descr.contains("TennisExplorer")) {
                        Anchor link = new Anchor(note.getLink(), "Tennis Explorer");
                        awayRow.add(link);
                    }
                }

            }
        }
        if (eventP.getSport_id() == Constants.TENNIS_ID) {
            Anchor link = new Anchor("https://matchstat.com/tennis/h2h-odds-bets/" +
                    getPlayerName(eventP.getHome()) + "/" + getPlayerName(eventP.getAway()),
                    "Match Stat H2H");
            grid.add(link);
        }

        grid.add(homeRow, awayRow);

    }

    private void changeToUserLinks() {
        setVisible(false);
        eventsView.getLinksForm().setVisible(true);
        eventsView.getNotesForm().setVisible(false);
        eventsView.getPersonalNotesForm().setVisible(false);
    }

    private void changeToNotes() {
        setVisible(false);
        eventsView.getLinksForm().setVisible(false);
        eventsView.getNotesForm().setVisible(true);
        eventsView.getPersonalNotesForm().setVisible(false);
    }

    private void changePersonal() {
        setVisible(false);
        eventsView.getPersonalNotesForm().setVisible(true);
        eventsView.getLinksForm().setVisible(false);
        eventsView.getNotesForm().setVisible(false);
    }

    private String getPlayerName(String srcName) {
        String[] strArr = srcName.split(" ");
        return strArr[0] + " " + strArr[1];
    }

}



