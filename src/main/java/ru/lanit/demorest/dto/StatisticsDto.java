package ru.lanit.demorest.dto;

public class StatisticsDto {

    private Long personcount;

    private Long carcount;

    private Long uniquevendorcount;

    public StatisticsDto(Long personcount, Long carcount, Long uniquevendorcount) {
        this.personcount = personcount;
        this.carcount = carcount;
        this.uniquevendorcount = uniquevendorcount;
    }

    public Long getPersoncount() {
        return personcount;
    }

    public Long getCarcount() {
        return carcount;
    }

    public Long getUniquevendorcount() {
        return uniquevendorcount;
    }
}