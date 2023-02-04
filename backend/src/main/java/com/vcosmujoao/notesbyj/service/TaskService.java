package com.vcosmujoao.notesbyj.service;

import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import com.vcosmujoao.notesbyj.entity.Note;
import com.vcosmujoao.notesbyj.repository.NotesRepository;
import com.vcosmujoao.notesbyj.service.implementation.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService implements ITaskService {
    @Autowired
    NotesRepository repository;

    @Override
    @Transactional
    public List<Note> getAllNotes() {
        return repository.findAll();
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

    private Note convertDTO( NoteDTO noteDTO){
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setTitulo(noteDTO.getTitulo());
        note.setDescricao(noteDTO.getDescricao());
        note.setDataCriacao(noteDTO.getDataCriacao());
        return note;
    }
}
