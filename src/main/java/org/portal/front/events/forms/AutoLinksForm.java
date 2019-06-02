package org.portal.front.events.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.portal.authentication.CurrentUser;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.portal.front.events.EventsView;

import java.util.Calendar;
import java.util.List;

public class AutoLinksForm extends Div {
    private VerticalLayout grid = new VerticalLayout();
    NoteRepository noteRepository;

    EventsView eventsView;

    public AutoLinksForm(NoteRepository noteRepository, EventsView eventsView) {
        this.eventsView=eventsView;
        setClassName("product-form");
        grid.setSizeUndefined();
        this.noteRepository=noteRepository;
        add(grid);
    }

    public void showStat(Long eventId) {
        grid.removeAll();

        Button userLinkButton = new Button("User Links");
        userLinkButton.addClickListener(event -> changeToUserLinks());
        userLinkButton.setWidth("200");
        Button notesButton = new Button("Notes");
        notesButton.addClickListener(event -> changeToNotes());
        notesButton.setWidth("200");

        Button personalButton = new Button("Personal");
        personalButton.addClickListener(event -> changePersonal());
        notesButton.setWidth("200");

        HorizontalLayout buttonBar = new HorizontalLayout(userLinkButton, notesButton,personalButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByEventId(eventId);
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.AUTOLINK)) {
                HorizontalLayout rows = new HorizontalLayout();
                Label label = new Label(note.getDescr());
                Anchor link = new Anchor(note.getLink(), "Link");
                rows.add(label, link);
                grid.add(rows);
            }
        }
    }

    private void changeToUserLinks(){
        setVisible(false);
        eventsView.getLinksForm().setVisible(true);
        eventsView.getNotesForm().setVisible(false);
        eventsView.getPersonalNotesForm().setVisible(false);
    }
    private void changeToNotes(){
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
}



