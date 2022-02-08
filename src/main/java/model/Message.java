package model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bson.Document;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message {
    Document from;
    Document to;
}
