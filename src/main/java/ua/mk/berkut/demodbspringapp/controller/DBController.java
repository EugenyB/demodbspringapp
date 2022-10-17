package ua.mk.berkut.demodbspringapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.mk.berkut.demodbspringapp.entities.Gruppa;
import ua.mk.berkut.demodbspringapp.entities.Student;
import ua.mk.berkut.demodbspringapp.repository.GruppaRepository;
import ua.mk.berkut.demodbspringapp.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class DBController {

    private GruppaRepository gruppaRepository;

    private StudentRepository studentRepository;

    @GetMapping("/groups")
    public String showAllGroups(Model model) {
        List<Gruppa> groups = gruppaRepository.findAll();
        model.addAttribute("grs", groups);
        return "groups";
    }

    @PostMapping("/add_group")
    public String addGroup(@RequestParam String group) {
        Gruppa g = new Gruppa();
        g.setTitle(group);
        gruppaRepository.save(g);

        return "redirect:/groups";
    }

    @GetMapping("/delete_group/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        gruppaRepository.deleteById(id);

        return "redirect:/groups";
    }

    @GetMapping("/edit_group/{id}")
    public String showUpdateGroupPage(@PathVariable("id") int id, Model model) {
        Optional<Gruppa> gruppa = gruppaRepository.findById(id);
        if (gruppa.isEmpty()) {
            model.addAttribute("message", "Группы с id " + id + " нет");
            return "error";
        } else {
            model.addAttribute("gr", gruppa.get());
            return "edit_group";
        }
    }

    @PostMapping("/update_group/{id}")
    public String updateGroup(@PathVariable("id") int id, Gruppa gruppa) {
        Optional<Gruppa> g1 = gruppaRepository.findById(id);
        Gruppa gr = g1.get();
        gr.setTitle(gruppa.getTitle());
        gruppaRepository.save(gr);
        return "redirect:/groups";
    }

    @GetMapping("/students_group/{id}")
    public String viewStudentsByGroup(@PathVariable("id") int id, Model model) {
        Optional<Gruppa> gruppa = gruppaRepository.findById(id);
        if (gruppa.isEmpty()) {
            model.addAttribute("message", "Группы с id " + id + " нет");
            return "error";
        } else {
            model.addAttribute("gr", gruppa.get());
            return "student_by_group";
        }
    }

    @PostMapping("/find_student")
    public String findStudentByName(@RequestParam String name, Model model) {
        Optional<Student> student = studentRepository.findByName(name);
        if(student.isEmpty()) {
            model.addAttribute("message", "Студент с именем " + name + " не найден");
            return "error";
        } else {
            model.addAttribute("student", student.get());
            return "view_student";
        }
    }
}
