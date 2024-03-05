package ru.job4j.ood.srp.badsrp;

import java.text.SimpleDateFormat;
import java.util.Date;

/* Дата выводится в определённом формате, преобразование в формат есть преобразование, которое может поменяться,
поэтому лучше вынести преобразование в отдельный класс */
public class DateFormatter {
    public static String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
