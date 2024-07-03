package com.example.proiect_android_ionanamaria;
import androidx.room.Dao;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.Query;
        import androidx.room.Update;
        import java.util.List;

@Dao
public interface CardDao {


    @Insert
    long addCard(Card card);

    @Insert
    void addCards(List<Card> cards);

    @Query("UPDATE cards SET bankName=:bankName, cardNum=:cardNum, cardCVV=:cardCVV, expYr=:expYr,expMth=:expMth, balance=:balance, currency=:currency WHERE id=:id ")
    void update(long id, String bankName, long cardNum, int cardCVV, int expYr, int expMth, int balance, String currency);

    @Query("SELECT * FROM cards")
    List<Card> getAllCards();

    @Update
    void update(Card card);

    @Query("DELETE FROM cards WHERE id = :id")
    abstract void deleteById(long id);

    @Query("DELETE FROM cards")
    void deleteAllCards();

    @Delete
    void deleteCard(Card card);

}
