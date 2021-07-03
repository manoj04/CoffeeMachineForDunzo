package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.Set;


@Getter
@Setter
@Builder
@ToString
public class Machine {
    private String id;

    private Integer numberOfOutlets;

    private Map<String, Integer> inventoryCatalogue;

    private Set<Beverage> beveragesToBeServed;

}
