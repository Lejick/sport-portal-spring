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

public class NotesForm extends Div {
    String stdWidth = "100px";
    private VerticalLayout grid = new VerticalLayout();
    NoteRepository noteRepository;

    EventsView eventsView;

    public NotesForm(NoteRepository noteRepository, EventsView eventsView) {
        this.eventsView = eventsView;
        setClassName("product-form");
        grid.setSizeUndefined();
        this.noteRepository = noteRepository;
        add(grid);
    }

    public void showStat(Long eventId) {
        grid.removeAll();

        Button autoLinksButton = new Button("Auto Links");
        autoLinksButton.addClickListener(event -> changeToAutoLinks());
        autoLinksButton.setWidth("200");
        Button userLinksButton = new Button("User Links");
        userLinksButton.addClickListener(event -> changeUserLinks());
        userLinksButton.setWidth("200");
        HorizontalLayout buttonBar = new HorizontalLayout(autoLinksButton, userLinksButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByEventId(eventId);
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.NOTE) || note.getType().equals(NoteType.NOTE)) {
                HorizontalLayout rows = new HorizontalLayout();
                Label label = new Label(note.getDescr());
                Anchor link = new Anchor(note.getLink(), note.getLink());
                Label label2 = new Label("(" + note.getUser() + "/" + note.getDate() + ")");
                rows.add(label, link, label2);
                grid.add(rows);
            }
        }

        HorizontalLayout rows = new HorizontalLayout();
        TextField descrField = new TextField("Input Description");
        descrField.setWidth("1000");
        rows.add(descrField);


        HorizontalLayout buttonRows = new HorizontalLayout();

        Button addPublicButton = new Button("Add Public");
        addPublicButton.addClickListener(event -> createNote(descrField.getValue(),null, eventId, true));
        addPublicButton.setWidth("300");
        Button addPivateButton = new Button("Add Private");
        addPivateButton.addClickListener(event -> createNote(descrField.getValue(), null, eventId, false));

        addPivateButton.setWidth("300");
        buttonRows.add(addPivateButton, addPublicButton);


        grid.add(rows);
        grid.add(buttonRows);

    }

    public void createNote(String descr, String link, Long eventId, boolean isPublic) {
        Calendar calendar = Calendar.getInstance();
        Note note = new Note();
        note.setDate(calendar.getTime());
        note.setUser(CurrentUser.get());
        note.setDescr(descr);
        note.setEventId(eventId);
        note.setLink(link);
        note.setType(NoteType.NOTE);
        note.setPublictype(isPublic);
        noteRepository.save(note);
    }

    private void changeToAutoLinks() {
        setVisible(false);
        eventsView.getLinksForm().setVisible(false);
        eventsView.getAutoLinksForm().setVisible(true);

    }

    private void changeUserLinks() {
        setVisible(false);
        eventsView.getLinksForm().setVisible(true);
        eventsView.getAutoLinksForm().setVisible(false);

    }
}



