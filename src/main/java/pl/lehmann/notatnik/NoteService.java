package pl.lehmann.notatnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private NoteRepo noteRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<Note> getAllNotes() {
        List<Note> result = (List<Note>) noteRepo.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Note>();
        }
    }

    public Note getNoteById(Long id) {
        Optional<Note> note = noteRepo.findById(id);

        if (note.isPresent()) {
            return note.get();
        } else {
            return null;
        }
    }

    public Note createOrUpdateNote(Note note) {
        if (note.getId() == null) {
            note = noteRepo.save(note);
            return note;
        } else {
            Optional<Note> note1 = noteRepo.findById(note.getId());

            if (note1.isPresent()) {
                Note newNote = note1.get();
                newNote.setTitle(note.getTitle());
                newNote.setAuthor(note.getAuthor());
                newNote.setMessage(note.getMessage());
                newNote.setDate(note.getDate());

                newNote = noteRepo.save(newNote);

                return newNote;
            } else {
                note = noteRepo.save(note);

                return note;
            }
        }
    }

    public void deleteNoteById(Long id) {
        Optional<Note> note = noteRepo.findById(id);

        if (note.isPresent()) {
            noteRepo.deleteById(id);
        } else {
            System.out.println("No note record exist for given id");
        }
    }
}
