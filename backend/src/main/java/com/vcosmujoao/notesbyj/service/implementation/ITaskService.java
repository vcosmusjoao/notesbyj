package com.vcosmujoao.notesbyj.service.implementation;

import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import com.vcosmujoao.notesbyj.entity.Note;

import java.util.List;
import java.util.Optional;

public interface ITaskService {
    List<Note> getAllNotes();
    Note saveNewNote(NoteDTO noteDTO);
    Optional<Note> findNoteById(Long id);

}
