package ru.netology.manager;

import lombok.RequiredArgsConstructor;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;


@RequiredArgsConstructor

public class TicketManager {
    private TicketRepository repo = new TicketRepository();

    public void save(Ticket product) {
        repo.save(product);
    }

    public Ticket[] searchBy(String departed, String arrival) {
        Ticket[] result = new Ticket[0];
        for (Ticket item : repo.findAll()) {
            if (item.getArrival().equalsIgnoreCase(arrival) & item.getDeparture().equalsIgnoreCase(departed)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        Arrays.sort(result);
        return result;
    }

    public void remove(int id) {
        repo.removeByID(id);
    }
}