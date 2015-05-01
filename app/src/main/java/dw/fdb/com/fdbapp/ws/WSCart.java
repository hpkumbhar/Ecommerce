package dw.fdb.com.fdbapp.ws;


import dw.fdb.com.fdbapp.model.cart.Cart;
import dw.fdb.com.fdbapp.model.cart.CartModel;
import dw.fdb.com.fdbapp.model.cart.CartProduct;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

public interface WSCart {

    @GET("/cart/customer/{id_customer}/get")
    public Cart getLastNoneOrderedCart(@Path("id_customer") int id_customer);

    @GET("/cart/{id_cart}/product/get")
    public CartModel getProductCartById(@Path("id_cart") int id_cart,
                                                  @Header("Authorization") String token);

    @PUT("/cart/{id_cart}/product/edit")
    public Cart editProductCartById(@Path("id_cart") int id_cart,
                                    @Body Cart cart);

    @DELETE("/cart/{id_cart}/product/{id_product}/delete")
    public Cart deleteProductCartById(@Path("id_cart") int id_cart,
                                      @Path("id_product") int id_product);

    @POST("/cart/create")
    public Cart createCart();

    @POST("/cart/{id_cart}/product/add")
    public CartProduct addProductToCartById(
            @Path("id_cart") int id_cart,
            @Header("Authorization") String token,
            @Body CartProduct cartProduct, @Query("cart") String cart);

}
