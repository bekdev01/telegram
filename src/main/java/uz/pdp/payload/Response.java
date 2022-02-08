package uz.pdp.payload;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.Message;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response{

	private List<Message> messageUser;

	private String id;

}