import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Note } from './../../models/note';
import { NoteService } from './../../services/note.service';
import { Component, OnInit } from '@angular/core';
import { CreateNoteModalComponent } from '../create-note-modal/create-note-modal.component';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css'],
  providers:[DynamicDialogRef]
})
export class NoteComponent implements OnInit {

  notes: Note[] = [];
  selectedNote: Note;

  constructor(
    private noteService: NoteService,
    private dialogService: DialogService,
    private ref: DynamicDialogRef // injeta o DynamicDialogRef
  ) { }

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


  openNoteModal(note: Note) {
    this.selectedNote = note;
    const ref = this.dialogService.open(CreateNoteModalComponent, {
      width: '40%',
      height: '70%',
      data: {note: this.selectedNote}
    });
    ref.onClose.subscribe(() => {
      this.getAllNotes();
    });
  }

  deleteNoteModal(note: Note){
    const noteId = note.id
    if(confirm('Tem certeza que deseja excluir essa nota?')){
      this.noteService.deleteNote(noteId).subscribe(()=>{
        this.ref.close();
        this.getAllNotes();
      })
    }
  }
}
