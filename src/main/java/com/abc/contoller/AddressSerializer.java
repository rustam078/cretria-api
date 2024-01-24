package com.abc.contoller;
import java.io.IOException;

import org.hibernate.proxy.HibernateProxy;

import com.abc.entity.Address;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class AddressSerializer extends JsonSerializer<Address> {

    @Override
    public void serialize(Address value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value instanceof HibernateProxy) {
            // Serialize the actual class instead of the proxy
            serializers.defaultSerializeValue(((HibernateProxy) value).getHibernateLazyInitializer().getImplementation(), gen);
        } else {
            // Serialize the actual instance
            serializers.defaultSerializeValue(value, gen);
        }
    }
}
