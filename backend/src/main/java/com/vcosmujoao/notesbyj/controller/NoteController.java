package com.vcosmujoao.notesbyj.controller;

import com.vcosmujoao.notesbyj.DTO.NoteDTO;
import com.vcosmujoao.notesbyj.entity.Note;
import com.vcosmujoao.notesbyj.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService service;

    @GetMapping
    public ResponseEntity<Page<Note>> findAll(Pageable pageable) {
        Page<Note> notes = service.getAllNotes(pageable);
        return ResponseEntity.ok().body(notes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findNoteById(@PathVariable Long id) {
        try{
            Optional<Note> optNote = service.findNoteById(id);
            if(optNote.isPresent()) {
                return new ResponseEntity<>(
                        optNote.get(), HttpStatus.OK);
            }
            else {
                return noNoteFoundResponse(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody NoteDTO noteDTO) {
        try {
            Optional<Note> optNote = service.findNoteById(id);
            if(optNote.isPresent()){
                return new ResponseEntity<>(
                        service.updateNote(optNote.get().getId(), noteDTO),
                        HttpStatus.OK);
            }else {
                return noNoteFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        try {
            Optional<Note> optNote = service.findNoteById(id);
            if (optNote.isPresent()) {
                service.deleteNote(optNote.get());
                return new ResponseEntity<>(String.format("Note with id: %d was deleted",id),HttpStatus.NO_CONTENT);
            } else {
                return noNoteFoundResponse(id);
            }
        } catch (Exception e) {
            return errorResponse();
        }
    }


    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong :(", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private ResponseEntity<String> noNoteFoundResponse(Long id){
        return new ResponseEntity<>("No task found with id: " + id, HttpStatus.NOT_FOUND);
    }

}
