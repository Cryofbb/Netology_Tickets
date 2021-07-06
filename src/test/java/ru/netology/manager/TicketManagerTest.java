package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.NotFoundExeption;
import ru.netology.domain.Ticket;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketManager manager = new TicketManager();
    private Ticket item1 = new Ticket(1, 89235, "DME", "LED", 123);
    private Ticket item2 = new Ticket(2, 253890, "DME", "SVO", 523);
    private Ticket item3 = new Ticket(3, 12943, "DME", "LED", 678);
    private Ticket item4 = new Ticket(4, 890723, "DME", "SVO", 39);
    private Ticket item5 = new Ticket(5, 934, "SVO", "LED", 342);
    private Ticket item6 = new Ticket(6, 9712, "LED", "SVO", 938);
    private Ticket item7 = new Ticket(7, 978243, "LED", "SVO", 982);
    private Ticket item8 = new Ticket(8, 12389, "LED", "SVO", 892);
    private Ticket item9 = new Ticket(9, 4589, "LED", "SVO", 984);


    @BeforeEach
    public void setup() {
        manager.save(item1);
        manager.save(item2);
        manager.save(item3);
        manager.save(item4);
        manager.save(item5);
        manager.save(item6);
        manager.save(item7);
        manager.save(item8);
        manager.save(item9);
    }

    @Test
    public void sortByPrice() {
        Ticket[] expected = new Ticket[]{item5, item9, item6, item8, item3, item1, item2, item4, item7};
        Ticket[] actual = new Ticket[]{item1, item2, item3, item4, item5, item6, item7, item8, item9};
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNothing() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("SVO", "SVO");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchSome() {
        Ticket[] expected = new Ticket[]{item9, item6, item8, item7};
        Ticket[] actual = manager.searchBy("LED", "SVO");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchLowerCase() {
        Ticket[] expected = new Ticket[]{item9, item6, item8, item7};
        Ticket[] actual = manager.searchBy("led", "svo");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchOne() {
        Ticket[] expected = new Ticket[]{item5};
        Ticket[] actual = manager.searchBy("svo", "LED");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchOneInEmpty() {
        TicketManager manager = new TicketManager();
        manager.save(item1);
        Ticket[] expected = new Ticket[]{item1};
        Ticket[] actual = manager.searchBy("dme", "LED");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNothingInEmpty() {
        TicketManager manager = new TicketManager();
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.searchBy("svo", "LED");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByID() {
        manager.remove(6);
        Ticket[] expected = new Ticket[]{item9, item8, item7};
        Ticket[] actual = manager.searchBy("led", "svo");
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByWrongID(){
        assertThrows(NotFoundExeption.class, () -> {
            manager.remove(23);
        });
    }
}