package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.portal.authentication.CurrentUser;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

public class AutoLinksForm extends Div {
    String stdWidth = "100px";
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
        HorizontalLayout buttonBar = new HorizontalLayout(userLinkButton, notesButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByEventId(eventId);
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.AUTOLINK)) {
                HorizontalLayout rows = new HorizontalLayout();
                Label label = new Label(note.getDescr());
                Anchor link = new Anchor(note.getLink(), note.getLink());
                rows.add(label, link);
                grid.add(rows);
            }
        }
    }

    public void createNote(String descr, String link,Long eventId) {
        Calendar calendar = Calendar.getInstance();
        Note note = new Note();
        note.setDate(calendar.getTime());
        note.setUser(CurrentUser.get());
        note.setDescr(descr);
        note.setEventId(eventId);
        note.setLink(link);
        note.setType(NoteType.AUTOLINK);
        noteRepository.save(note);
    }
    private void changeToUserLinks(){
        setVisible(false);
        eventsView.getLinksForm().setVisible(true);
        eventsView.getNotesForm().setVisible(false);

    }
    private void changeToNotes(){
        setVisible(false);
        eventsView.getLinksForm().setVisible(false);
        eventsView.getNotesForm().setVisible(true);

    }
}



