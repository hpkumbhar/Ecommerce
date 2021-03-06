
package dw.fdb.com.fdbapp;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import org.apache.http.HttpStatus;

import java.io.IOException;

import de.greenrobot.event.EventBus;
import dw.fdb.com.fdbapp.model.AuthTokenException;
import dw.fdb.com.fdbapp.ws.WSCart;
import dw.fdb.com.fdbapp.ws.WSCategory;
import dw.fdb.com.fdbapp.ws.WSProduct;
import retrofit.ErrorHandler;
import retrofit.RestAdapter.Builder;
import retrofit.RetrofitError;
import retrofit.converter.GsonConverter;

public class RestService extends RetrofitGsonSpiceService {
    public String url = "http://142.4.211.181/dwickrema/prestashop.v1/ci/index.php/api";

    @Override
    protected String getServerUrl() {
        return url;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(WSCategory.class);
        addRetrofitInterface(WSProduct.class);
        addRetrofitInterface(WSCart.class);
    }

    @Override
    protected Builder createRestAdapterBuilder() {

        return super.createRestAdapterBuilder().setConverter(new GsonConverter(new GsonBuilder()
                .registerTypeAdapter(TypeAdapter.class, new TypeAdapter<Integer>() {

                    @Override
                    public void write(JsonWriter out, Integer value)
                            throws IOException {
                        out.value(value);
                    }

                    @Override
                    public Integer read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        try {
                            String result = in.nextString();
                            if ("".equals(result)) {
                                return null;
                            }
                            return Integer.parseInt(result);
                        } catch (NumberFormatException e) {
                            throw new JsonSyntaxException(e);
                        }
                    }
                }).create()));
    }


    class AuthErrorHandler implements ErrorHandler {
        @Override
        public Throwable handleError(RetrofitError e) {

            switch (e.getResponse().getStatus()) {

                case HttpStatus.SC_OK:
                    System.out.println(e.getBodyAs(String.class).toString());

                case HttpStatus.SC_ACCEPTED:
                    System.out.println(e.getBodyAs(String.class).toString());

                case HttpStatus.SC_NOT_FOUND:
                    return new Throwable("ERREUR 404").getCause();

                case HttpStatus.SC_UNAUTHORIZED:
                    AuthTokenException error = (AuthTokenException) e
                            .getBodyAs(AuthTokenException.class);
                    AuthTokenException tokenException = new AuthTokenException();
                    tokenException.setError(error.getErrorDescription());
                    EventBus.getDefault().post(tokenException);
                    return new Throwable("ERREUR 401").getCause();

            }
            return new SpiceException("" + e.getBodyAs(String.class).toString());
        }

    }

}

