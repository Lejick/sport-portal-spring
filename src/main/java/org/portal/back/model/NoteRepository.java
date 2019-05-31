package org.portal.back.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByEventId(Long eventId);
    List<Note> findByPersonName(String personName);
}
