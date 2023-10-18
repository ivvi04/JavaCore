package ru.lakeevda.lesson3.homework.enums.parameters;

import ru.lakeevda.lesson3.homework.enums.Priority;

import java.util.EnumMap;
import java.util.Map;

public abstract class PriorityParams {
    static public EnumMap<Priority, Integer> priorityEnumMap = new EnumMap<>(
            Map.of(
                    ru.lakeevda.lesson3.homework.enums.Priority.LOW, 1,
                    ru.lakeevda.lesson3.homework.enums.Priority.MIDDLE, 2,
                    ru.lakeevda.lesson3.homework.enums.Priority.HIGH, 3,
                    ru.lakeevda.lesson3.homework.enums.Priority.VERY_HIGH, 4
            ));
}
