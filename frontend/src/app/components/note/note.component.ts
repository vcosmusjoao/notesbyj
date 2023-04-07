import { DialogService } from 'primeng/dynamicdialog';
import { Note } from './../../models/note';
import { NoteService } from './../../services/note.service';
import { Component, OnInit } from '@angular/core';
import { CreateNoteModalComponent } from '../create-note-modal/create-note-modal.component';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  notes: Note[] = [];

  constructor(private noteService: NoteService, public dialogService: DialogService) { }

  ngOnInit(): void {
   this.getAllNotes();
  }

  getAllNotes(){
    this.noteService.getAllNotes().subscribe(response =>{
      this.notes = response.content;
    });
  }

  createNoteModal(){
    const ref = this.dialogService.open(CreateNoteModalComponent,{
      width:'40%',
      height:'70%'
    });
    ref.onClose.subscribe(()=>{
      this.getAllNotes();
    })
  }

}
