package dw.fdb.com.fdbapp.db.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DBCART.
 */
public class DBCart {

    private Long id;
    private Integer id_cart;
    private String date_upd;
    private String date_add;

    public DBCart() {
    }

    public DBCart(Long id) {
        this.id = id;
    }

    public DBCart(Long id, Integer id_cart, String date_upd, String date_add) {
        this.id = id;
        this.id_cart = id_cart;
        this.date_upd = date_upd;
        this.date_add = date_add;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getId_cart() {
        return id_cart;
    }

    public void setId_cart(Integer id_cart) {
        this.id_cart = id_cart;
    }

    public String getDate_upd() {
        return date_upd;
    }

    public void setDate_upd(String date_upd) {
        this.date_upd = date_upd;
    }

    public String getDate_add() {
        return date_add;
    }

    public void setDate_add(String date_add) {
        this.date_add = date_add;
    }

}