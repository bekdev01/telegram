package uz.pdp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bson.Document;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    String text;
    Date date;
    boolean isMine;
}
