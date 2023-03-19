import { Note } from './../../models/note';
import { NoteService } from './../../services/note.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  notes: Note[] = [];

  constructor(private noteService: NoteService) { }

  ngOnInit(): void {
    this.noteService.getAllNotes().subscribe(response =>{
      this.notes = response.content;
    });
  }

}
