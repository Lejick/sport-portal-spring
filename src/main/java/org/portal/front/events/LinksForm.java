package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class LinksForm extends Div {
    String stdWidth = "100px";
    private VerticalLayout grid = new VerticalLayout();
    NoteRepository noteRepository;

    EventsView eventsView;

    public LinksForm(NoteRepository noteRepository, EventsView eventsView) {
        this.eventsView = eventsView;
        setClassName("product-form");
        grid.setSizeUndefined();
        this.noteRepository = noteRepository;
        add(grid);
    }

    public void showStat(Long eventId) {
        grid.removeAll();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String userName = CurrentUser.get();
      //  Button autoLinksButton = new Button("Auto Links");
      //  autoLinksButton.addClickListener(event -> changeToAutoLinks());

        Button notesButton = new Button("Notes");
        notesButton.addClickListener(event -> changeToNotes());

        HorizontalLayout buttonBar = new HorizontalLayout( notesButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByEventId(eventId);
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.USERLINK) && (note.getUser().equals(userName) || note.isPublictype())) {
                HorizontalLayout rows = new HorizontalLayout();

                Button delButton = new Button("del");
                delButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
                delButton.setWidth("50");
                delButton.addClickListener(event -> deleteNote(note.getId()));


                Label label = new Label(note.getDescr());
                Anchor link = new Anchor(note.getLink(), note.getLink());
                Label label2 = new Label(format.format(note.getDate()) + ";" + note.getUser());
                if (note.getUser().equals(userName)) {
                    rows.add(delButton);
                }
                rows.add(label, link, label2);
                grid.add(rows);
            }
        }

        HorizontalLayout rows = new HorizontalLayout();
        TextField descrField = new TextField("Input Description");
        descrField.setWidth("1000");
        TextField linkField = new TextField("Input Link");
        linkField.setWidth("1000");
        rows.add(descrField, linkField);

        HorizontalLayout buttonRows = new HorizontalLayout();
        Button addPublicButton = new Button("Add Public");
        addPublicButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
        addPublicButton.setWidth("300");
        addPublicButton.addClickListener(event -> createNote(descrField.getValue(), linkField.getValue(), eventId, true));

        Button addPrivateButton = new Button("Add Private");
        addPrivateButton.addClickListener(event -> createNote(descrField.getValue(), linkField.getValue(), eventId, false));
        addPrivateButton.setWidth("300");
        addPrivateButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);
        buttonRows.add(addPrivateButton, addPublicButton);
        grid.add(rows);
        grid.add(buttonRows);

    }

    public void createNote(String descr, String link, Long eventId, boolean isPublic) {
        if (descr != null && !descr.trim().isEmpty() && link != null && !link.trim().isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            Note note = new Note();
            note.setDate(calendar.getTime());
            note.setUser(CurrentUser.get());
            note.setDescr(descr);
            note.setEventId(eventId);
            note.setLink(link);
            note.setType(NoteType.USERLINK);
            note.setPublictype(isPublic);
            noteRepository.save(note);
        }
    }

    public void deleteNote(Long noteId) {
        noteRepository.deleteById(noteId);

    }

    private void changeToAutoLinks() {
        setVisible(false);
        eventsView.getNotesForm().setVisible(false);
        eventsView.getAutoLinksForm().setVisible(true);
    }

    private void changeToNotes() {
        setVisible(false);
        eventsView.getNotesForm().setVisible(true);
        eventsView.getAutoLinksForm().setVisible(false);
    }
}



