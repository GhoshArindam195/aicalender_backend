package com.holidays.alcalender_backend.util;

import com.holidays.alcalender_backend.dto.HolidayInstanceDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Utility class for detecting Long Weekends and Bridge Days
 * AC1: Detection Logic - Check if holiday falls on Friday or Monday
 * AC2: Bridge Detection - Check if holiday falls on Tuesday or Thursday
 */
@Component
public class LongWeekendUtil {

    /**
     * Analyze a list of holidays and mark long weekend and bridge day flags
     * @param holidays list of holiday instances
     * @return list of holidays with long weekend/bridge day metadata
     */
    public List<HolidayInstanceDto> analyzeHolidays(List<HolidayInstanceDto> holidays) {
        // First pass: mark all individual days
        for (HolidayInstanceDto holiday : holidays) {
            markDayType(holiday);
        }
        
        // Second pass: detect long weekends (Friday + Saturday + Sunday + Monday pattern)
        detectLongWeekends(holidays);
        
        return holidays;
    }

    /**
     * Mark individual day type (FRIDAY, MONDAY for long weekend, TUESDAY/THURSDAY for bridge)
     */
    private void markDayType(HolidayInstanceDto holiday) {
        LocalDate date = holiday.getDate();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        
        String dayName = dayOfWeek.name();
        holiday.setDayOfWeek(dayName);
        
        // AC1: Long Weekend detection - Friday or Monday
        boolean isFriday = dayOfWeek == DayOfWeek.FRIDAY;
        boolean isMonday = dayOfWeek == DayOfWeek.MONDAY;
        holiday.setIsLongWeekend(isFriday || isMonday);
        
        // AC2: Bridge Day detection - Tuesday or Thursday
        boolean isTuesday = dayOfWeek == DayOfWeek.TUESDAY;
        boolean isThursday = dayOfWeek == DayOfWeek.THURSDAY;
        holiday.setIsBridgeDay(isTuesday || isThursday);
    }

    /**
     * Detect consecutive long weekends by checking surrounding days
     * A long weekend is detected when:
     * - Holiday on Friday (plus weekend follows)
     * - Holiday on Monday (plus weekend precedes)
     */
    private void detectLongWeekends(List<HolidayInstanceDto> holidays) {
        // Create a date-based lookup
        java.util.Map<LocalDate, HolidayInstanceDto> holidayMap = new java.util.HashMap<>();
        for (HolidayInstanceDto holiday : holidays) {
            holidayMap.put(holiday.getDate(), holiday);
        }
        
        // Check each holiday to see if it forms a long weekend
        for (HolidayInstanceDto holiday : holidays) {
            LocalDate date = holiday.getDate();
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            boolean isLongWeekend = false;
            
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                // Check if Saturday and Sunday exist in the holiday list
                LocalDate saturday = date.plusDays(1);
                LocalDate sunday = date.plusDays(2);
                if (holidayMap.containsKey(saturday) || holidayMap.containsKey(sunday)) {
                    isLongWeekend = true;
                }
            } else if (dayOfWeek == DayOfWeek.MONDAY) {
                // Check if Saturday and Sunday exist before the holiday
                LocalDate saturday = date.minusDays(2);
                LocalDate sunday = date.minusDays(1);
                if (holidayMap.containsKey(saturday) || holidayMap.containsKey(sunday)) {
                    isLongWeekend = true;
                }
            }
            
            // Update the holiday with refined detection
            holiday.setIsLongWeekend(isLongWeekend || dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.MONDAY);
        }
    }

    /**
     * Get upcoming long weekends from the holiday list
     * @param holidays list of holiday instances
     * @param limit number of long weekends to return
     * @return list of long weekend holidays
     */
    public List<HolidayInstanceDto> getUpcomingLongWeekends(List<HolidayInstanceDto> holidays, int limit) {
        LocalDate today = LocalDate.now();
        
        return holidays.stream()
                .filter(h -> h.getIsLongWeekend() != null && h.getIsLongWeekend())
                .filter(h -> !h.getDate().isBefore(today))
                .sorted((h1, h2) -> h1.getDate().compareTo(h2.getDate()))
                .limit(limit)
                .toList();
    }

    /**
     * Get upcoming bridge days from the holiday list
     * @param holidays list of holiday instances
     * @param limit number of bridge days to return
     * @return list of bridge day holidays
     */
    public List<HolidayInstanceDto> getUpcomingBridgeDays(List<HolidayInstanceDto> holidays, int limit) {
        LocalDate today = LocalDate.now();
        
        return holidays.stream()
                .filter(h -> h.getIsBridgeDay() != null && h.getIsBridgeDay())
                .filter(h -> !h.getDate().isBefore(today))
                .sorted((h1, h2) -> h1.getDate().compareTo(h2.getDate()))
                .limit(limit)
                .toList();
    }
}
