package com.vcosmujoao.notesbyj.controller;

import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import com.vcosmujoao.notesbyj.entity.Note;
import com.vcosmujoao.notesbyj.repository.NotesRepository;
import com.vcosmujoao.notesbyj.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        List<Note> notes = service.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findNoteById(@PathVariable Long id) {
        try{
            Optional<Note> optNote = service.findNoteById(id);
            if(optNote.isPresent()) {
                return new ResponseEntity<>(
                        optNote.get(), HttpStatus.OK);
            }
            else {
                return noTaskFoundResponse(id);
            }
            }catch(Exception e){
                return  errorResponse();
            }
    }


    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody NoteDTO noteDTO){
        try{
            return new ResponseEntity<>(
                    service.saveNewNote(noteDTO),
                    HttpStatus.CREATED);
        }catch (Exception e){
            return errorResponse();
        }
    }


    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseEntity<String> noTaskFoundResponse(Long id){
        return new ResponseEntity<>("No task found with id: " + id, HttpStatus.NOT_FOUND);
    }

}
