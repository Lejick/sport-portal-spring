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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class LinksForm extends Div {
    String stdWidth = "100px";
    private VerticalLayout grid = new VerticalLayout();
    @Autowired
    NoteRepository noteRepository;

    public LinksForm(NoteRepository noteRepository) {
        setClassName("product-form");
        grid.setSizeUndefined();
        this.noteRepository=noteRepository;
        add(grid);
    }

    public void showStat(Long eventId) {
        grid.removeAll();

        List<Note> listNote = noteRepository.findByEventId(eventId);
        for (Note note : listNote) {
            HorizontalLayout rows = new HorizontalLayout();
            Label label = new Label(note.getDescr());

            Anchor link = new Anchor(note.getLink(), note.getLink());
            Label label2 = new Label("("+note.getUser()+"/"+note.getDate()+")");
            rows.add(label, link,label2);
          grid.add(rows);
        }

      HorizontalLayout rows = new HorizontalLayout();
        TextField descrField = new TextField("Input Description");
        descrField.setWidth("1000");
        TextField linkField = new TextField("Input Link");
        linkField.setWidth("1000");
        Button button = new Button("Add Link");
        button.setWidth(stdWidth);
        button.addClickListener(event -> createNote(descrField.getValue(), linkField.getValue(), eventId));
        rows.add(descrField, linkField);
        HorizontalLayout buttonRows = new HorizontalLayout();
        buttonRows.add(button);
        grid.add(rows);
        grid.add(buttonRows);

    }

    public void createNote(String descr, String link,Long eventId) {
        Calendar calendar = Calendar.getInstance();
        Note note = new Note();
        note.setDate(calendar.getTime());
        note.setUser(CurrentUser.get());
        note.setDescr(descr);
        note.setEventId(eventId);
        note.setLink(link);
        noteRepository.save(note);
    }
}



