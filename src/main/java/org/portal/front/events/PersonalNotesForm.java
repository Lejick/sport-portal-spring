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
import org.portal.back.model.Event;
import org.portal.back.model.Note;
import org.portal.back.model.NoteRepository;
import org.portal.back.model.NoteType;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

public class PersonalNotesForm extends Div {
    String stdWidth = "100px";
    private VerticalLayout grid = new VerticalLayout();
    NoteRepository noteRepository;

    EventsView eventsView;

    public PersonalNotesForm(NoteRepository noteRepository, EventsView eventsView) {
        this.eventsView = eventsView;
        setClassName("product-form");
        grid.setSizeUndefined();
        this.noteRepository = noteRepository;
        add(grid);
    }

    public void showStat(Event eventP) {
        grid.removeAll();
        String userName = CurrentUser.get();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Button autoLinksButton = new Button("Auto Links");
        autoLinksButton.addClickListener(event -> changeToAutoLinks());
        autoLinksButton.setWidth("200");
        Button userLinksButton = new Button("User Links");
        userLinksButton.addClickListener(event -> changeUserLinks());
        userLinksButton.setWidth("200");
        Button personalButton = new Button("Notes");
        personalButton.addClickListener(event -> changeToNotes());
        personalButton.setWidth("200");
        HorizontalLayout buttonBar = new HorizontalLayout(userLinksButton, autoLinksButton, personalButton);
        grid.add(buttonBar);
        List<Note> listNote = noteRepository.findByPersonName(eventP.getHome());
        grid.add(new Label(eventP.getHome()));
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.PERSONAL) && note.getSport_id() == eventP.getSport_id() && (note.getUser().equals(userName) || note.isPublictype())) {
                Button delButton = new Button("del");
                delButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
                delButton.setWidth("50");
                delButton.addClickListener(event -> deleteNote(note.getId()));

                HorizontalLayout rows = new HorizontalLayout();
                String descr = "";
                try {
                    descr = new String(Base64.getDecoder().decode(note.getDescr()), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Label label = new Label(descr);
                Label label2 = new Label(format.format(note.getDate()) + ";" + note.getUser());
                if (note.getUser().equals(userName)) {
                    rows.add(delButton);
                }
                rows.add(label, label2);
                grid.add(rows);
            }
        }

        HorizontalLayout rowsHome = new HorizontalLayout();

        TextArea descrFieldHome = new TextArea("Note for "+eventP.getHome());
        descrFieldHome.setWidth("2000");
        descrFieldHome.setHeight("200");
        rowsHome.add(descrFieldHome);


        HorizontalLayout buttonRowsHome = new HorizontalLayout();

        Button addPublicButtonHome = new Button("Add Public");
        addPublicButtonHome.addClickListener(event -> createNote(descrFieldHome.getValue(), eventP, true, eventP.getHome()));
        addPublicButtonHome.setWidth("300");
        addPublicButtonHome.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        Button addPivateButtonHome = new Button("Add Private");
        addPivateButtonHome.addClickListener(event -> createNote(descrFieldHome.getValue(), eventP, false, eventP.getHome()));
        addPivateButtonHome.setWidth("300");
        addPivateButtonHome.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        buttonRowsHome.add(addPivateButtonHome, addPublicButtonHome);

        grid.add(rowsHome);
        grid.add(buttonRowsHome);

        listNote = noteRepository.findByPersonName(eventP.getAway());
        grid.add(new Label(eventP.getAway()));
        for (Note note : listNote) {
            if (note.getType().equals(NoteType.PERSONAL) && note.getSport_id() == eventP.getSport_id() && (note.getUser().equals(userName) || note.isPublictype())) {
                Button delButton = new Button("del");
                delButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
                delButton.setWidth("50");
                delButton.addClickListener(event -> deleteNote(note.getId()));

                HorizontalLayout rows = new HorizontalLayout();
                String descr = "";
                try {
                    descr = new String(Base64.getDecoder().decode(note.getDescr()), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Label label = new Label(descr);
                Label label2 = new Label(format.format(note.getDate()) + ";" + note.getUser());
                if (note.getUser().equals(userName)) {
                    rows.add(delButton);
                }
                rows.add(label, label2);
                grid.add(rows);
            }
        }
        HorizontalLayout rowsAway = new HorizontalLayout();
        TextArea descrFieldAway = new TextArea("Note for "+eventP.getAway());
        descrFieldAway.setWidth("2000");
        descrFieldAway.setHeight("200");
        rowsAway.add(descrFieldAway);


        HorizontalLayout buttonRowsAway = new HorizontalLayout();

        Button addPublicButtonAway = new Button("Add Public");
        addPublicButtonAway.addClickListener(event -> createNote(descrFieldAway.getValue(), eventP, true, eventP.getAway()));
        addPublicButtonAway.setWidth("300");
        addPublicButtonAway.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        Button addPivateButtonAway = new Button("Add Private");
        addPivateButtonAway.addClickListener(event -> createNote(descrFieldAway.getValue(), eventP, false, eventP.getAway()));
        addPivateButtonAway.setWidth("300");
        addPivateButtonAway.addThemeVariants(ButtonVariant.LUMO_SUCCESS, ButtonVariant.LUMO_PRIMARY);

        buttonRowsAway.add(addPivateButtonAway, addPublicButtonAway);
        grid.add(rowsAway);
        grid.add(buttonRowsAway);


    }

    public void createNote(String descr, Event event, boolean isPublic, String name) {
        if (descr != null && !descr.trim().isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            Note note = new Note();
            note.setDate(calendar.getTime());
            note.setUser(CurrentUser.get());
            note.setDescr(convert(descr));
            note.setSport_id(event.getSport_id());
            note.setPersonName(name);
            note.setType(NoteType.PERSONAL);
            note.setPublictype(isPublic);
            noteRepository.save(note);
        }
    }

    private String convert(String text) {
        try {
            byte[] arr = text.getBytes("UTF-8");
            return Base64.getEncoder().encodeToString(arr);
        } catch (UnsupportedEncodingException e) {
        }
        return "";
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

    private void changeUserLinks() {
        setVisible(false);
        eventsView.getLinksForm().setVisible(true);
        eventsView.getAutoLinksForm().setVisible(false);

    }
}



