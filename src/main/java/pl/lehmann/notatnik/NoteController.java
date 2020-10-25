package pl.lehmann.notatnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping
    public String getAllNotes(Model model)
    {
        List<Note> list = noteService.getAllNotes();

        model.addAttribute("notes", list);
        return "list-notes";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editNoteById(Model model, @PathVariable("id") Optional<Long> id)
    {
        if (id.isPresent()) {
            Note entity = noteService.getNoteById(id.get());
            model.addAttribute("note", entity);
        } else {
            model.addAttribute("note", new Note());
        }
        return "add-edit-note";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteNoteById(Model model, @PathVariable("id") Long id)
    {
        return "delete-note";
    }

    @RequestMapping(path = "/createNote", method = RequestMethod.POST)
    public String createOrUpdateNote(Note note)
    {
        noteService.createOrUpdateNote(note);
        return "redirect:/";
    }
}
