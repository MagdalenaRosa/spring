package pl.java.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactsController {
    private static final String Phone = "+48 786 456 788";
    private static final String Email = "one.wp.pl";

    // tylko raz pod głównym adresem można
    @GetMapping("/AboutUs")
    public String ShowAboutUs(Model model) {
        model.addAttribute("title", "O nas");
        return "AboutUs";
    }

    @GetMapping("/Contacts")
    public String ShowContacts(Model model) {
        model.addAttribute("title", "Kontakt");
        model.addAttribute("PHONE", Phone);
        model.addAttribute("Email", Email);
        return "Contacts";

    }
}
// żeby módł rozmawić z innymi kontolerami to
