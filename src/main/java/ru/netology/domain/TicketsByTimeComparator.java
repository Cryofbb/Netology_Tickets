package ru.netology.domain;

import java.util.Comparator;

public class TicketsByTimeComparator implements Comparator<Ticket> {
    public int compare(Ticket o1, Ticket o2) {
        return o1.getTime() - o2.getTime();
    }
}