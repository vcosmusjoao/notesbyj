package com.vcosmujoao.notesbyj.controller;

import com.vcosmujoao.notesbyj.entity.Note;
import com.vcosmujoao.notesbyj.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NotesRepository repository;

    @GetMapping
    public List<Note> findAll() {
        return repository.findAll();
    }

}
