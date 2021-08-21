package pl.Dominik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.Dominik.entity.Event;
import pl.Dominik.entity.User;
import pl.Dominik.app.UserEntityDetails;
import pl.Dominik.repo.EventRepo;
import pl.Dominik.repo.UserRepo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventRepo repo;

    @Autowired
    UserRepo userRepo;

    private String userDetails() {
        UserEntityDetails details = (UserEntityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return details.getUsername();
    }

    @GetMapping("/add")
    public String addEvent(Model model) {
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @PostMapping("/save")
    public String save(@Valid Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "addEvent";
        }
        UserEntityDetails details = (UserEntityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = details.getUsername();
        User user = new User();
        user.setId(userRepo.findByUsernameIgnoreCase(userName).get().getId());
        user.setUsername(userName);
        user.setPassword(details.getPassword());
        event.setOwner(user);
        List<User> participants = new ArrayList<>();
        participants.add(user);
        event.setParticipants(participants);
        repo.save(event);
        return "redirect:/user/homePage";
    }

    @GetMapping("/all")
    public String allUserEvents(Model model) {
        String userName = userDetails();
        List<Event> list = repo.findByOwner_id(userRepo.findByUsernameIgnoreCase(userName).get().getId());
        List<Event> events = repo.findAll();
        List<Event> freshList = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            List<User> listOfUsers = events.get(i).getParticipants();
            if(events.get(i).getOwner().getId() != userRepo.findByUsernameIgnoreCase(userName).get().getId()) {
                int counter = 0;
                for (int j = 0; j < listOfUsers.size(); j++) {
                    if (listOfUsers.get(j).getId() == userRepo.findByUsernameIgnoreCase(userName).get().getId()) {
                        counter++;
                    }
                    if (counter == 1) {
                        freshList.add(events.get(i));
                    }
                }
            }
        }
        model.addAttribute("events", list);
        model.addAttribute("joined", freshList);
        return "allUserEvents";
    }

    @GetMapping("/delete")
    public String deleteEvent(@RequestParam Long id) {
        repo.deleteById(id);
        return "redirect:/event/all";
    }


    @GetMapping("/allEvents")
    public String allEvents(Model model) {
        String userName = userDetails();
        List<Event> list = repo.findAll();
        List<Event> sortedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOwner().getId() != userRepo.findByUsernameIgnoreCase(userName).get().getId()) {
                List<User> users = list.get(i).getParticipants();
                int counter = 0;
                for (int j = 0; j < users.size(); j++) {
                    if(users.get(j).getId() == userRepo.findByUsernameIgnoreCase(userName).get().getId()){
                        counter++;
                    }
                }
                if(counter == 0){
                    sortedList.add(list.get(i));
                }
            }
        }
        model.addAttribute("list", sortedList);
        return "allEvents";
    }

    @RequestMapping("/join")
    public String join(@RequestParam Long id) {
        Event event = new Event();
        event.setId(id);
        Event event1 = repo.findById(id).get();
        event.setDate(event1.getDate());
        event.setDescription(event1.getDescription());
        event.setName(event1.getName());
        event.setPlace(event1.getPlace());
        event.setOwner(event1.getOwner());
        List<User> list = event1.getParticipants();
        User user = new User();
        String userName = userDetails();
        user.setId(userRepo.findByUsernameIgnoreCase(userName).get().getId());
        list.add(user);
        event.setParticipants(list);
        repo.save(event);
        return "redirect:/event/allEvents";
    }

    @GetMapping("/details")
    public String details(@RequestParam Long id, Model model){
        Optional<Event> event = repo.findById(id);
        Event event1 = new Event();
        event1.setId(event.get().getId());
        event1.setParticipants(event.get().getParticipants());
        event1.setPlace(event.get().getPlace());
        event1.setDate(event.get().getDate());
        event1.setDescription(event.get().getDescription());
        event1.setName(event.get().getName());
        event1.setOwner(event.get().getOwner());
        model.addAttribute("event", event1);
        return "eventDetails";
    }

    @GetMapping("/edit")
    public String editEvent(Model model, @RequestParam Long id){
        repo.findById(id);
        Event event = new Event();
        event.setId(repo.findById(id).get().getId());
        event.setName(repo.findById(id).get().getName());
        event.setDescription(repo.findById(id).get().getDescription());
        event.setPlace(repo.findById(id).get().getPlace());
        event.setDate(repo.findById(id).get().getDate());
        event.setParticipants(repo.findById(id).get().getParticipants());
        model.addAttribute("event", event);
        return "editEvent";
    }
    @PostMapping("/editSave")
    public String saveEvent(Event event){
        UserEntityDetails details = (UserEntityDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = details.getUsername();
        User user = new User();
        user.setId(userRepo.findByUsernameIgnoreCase(userName).get().getId());
        user.setUsername(userName);
        user.setPassword(details.getPassword());
        event.setOwner(user);
        List<User> participants = new ArrayList<>();
        participants.add(user);
        event.setParticipants(participants);
        repo.save(event);
        return "redirect:/user/homePage";
    }


}
