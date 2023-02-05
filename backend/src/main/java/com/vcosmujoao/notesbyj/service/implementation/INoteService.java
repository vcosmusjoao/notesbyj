package com.vcosmujoao.notesbyj.service.implementation;

import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import com.vcosmujoao.notesbyj.entity.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface INoteService {
    Page<Note> getAllNotes(Pageable pageable);
    Note saveNewNote(NoteDTO noteDTO);
    Optional<Note> findNoteById(Long id);
    Note updateNote(Long id, NoteDTO noteDTO);
    void deleteNote(Note note);
}
