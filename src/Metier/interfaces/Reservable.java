package Metier.interfaces;

public interface Reservable {


    public void reserve(int document_id,int user_id);
    public void unreserve(int document_id,int user_id);
}
