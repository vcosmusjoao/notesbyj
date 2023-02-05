package com.vcosmujoao.notesbyj.service;

import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import com.vcosmujoao.notesbyj.entity.Note;
import com.vcosmujoao.notesbyj.repository.NotesRepository;
import com.vcosmujoao.notesbyj.service.implementation.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NoteService implements INoteService {
    @Autowired
    NotesRepository repository;

    @Override
    @Transactional
    public Page<Note> getAllNotes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Note saveNewNote(NoteDTO noteDTO) {
        return repository.save(convertDTO(noteDTO));
    }
    @Override
    @Transactional
    public Optional<Note> findNoteById(Long id){
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Note updateNote (Long id, NoteDTO noteDTO) {
        Optional<Note> optionalNote = repository.findById(id);
        if (optionalNote.isPresent()) {
            Note note = optionalNote.get();
            note.setTitulo(noteDTO.getTitulo());
            note.setDescricao(noteDTO.getDescricao());
            return repository.save(note);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteNote(Note note) {
        repository.delete(note);
    }

    private Note convertDTO( NoteDTO noteDTO){
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setTitulo(noteDTO.getTitulo());
        note.setDescricao(noteDTO.getDescricao());
        note.setDataCriacao(noteDTO.getDataCriacao());
        return note;
    }
}
