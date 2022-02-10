package streams.serializer;

import java.nio.charset.Charset;

import dto.ShopDTO;
import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;

public class ShopDeserializer implements Deserializer {
	
	private static final Charset CHARSET = Charset.forName("UTF-8");	
    static private Gson gson = new Gson();

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            String person = new String(bytes, CHARSET);
            return gson.fromJson(person, ShopDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading bytes! Yanlış", e);
        }
    }

}