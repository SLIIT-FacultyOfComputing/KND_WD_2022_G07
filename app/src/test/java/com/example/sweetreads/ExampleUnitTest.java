package com.example.sweetreads;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.sweetreads.shop.ItemSelectedActivity;
import com.example.sweetreads.shop.PaymentActivity;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public boolean signUpSucess() {

        AuthorizationActivity aa1 = new AuthorizationActivity();

        if(aa1.signUp.equals("sign up successful"))
            return true;
        else if(aa1.signUp.equals("sign up failed"))
            return false;
        else if(aa1.signUp.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean signInSucess() {

        AuthorizationActivity aa2 = new AuthorizationActivity();

        if(aa2.signUp.equals("sign In successful"))
            return true;
        else if(aa2.signUp.equals("sign In failed"))
            return false;
        else if(aa2.signUp.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean AccountIsDeleted() {

        ProfileActivity pa1 = new ProfileActivity();

        if(pa1.accountDeleted.equals("account deleted"))
            return true;
        else if(pa1.accountDeleted.equals("account not deleted"))
            return false;
        else if(pa1.accountDeleted.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean BookIsAdded() {

        UploadBookActivity uba1 = new UploadBookActivity();

        if(uba1.bookAdded.equals("book adding passed"))
            return true;
        else if(uba1.bookAdded.equals("book adding failed"))
            return false;
        else if(uba1.bookAdded.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean InventoryBookIsDeleted() {

        DisplayResultsActivity ura1 = new DisplayResultsActivity();

        if(ura1.inventoryBookDeleted.equals("book is deleted"))
            return true;
        else if(ura1.inventoryBookDeleted.equals("book is not deleted"))
            return false;
        else if(ura1.inventoryBookDeleted.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean InventoryBookIsSearched() {

        DisplayResultsActivity ura2 = new DisplayResultsActivity();

        if(ura2.inventoryBookSearched.equals("book is searched"))
            return true;
        else if(ura2.inventoryBookSearched.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean ClientBookIsSearched() {

        ItemSelectedActivity isa1 = new ItemSelectedActivity();

        if(isa1.clientBookSearched.equals("book is searched"))
            return true;
        else if(isa1.clientBookSearched.equals("failed to search book"))
            return false;
        else if(isa1.clientBookSearched.equals("not defined"))
            return false;
        else
            return false;

    }

    @Test
    public boolean paymentIsSucessful() {

        PaymentActivity pay1 = new PaymentActivity();

        if(pay1.paymentAdded.equals("payment is passed"))
            return true;
        else if(pay1.paymentAdded.equals("payment is failed"))
            return false;
        else if(pay1.paymentAdded.equals("not defined"))
            return false;
        else
            return false;

    }

}