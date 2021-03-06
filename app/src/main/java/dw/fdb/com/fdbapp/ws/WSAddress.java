package dw.fdb.com.fdbapp.ws;

import dw.fdb.com.fdbapp.model.address.AddressInvoice;
import dw.fdb.com.fdbapp.model.address.AddressModel;
import dw.fdb.com.fdbapp.model.order.OrderHistory;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

public interface WSAddress {

    @GET("/address/customer/{id_customer}/get")
    public AddressInvoice.AddressList getAddressById(@Path("id_customer") int id_customer);

    @GET("/address/get")
    public AddressModel getAddress();

    @DELETE("/address/{id_address}/delete")
    public OrderHistory deleteAddressById(@Path("id_address") int id_address);

    @PUT("/address/{id_address}/edit")
    public OrderHistory editAddressById(@Path("id_address") int id_address, @Body OrderHistory address);

    @POST("/address/create")
    public OrderHistory addAddress(@Body OrderHistory address);


}
