package pl.lehmann.notatnik;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public String getAllNotes(Model model) {
        List<Note> list = noteService.getAllNotes();

        model.addAttribute("notes", list);
        return "list-notes";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editNoteById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Note entity = noteService.getNoteById(id.get());
            model.addAttribute("note", entity);
            model.addAttribute("noteDto", new NoteDto());
        } else {
            model.addAttribute("note", new Note());
        }
        return "add-edit-note";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteNote(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        model.addAttribute("authorDto", new AuthorDto());
        return "delete-note";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String deleteNoteById(@ModelAttribute AuthorDto authorDto, Model model, @PathVariable("id") Long id) {
        try {
            noteService.deleteNoteById(id, authorDto);
            return "redirect:/";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "delete-note";
        }
    }

    @RequestMapping(path = "/createNote", method = RequestMethod.POST)
    public String createOrUpdateNote(@ModelAttribute NoteDto noteDto, Note note, Model model) {
        try {
            noteService.createOrUpdateNote(note, noteDto);
            return "redirect:/";
        } catch (IllegalArgumentException | EntityNotFoundException e) {
            model.addAttribute("messageAdd", e.getMessage());
            return "add-edit-note";
        }
    }
}
