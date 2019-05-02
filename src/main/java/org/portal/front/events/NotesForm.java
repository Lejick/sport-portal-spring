package org.portal.front.events;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
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
        String userName = CurrentUser.get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Button autoLinksButton = new Button("Auto Links");
        autoLinksButton.addClickListener(event -> changeToAutoLinks());
        autoLinksButton.setWidth("200");
        Button userLinksButton = new Button("User Links");
        userLinksButton.addClickListener(event -> changeUserLinks());
        userLinksButton.setWidth("200");
        HorizontalLayout buttonBar = new HorizontalLayout(userLinksButton, autoLinksButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByEventId(eventId);
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.NOTE) && (note.getUser().equals(userName) || note.isPublictype())) {
                Button delButton = new Button("del");
                delButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
                delButton.setWidth("50");
                delButton.addClickListener(event ->deleteNote(note.getId()));

                HorizontalLayout rows = new HorizontalLayout();
                Label label = new Label(note.getDescr());
                Label label2 = new Label(format.format(note.getDate()) + ";" + note.getUser());
                if (note.getUser().equals(userName)) {
                    rows.add(delButton);
                }
                rows.add(label, label2);
                grid.add(rows);
            }
        }

        HorizontalLayout rows = new HorizontalLayout();

        TextArea descrField = new TextArea("Input Note");
        descrField.setWidth("1000");
        descrField.setHeight("200");
        rows.add(descrField);


        HorizontalLayout buttonRows = new HorizontalLayout();

        Button addPublicButton = new Button("Add Public");
        addPublicButton.addClickListener(event -> createNote(descrField.getValue(), eventId, true));
        addPublicButton.setWidth("300");
        addPublicButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        Button addPivateButton = new Button("Add Private");
        addPivateButton.addClickListener(event -> createNote(descrField.getValue(), eventId, false));
        addPivateButton.setWidth("300");
        addPivateButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        buttonRows.add(addPivateButton, addPublicButton);


        grid.add(rows);
        grid.add(buttonRows);

    }

    public void createNote(String descr, Long eventId, boolean isPublic) {
        if (descr != null && !descr.trim().isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            Note note = new Note();
            note.setDate(calendar.getTime());
            note.setUser(CurrentUser.get());
            note.setDescr(descr);
            note.setEventId(eventId);
            note.setType(NoteType.NOTE);
            note.setPublictype(isPublic);
            noteRepository.save(note);
        }
    }

    public void deleteNote(Long noteId) {
            noteRepository.deleteById(noteId);

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



